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
 * @version $Id: Contact.java,v 1.3 2003/07/26 00:47:13 vanto Exp $
 */
public class Contact
{
    //~ Static fields/initializers ---------------------------------------------

    protected static final Namespace VCARD_NS = Namespace.getNamespace("vcard",
            "http://xregatta.berlios.de/vcard");

    //~ Instance fields --------------------------------------------------------

    private Date birthday;
    private Location home;
    private Location work;
    private String desc;
    private String email;
    private String family;
    private String fullname;
    private String gender;
    private String given;
    private String licenseid;
    private String middle;
    private String nickname;
    private String orgname;
    private String orgunit;
    private String role;
    private String title;
    private String url;
    private String xregid;

    //~ Methods ----------------------------------------------------------------

    /**
     * Sets Birthday
     *
     * @param date birthday
     */
    public void setBirthday(Date date)
    {
        birthday = date;
    }

    /**
     * Returns Birthday
     *
     * @return birthday
     */
    public Date getBirthday()
    {
        return birthday;
    }

    /**
     * Sets Description
     *
     * @param string description
     */
    public void setDesc(String string)
    {
        desc = string;
    }

    /**
     * Returns description
     *
     * @return description
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * Returns jdom element representing this objects' content
     *
     * @return jdom element
     */
    public Element getElement()
    {
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

        if (!u.getChildren().isEmpty()) {
            el.addContent(u);
        }

        u = new Element("org", VCARD_NS);
        add(u, "orgname", orgname);
        add(u, "orgunit", orgunit);

        if (!u.getChildren().isEmpty()) {
            el.addContent(u);
        }

        if (work != null) {
            addLocation(el, work, "work");
        }

        if (home != null) {
            addLocation(el, home, "home");
        }

        return el;
    }

    /**
     * Sets email address
     *
     * @param string email address
     */
    public void setEmail(String string)
    {
        email = string;
    }

    /**
     * Returns email address
     *
     * @return email address
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets family name
     *
     * @param string family name
     */
    public void setFamily(String string)
    {
        family = string;
    }

    /**
     * Returns family name
     *
     * @return family name
     */
    public String getFamily()
    {
        return family;
    }

    /**
     * Sets fullname
     *
     * @param string fullname
     */
    public void setFullname(String string)
    {
        fullname = string;
    }

    /**
     * Returns fullname
     *
     * @return fullname
     */
    public String getFullname()
    {
        return fullname;
    }

    /**
     * Sets gender
     *
     * @param string gender
     */
    public void setGender(String string)
    {
        gender = string;
    }

    /**
     * Returns gender
     *
     * @return
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * Sets given name
     *
     * @param string given name
     */
    public void setGiven(String string)
    {
        given = string;
    }

    /**
     * Returns given name
     *
     * @return
     */
    public String getGiven()
    {
        return given;
    }

    /**
     * Sets home location
     *
     * @param location home location
     */
    public void setHome(Location location)
    {
        home = location;
    }

    /**
     * Returns home location
     *
     * @return home location
     */
    public Location getHome()
    {
        return home;
    }

    /**
     * Sets drv license ID
     *
     * @param string license id
     */
    public void setLicenseid(String string)
    {
        licenseid = string;
    }

    /**
     * Returns drv license id
     *
     * @return
     */
    public String getLicenseid()
    {
        return licenseid;
    }

    /**
     * Sets middle name
     *
     * @param string middle name
     */
    public void setMiddle(String string)
    {
        middle = string;
    }

    /**
     * Returns middle name
     *
     * @return middle name
     */
    public String getMiddle()
    {
        return middle;
    }

    /**
     * Sets nickname
     *
     * @param string nickname
     */
    public void setNickname(String string)
    {
        nickname = string;
    }

    /**
     * Returns nickname
     *
     * @return nickname
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * Sets organisations name
     *
     * @param string org name
     */
    public void setOrgname(String string)
    {
        orgname = string;
    }

    /**
     * Returns organisations name
     *
     * @return org name
     */
    public String getOrgname()
    {
        return orgname;
    }

    /**
     * Sets organisation unit
     *
     * @param string unit
     */
    public void setOrgunit(String string)
    {
        orgunit = string;
    }

    /**
     * Returns organisation unit
     *
     * @return unit
     */
    public String getOrgunit()
    {
        return orgunit;
    }

    /**
     * Sets role
     *
     * @param string role
     */
    public void setRole(String string)
    {
        role = string;
    }

    /**
     * Returns role
     *
     * @return role
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Sets title
     *
     * @param string title
     */
    public void setTitle(String string)
    {
        title = string;
    }

    /**
     * Returns title
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets url
     *
     * @param string url
     */
    public void setUrl(String string)
    {
        url = string;
    }

    /**
     * Returns url
     *
     * @return
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Sets working location
     *
     * @param location worklocation
     */
    public void setWork(Location location)
    {
        work = location;
    }

    /**
     * Returns working location
     *
     * @return location
     */
    public Location getWork()
    {
        return work;
    }

    /**
     * Sets global xregatta id
     *
     * @param string xregatta id
     */
    public void setXregid(String string)
    {
        xregid = string;
    }

    /**
     * Returns global xregatta id
     *
     * @return xregatta id
     */
    public String getXregid()
    {
        return xregid;
    }

    /**
     * Creates an 'elname' element with 'value' as text if both values are  not
     * null and adds this element to el
     *
     * @param el Element to add to
     * @param elname Element to create and and to el
     * @param value value of Element
     */
    protected void add(Element el, String elname, String value)
    {
        if ((el != null) && (value != null) && !value.equals("")) {
            el.addContent(new Element(elname, VCARD_NS).setText(value));
        }
    }

    /**
     * Renders a location object to a jdom element and adds it to 'el'
     *
     * @param el Element
     * @param loc Location
     * @param name Name of location
     */
    protected void addLocation(Element el, Location loc, String name)
    {
        if (loc != null) {
            Element u = new Element("adr", VCARD_NS);
            add(u, "extadd", loc.extadd);
            add(u, "street", loc.street);
            add(u, "locality", loc.locality);
            add(u, "region", loc.region);
            add(u, "pcode", loc.pcode);
            add(u, "ctry", loc.country);

            if (!u.getChildren().isEmpty()) {
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

    //~ Inner Classes ----------------------------------------------------------

    /**
     * Location
     */
    public class Location
    {
        public String country;
        public String extadd;
        public String fax;
        public String locality;
        public String mobile;
        public String pcode;
        public String phone;
        public String region;
        public String street;
    }
}
