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
package xregatta.invitation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.jdom.Element;

import xregatta.util.IdBroker;

/**
 * Race
 * 
 * @author Tammo van Lessen
 * @version $Id: Race.java,v 1.1 2003/07/25 19:54:35 vanto Exp $
 */
public class Race {

	public static final int I = 1;
	public static final int II = 2;
	public static final int III = 3;
	
	public static final String LIGHTWEIGHT_RULE = "lightweight";
	
	public static final String METER = "meter";
	private Costs costs;
	private String name;
	private String shortcut = "";
	private float distance;
	private String distanceUnit = METER;
	private List classes = new ArrayList();
	private String remarks;
	private Date date;
	private String id;
	private Set rules = new HashSet();
	
	/**
	 * @return
	 */
	public int[] getClasses() {
		int[] array = new int[classes.size()];
		for (int i=0; i<classes.size(); i++) {
			array[i] = ((Integer)classes.get(i)).intValue();
		}
		return array;
	}

	public void setDistanceUnit(String unit) {
		distanceUnit = unit;
	}
	
	public String getDistanceUnit() {
		return distanceUnit;
	}
	
	/**
	 * @return
	 */
	public Costs getCosts() {
		return costs;
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public float getDistance() {
		return distance;
	}


	/**
	 * @return
	 */
	public String getId() {
		return id;
	}


	/**
	 * @return
	 */
	public String getName() {
		return (name!=null)?name:renderName(shortcut);
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
	public Set getRules() {
		return rules;
	}

	/**
	 * @return
	 */
	public String getShortcut() {
		return shortcut;
	}

	/**
	 * @param list
	 */
	public void addClass(int clazz) {
		classes.add(new Integer(clazz));
	}

	/**
	 * @param f
	 */
	public void setCosts(Costs c) {
		costs = c;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param f
	 */
	public void setDistance(float f) {
		distance = f;
	}


	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRemarks(String string) {
		remarks = string;
	}

	/**
	 * @param map
	 */
	public void addRule(String rule) {
		rules.add(rule);
	}

	/**
	 * @param string
	 */
	public void setShortcut(String string) {
		shortcut = string;
	}

	public boolean isLightweight() {
		return rules.contains(LIGHTWEIGHT_RULE);	
	}
	
	public void setLightweight(boolean lgw) {
		if (lgw) {
			rules.add(LIGHTWEIGHT_RULE);
		} else {
			rules.remove(LIGHTWEIGHT_RULE);	
		}
	}
	
	/**
	 * @param shortcut
	 * @return
	 */
	public static String renderName(String shortcut) {
		StringBuffer name = new StringBuffer();
		StringTokenizer st = new StringTokenizer(shortcut, " ");
		
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (tmp.indexOf("JM") != -1) {
				tmp = "Junioren";
			}
			if (tmp.indexOf("JF") != -1) {
				tmp = "Juniorinnen";
			}
			if (tmp.indexOf("SM") != -1) {
				tmp = "Männer";
			}
			if (tmp.indexOf("SF") != -1) {
				tmp = "Frauen";
			}
			if (tmp.indexOf("MM") != -1) {
				tmp = "Masters Männer";
			}
			if (tmp.indexOf("MW") != -1) {
				tmp = "Masters Frauen";
			}
			if (tmp.indexOf("Mäd.") != -1) {
				tmp = "Mädchen";
			}
			if (tmp.indexOf("Jung.") != -1) {
				tmp = "Juungen";
			}
			if (tmp.indexOf("LG") != -1) {
				tmp = "Leichtgewicht";
			}
			if (tmp.indexOf("Lgw") != -1) {
				tmp = "Leichtgewicht";
			}

			if (tmp.indexOf("1x") != -1) {
				tmp = "Einer";
			}
			if (tmp.indexOf("2x") != -1) {
				tmp = "Doppelzweier";
			}
			if (tmp.indexOf("4x") != -1) {
				tmp = "Doppelvierer";
			}
			if (tmp.indexOf("4x+") != -1) {
				tmp = "Doppelvierer mit Steuermann";
			}
			if (tmp.indexOf("2-") != -1) {
				tmp = "Zweier ohne Steuermann";
			}
			if (tmp.indexOf("2+") != -1) {
				tmp = "Zweier mit Steuermann";
			}
			if (tmp.indexOf("4-") != -1) {
				tmp = "Vierer ohne Steuermann";
			}
			if (tmp.indexOf("4+") != -1) {
				tmp = "Vierer mit Steuermann";
			}
			if (tmp.indexOf("2-") != -1) {
				tmp = "Zweier ohne Steuermann";
			}
			if (tmp.indexOf("8+") != -1) {
				tmp = "Achter";
			}



			name.append(tmp);
			if (st.hasMoreTokens()) name.append(" ");			
		}
		return name.toString();
	}
	
	public Element getXMLTree() {
		Element el = new Element("race", Invitation.NAMESPACE);
		String objId = IdBroker.getObjectId(this);
		if (objId == null) {
			objId = IdBroker.getNextId(this);
			IdBroker.registerObject(this, objId);
			el.setAttribute("id", objId);
		} else {
			el.setAttribute("refid", objId);
			return el;
		}
		// id
		el.addContent(new Element("id", Invitation.NAMESPACE).setText(this.id));
		
		// name
		el.addContent(new Element("name", Invitation.NAMESPACE).setText(getName()));
		
		// shotcut
		el.addContent(new Element("shortcut", Invitation.NAMESPACE).setText(this.shortcut));
		
		// date
		if (date != null) {
			el.addContent(date.getXMLTree());	
		}
		
		// distance
		el.addContent(new Element("distance", Invitation.NAMESPACE).setText(""+this.distance)
				.setAttribute("unit", distanceUnit));
				
		// classes
		Element classes = new Element("classes", Invitation.NAMESPACE);
		for (int i = 0; i<getClasses().length; i++) {
			classes.addContent(new Element("class", Invitation.NAMESPACE).setText(""+getClasses()[i]));
		}
		el.addContent(classes);
		
		// rules
		Element rules = new Element("rules", Invitation.NAMESPACE);
		Iterator rulesIt = getRules().iterator();
		while (rulesIt.hasNext()) {
			rules.addContent(new Element("rule", Invitation.NAMESPACE).setText((String) rulesIt.next()));
		}
		el.addContent(rules);

		// costs
		if (costs != null) {
			el.addContent(costs.getXMLTree());
		}

		// remarks
		if (remarks != null) {
			el.addContent(new Element("remarks", Invitation.NAMESPACE).setText(remarks));
		}

		return el;
	}
}
