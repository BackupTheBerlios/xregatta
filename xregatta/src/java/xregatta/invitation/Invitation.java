/*
 * xregatta - electronic regatta standards
 * http://xregatta.berlios.de
 *
 * Copyright (C) 2003 Tammo van Lessen
 *
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

import xregatta.common.Contact;
import xregatta.util.IdBroker;


/**
 * Schedule
 *
 * @author Tammo van Lessen
 * @version $Id: Invitation.java,v 1.4 2004/04/22 23:50:25 vanto Exp $
 */
public class Invitation
{
    //~ Static fields/initializers ---------------------------------------------

    public static final String NAMESPACE_URI = "http://xregatta.berlios.de/invitation";
    public static final Namespace NAMESPACE = Namespace.getNamespace("inv",
            NAMESPACE_URI);

    //~ Instance fields --------------------------------------------------------

    private BankInformation bankInformation;
    private List dates = new ArrayList();
    private List races = new ArrayList();
    private List schedule = new ArrayList();
    private String description;
    private String host;
    private String location;
    private String name;
    private String remarks;
    private String type;
    private String uid;
    private Contact address;
    private java.util.Date deadline;
    private Contact drawingAddress;
    private java.util.Date drawingDate;

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param bi
     */
    public void setBankInformation(BankInformation bi)
    {
        bankInformation = bi;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public BankInformation getBankInformation()
    {
        return bankInformation;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public List getDates()
    {
        return dates;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setDescription(String string)
    {
        description = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    public void setAddress(Contact contact) {
    	this.address = contact;
    }
    
    public Contact getAddress() {
    	return address;
    }
    
    public void setDeadline(java.util.Date deadline) {
    	this.deadline = deadline;
    }
    
    public java.util.Date getDeadline() {
    	return deadline;
    }
    
    public void setDrawingAddress(Contact contact) {
    	this.drawingAddress = contact;
    }
    
    public Contact getDrawingAddress() {
    	return address;
    }
    
    public void setDrawingDate(java.util.Date date) {
    	this.drawingDate = date;
    }
    
    public java.util.Date getDrawingDate() {
    	return drawingDate;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Element getElement()
    {
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
        el.addContent(new Element("description", NAMESPACE).setText(
                this.description));

        // type
        el.addContent(new Element("type", NAMESPACE).setText(this.type));

        // host
        el.addContent(new Element("host", NAMESPACE).setText(this.host));

        // location
        el.addContent(new Element("location", NAMESPACE).setText(this.location));

        // bankinfo
        if (bankInformation != null) {
            el.addContent(bankInformation.getElement());
        }

        // deadline
        if (deadline != null) {
        	el.addContent(new Element("deadline", NAMESPACE).setText(deadline.toString()));
        }

        if (address != null) {
        	el.addContent(new Element("address", NAMESPACE).addContent(address.getElement()));
        }
        
        // drawing
        if (drawingDate != null) {
        	el.addContent(new Element("drawing", NAMESPACE).setText(drawingDate.toString()));
        }

        if (drawingAddress != null) {
        	el.addContent(new Element("drawing-address", NAMESPACE).addContent(drawingAddress.getElement()));
        }

        // races
        Element races = new Element("races", NAMESPACE);

        for (int i = 0; i < this.races.size(); i++) {
            races.addContent(((Race) this.races.get(i)).getElement());
        }

        el.addContent(races);

        return el;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setHost(String string)
    {
        host = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getHost()
    {
        return host;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setLocation(String string)
    {
        location = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setName(String string)
    {
        name = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * DOCUMENT ME!
     *
     * @param list
     */
    public void setRaces(List list)
    {
        races = list;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public List getRaces()
    {
        return races;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setRemarks(String string)
    {
        remarks = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getRemarks()
    {
        return remarks;
    }

    /**
     * DOCUMENT ME!
     *
     * @param list
     */
    public void setSchedule(List list)
    {
        schedule = list;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public List getSchedule()
    {
        return schedule;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setType(String string)
    {
        type = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getType()
    {
        return type;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getUID()
    {
        return (uid != null) ? uid
                             : Long.toHexString(new java.util.Date().getTime());
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setUid(String string)
    {
        uid = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getUid()
    {
        return uid;
    }

    /**
     * DOCUMENT ME!
     *
     * @param date
     */
    public void addDate(Date date)
    {
        dates.add(date);
    }

    /**
     * DOCUMENT ME!
     *
     * @param race
     */
    public void addRace(Race race)
    {
        races.add(race);
    }

    /**
     * DOCUMENT ME!
     *
     * @param uid DOCUMENT ME!
     */
    protected void setUID(String uid)
    {
        this.uid = uid;
    }
}
