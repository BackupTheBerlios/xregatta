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
package xregatta;

import java.io.IOException;
import java.util.GregorianCalendar;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

import xregatta.common.Contact;
import xregatta.common.MetaInfo;
import xregatta.common.Contact.Location;
import xregatta.invitation.BankInformation;
import xregatta.invitation.Costs;
import xregatta.invitation.Date;
import xregatta.invitation.Invitation;
import xregatta.invitation.Race;


/**
 * XRegatta
 */
public class XRegatta
{
    //~ Static fields/initializers ---------------------------------------------

	public static final Namespace NAMESPACE = Namespace.getNamespace("x",
		"http://xregatta.berlios.de/1.0");

    public static final String CREATOR = "xregatta 1.0";

    //~ Methods ----------------------------------------------------------------

    /**
     * Main methos
     *
     * @param args commandline args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        MetaInfo mi = new MetaInfo();
        mi.setPublisher("Tammo van Lessen");
        mi.setDate(new java.util.Date());
        mi.setDescription("Ausschreibung für die Testregatta");
        mi.setSubject("Ausschreibung von mir");
        mi.setTitle("Ausschreibung");

        Race s = new Race();
        s.setId("1");
        s.addClass(Race.I);
        s.addClass(Race.II);
        s.setDate(new Date());
        s.setDistance(2000);
        s.setLightweight(true);
        s.setShortIdentifier("SM 2x A LG");
        s.setRemarks("Wanderpokal");

        Costs c = new Costs();
        c.setEntryFee(12.4f);
        c.setCurrency("EUR");
        c.setFine(30);
        s.setCosts(c);

        Costs c1 = new Costs();
        c1.setEntryFee(12.4f);
        c1.setCurrency("EUR");
        c1.setFine(30);

        Race t = new Race();
        t.setCosts(c1);

        Invitation inv = new Invitation();
        inv.addRace(s);
        inv.addRace(t);

        BankInformation bi = new BankInformation();
        bi.setAccount("123213");
        bi.setAccountOwner("Tammo van Lessen");
        bi.setBankCode("121221");
        bi.setBankName("LBBW");
        bi.setPurpose("regatta");

        inv.setBankInformation(bi);
        inv.setName("SWDM 2003");
        inv.setHost(
            "Mainzer Ruder-Verein von 1878 e.V., LRV Rheinland-Pfalz/Saarbrücken");
        inv.setLocation("Mainz/Acker");
        inv.setType("Spitzensportregatta");

        inv.setDescription("Südwestdeutsche Meisterschaften im Acker");

        XMLOutputter outputter = new XMLOutputter("  ", true);
        outputter.setEncoding("ISO-8859-1");

        Document xregatta = new Document(new Element("xregatta",
                    XRegatta.NAMESPACE));
		Element root = xregatta.getRootElement();
        root.addContent(mi.getElement(CREATOR));
        
        //Element events = new Element("events", XRegatta.NAMESPACE);
        //root.addContent(events);
		
        Element event = new Element("event", XRegatta.NAMESPACE);
        //events.addContent(event);
		root.addContent(event);
		
        event.addContent(inv.getElement());
        
        xregatta.getRootElement().addNamespaceDeclaration(MetaInfo.DC_NAMESPACE);
        outputter.output(xregatta, System.out);

        Contact con = new Contact();

        con.setBirthday(new GregorianCalendar(1979, 10, 30).getTime());
        con.setDesc("ich bins");
        con.setEmail("tvanlessen@taval.de");
        con.setFullname("Tammo van Lessen");
        con.setFamily("van Lessen");
        con.setGender("male");

        Location loc = con.new Location();
        loc.country = "Germany";
        loc.street = "Schlösslestr 6";
        loc.phone = "568765";
        con.setWork(loc);

        //outputter.output(con.getXMLTree(), System.out);
    }
}
