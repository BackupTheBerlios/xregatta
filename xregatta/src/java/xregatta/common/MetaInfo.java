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

import java.util.Date;

import org.jdom.Element;
import org.jdom.Namespace;

import xregatta.XRegatta;
import xregatta.invitation.Invitation;


/**
 * MetaInfo
 *
 * @author Tammo van Lessen
 * @version $Id: MetaInfo.java,v 1.3 2003/07/26 00:47:13 vanto Exp $
 */
public class MetaInfo
{
    //~ Static fields/initializers ---------------------------------------------

    public static final Namespace DC_NAMESPACE = Namespace.getNamespace("dc",
            "http://purl.org/dc/elements/1.1");

    //~ Instance fields --------------------------------------------------------

    private Date date = new Date();
    private String creator;
    private String description;
    private String publisher;
    private String subject;
    private String title;

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getCreator()
    {
        return creator;
    }

    /**
     * DOCUMENT ME!
     *
     * @param date
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public Date getDate()
    {
        return date;
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

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setPublisher(String string)
    {
        publisher = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setSubject(String string)
    {
        subject = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setTitle(String string)
    {
        title = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * DOCUMENT ME!
     *
     * @param prefix DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Element getElement(String prefix)
    {
        Element el = new Element("meta", Invitation.NAMESPACE);

        el.addContent(new Element("title", DC_NAMESPACE).setText(title));
        el.addContent(new Element("subject", DC_NAMESPACE).setText(subject));
        el.addContent(new Element("description", DC_NAMESPACE).setText(
                description));
        el.addContent(new Element("publisher", DC_NAMESPACE).setText(publisher));
        el.addContent(new Element("date", DC_NAMESPACE).setText(
                date.toGMTString()));
        el.addContent(new Element("creator", DC_NAMESPACE).setText(
                XRegatta.CREATOR));

        return el;
    }
}
