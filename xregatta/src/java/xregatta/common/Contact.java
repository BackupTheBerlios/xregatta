/* 
 * xregatta - regatta documentation exchange format
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
package xregatta.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jdom.Element;
import org.jdom.Namespace;

import xregatta.util.IdBroker;

/**
 * Contact
 * 
 * @author Tammo van Lessen
 * @version $Id: Contact.java,v 1.1 2003/07/25 19:54:35 vanto Exp $
 */
public class Contact {
	protected static final Namespace VCARD_NS = Namespace.getNamespace("vcard", "http://xregatta.berlios.de/vcard");
	
	private String nickname;
	private String family;
	private String given;
	private String middle;
	private String fullname;
	private String email;
	private Date birthday;
	private String url;
	private String orgname;
	private String orgunit;
	private String title;
	private String role;
	private String desc;
	private String gender;

	private Location home;
	private Location work;
	
	private String licenseid;
	private String xregid;

	/**
	 * Location
	 */
	public class Location {
		public String phone;
		public String fax;
		public String mobile;

		public String street;
		public String extadd;
		public String locality;
		public String region;
		public String pcode;
		public String country;
	}

	/**
	 * @return
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @return
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return
	 */
	public String getGiven() {
		return given;
	}

	/**
	 * @return
	 */
	public Location getHome() {
		return home;
	}

	/**
	 * @return
	 */
	public String getLicenseid() {
		return licenseid;
	}

	/**
	 * @return
	 */
	public String getMiddle() {
		return middle;
	}

	/**
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * @return
	 */
	public String getOrgunit() {
		return orgunit;
	}

	/**
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return
	 */
	public Location getWork() {
		return work;
	}

	/**
	 * @return
	 */
	public String getXregid() {
		return xregid;
	}

	/**
	 * @param string
	 */
	public void setBirthday(Date date) {
		birthday = date;
	}

	/**
	 * @param string
	 */
	public void setDesc(String string) {
		desc = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setFamily(String string) {
		family = string;
	}

	/**
	 * @param string
	 */
	public void setFullname(String string) {
		fullname = string;
	}

	/**
	 * @param string
	 */
	public void setGender(String string) {
		gender = string;
	}

	/**
	 * @param string
	 */
	public void setGiven(String string) {
		given = string;
	}

	/**
	 * @param location
	 */
	public void setHome(Location location) {
		home = location;
	}

	/**
	 * @param string
	 */
	public void setLicenseid(String string) {
		licenseid = string;
	}

	/**
	 * @param string
	 */
	public void setMiddle(String string) {
		middle = string;
	}

	/**
	 * @param string
	 */
	public void setNickname(String string) {
		nickname = string;
	}

	/**
	 * @param string
	 */
	public void setOrgname(String string) {
		orgname = string;
	}

	/**
	 * @param string
	 */
	public void setOrgunit(String string) {
		orgunit = string;
	}

	/**
	 * @param string
	 */
	public void setRole(String string) {
		role = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @param location
	 */
	public void setWork(Location location) {
		work = location;
	}

	/**
	 * @param string
	 */
	public void setXregid(String string) {
		xregid = string;
	}

	public Element getXMLTree() {
		Element el = new Element("vCard", VCARD_NS);
		String objId = IdBroker.getObjectId(this);
		if (objId == null) {
			objId = IdBroker.getNextId(this);
			IdBroker.registerObject(this, objId);
			el.setAttribute("id", objId);
		} else {
			el.setAttribute("refid", objId);
			return el;
		}

		add(el, "fn", fullname);
		add(el, "nickname", nickname);
		add(el, "url", url);
		Calendar bday = Calendar.getInstance();
		bday.setTime(birthday);
		//add(el, "year-of-birth", ""+bday.get(Calendar.YEAR));
		add(el, "bday", new SimpleDateFormat("yyyy-MM-dd").format(birthday));
		add(el, "gender", gender);
		add(el, "title", title);
		add(el, "role", role);
		add(el, "desc", desc);
		add(el, "licendeid", licenseid);
		add(el, "xregattaid", xregid);
		
		add(el, "email", email);


		Element u = new Element("name", VCARD_NS);
		add(u, "family", family);
		add(u, "given", given);
		add(u, "middle", middle);
		if (u.hasChildren()) el.addContent(u);

		u = new Element("org", VCARD_NS);
		add(u, "orgname", orgname);
		add(u, "orgunit", orgunit);
		if (u.hasChildren()) el.addContent(u);

		if (work != null) {
			addLocation(el, work, "work");
		}

		if (home != null) {
			addLocation(el, home, "home");
		}

		return el;
	}
	
	protected void addLocation(Element el, Location loc, String name) {
		if (loc != null) {
			Element u = new Element("adr", VCARD_NS);
			add(u, "extadd", loc.extadd);
			add(u, "street", loc.street);
			add(u, "locality", loc.locality);
			add(u, "region", loc.region);
			add(u, "pcode", loc.pcode);
			add(u, "ctry", loc.country);
			if (u.hasChildren()) {
				add(u, "type", name);
				el.addContent(u);
			} 

			if (loc.phone != null) {
				u = new Element("tel", VCARD_NS);
				add(u, "type", "voice");
				add(u, "type", name);
				add(u, "number", loc.phone);
				el.addContent(u);
			}

			if (loc.fax != null) {
				u = new Element("tel", VCARD_NS);
				add(u, "type", "fax");
				add(u, "type", name);
				add(u, "number", loc.fax);
				el.addContent(u);
			}

			if (loc.mobile != null) {
				u = new Element("tel", VCARD_NS);
				add(u, "type", "mobile");
				add(u, "type", name);
				add(u, "number", loc.mobile);
				el.addContent(u);
			}
		}
	}
	protected void add(Element el, String elname, String value) {
		if (el != null && value != null && !value.equals("")) {
			el.addContent(new Element(elname, VCARD_NS).setText(value));
		}		
	}		
}
