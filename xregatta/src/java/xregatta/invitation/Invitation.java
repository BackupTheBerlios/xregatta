/* 
 * xregatta - electronic regatta standards
 * http://xregatta.berlios.de
 * 
 * Copyright (C) 2003 Tammo van Lessen
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package xregatta.invitation;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.Namespace;

import xregatta.util.IdBroker;

/**
 * Schedule
 * 
 * @author Tammo van Lessen
 * @version $Id: Invitation.java,v 1.2 2003/07/25 20:06:38 vanto Exp $
 */
public class Invitation {

	public static final String NAMESPACE_URI = "http://xregatta.berlios.de/invitation";
	public static final Namespace NAMESPACE = Namespace.getNamespace("inv", NAMESPACE_URI);
	private String uid;
	private String name;
	private String description;
	private String remarks;
	private String type;
	private String location;
	private String host;
	private List dates = new ArrayList();
	private BankInformation bankInformation;
	private List races = new ArrayList();
	private List schedule = new ArrayList(); 

	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	public String getUID() {
		return (uid != null)?uid:Long.toHexString(new java.util.Date().getTime());
	}
	
	protected void setUID(String uid) {
		this.uid = uid;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public List getRaces() {
		return races;
	}

	/**
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string) {
		location = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param list
	 */
	public void addRace(Race race) {
		races.add(race);
	}

	/**
	 * @param string
	 */
	public void setRemarks(String string) {
		remarks = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	public Element getXMLTree() {
		Element el = new Element("regatta", NAMESPACE);
		String objId = IdBroker.getObjectId(this);
		if (objId == null) {
			objId = IdBroker.getNextId(this);
			IdBroker.registerObject(this, objId);
			el.setAttribute("id", objId);
		} else {
			el.setAttribute("refid", objId);
			return el;
		}

		// UID
		el.setAttribute("uid", getUID());
		// name
		el.addContent(new Element("name", NAMESPACE).setText(this.name));
		// description
		el.addContent(new Element("description", NAMESPACE).setText(this.description));
		// type
		el.addContent(new Element("type", NAMESPACE).setText(this.type));
		// host
		el.addContent(new Element("host", NAMESPACE).setText(this.host));
		// location
		el.addContent(new Element("location", NAMESPACE).setText(this.location));

		// bankinfo
		if (bankInformation != null) {
			el.addContent(bankInformation.getXMLTree());
		}
		
		// races
		Element races = new Element("races", NAMESPACE);
		for (int i=0; i<this.races.size(); i++) {
			races.addContent(((Race)this.races.get(i)).getXMLTree());
		}
		
		el.addContent(races);
		return el;
	}

	/**
	 * @param bi
	 */
	public void setBankInformation(BankInformation bi) {
		bankInformation = bi;
	}
	
	public BankInformation getBankInformation() {
		return bankInformation;
	}
	/**
	 * @return
	 */
	public List getDates() {
		return dates;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return
	 */
	public List getSchedule() {
		return schedule;
	}

	/**
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param list
	 */
	public void addDate(Date date) {
		dates.add(date);
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setHost(String string) {
		host = string;
	}

	/**
	 * @param list
	 */
	public void setRaces(List list) {
		races = list;
	}

	/**
	 * @param list
	 */
	public void setSchedule(List list) {
		schedule = list;
	}

	/**
	 * @param string
	 */
	public void setUid(String string) {
		uid = string;
	}

}
