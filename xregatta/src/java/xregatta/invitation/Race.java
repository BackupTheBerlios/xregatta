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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.jdom.Comment;
import org.jdom.Element;

import xregatta.common.constraint.AbstractConstraint;
import xregatta.common.constraint.AgeGroupConstraint;
import xregatta.common.constraint.LevelConstraint;
import xregatta.common.constraint.LightweightConstraint;
import xregatta.util.IdBroker;


/**
 * Race Represents a race in the enclosing invitation
 *
 * @author Tammo van Lessen
 * @version $Id: Race.java,v 1.5 2004/04/22 22:51:54 vanto Exp $
 */
public class Race
{
    //~ Static fields/initializers ---------------------------------------------

	public static final int I = 1;
    public static final int II = 2;
    public static final int III = 3;
    public static final String LIGHTWEIGHT_RULE = "lightweight";
    public static final String METER = "meter";

    //~ Instance fields --------------------------------------------------------

    private Costs costs;
    private Date date;
	//private List groups = new ArrayList();
    private SortedSet constraints = new TreeSet(new ConstraintComparator());
    private String distanceUnit = METER;
    private String gender;
    private String id;
    private String longIdentifier;
    private String remarks;
    private String shortIdentifier = "";
    private float distance;

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns array of classes (Leistungsklassen)
     *
     * @return class
     */
    public int[] getLevels()
    {
        Set l = findConstraintsById(LevelConstraint.ID, LevelConstraint.NS);
    	int[] array = new int[l.size()];

    	Iterator it = l.iterator();
    	for (int i = 0; it.hasNext(); i++) {
            array[i] = Integer.parseInt(((AbstractConstraint)it.next()).getValue());
        }

        return array;
    }

    /**
     * Sets pricing informations
     *
     * @param costs costs
     */
    public void setCosts(Costs costs)
    {
        this.costs = costs;
    }

    /**
     * Returns pricing informations
     *
     * @return costs
     */
    public Costs getCosts()
    {
        return costs;
    }

    /**
     * Sets date
     *
     * @param date
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Returns date
     *
     * @return
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets distance
     *
     * @param f distance
     */
    public void setDistance(float f)
    {
        distance = f;
    }

    /**
     * Returns racing distince
     *
     * @return distance
     */
    public float getDistance()
    {
        return distance;
    }

    /**
     * Sets distance unit
     *
     * @param unit distance unit
     */
    public void setDistanceUnit(String unit)
    {
        distanceUnit = unit;
    }

    /**
     * Returns distance unit
     *
     * @return distance unit
     */
    public String getDistanceUnit()
    {
        return distanceUnit;
    }

    /**
     * Sets race id
     *
     * @param id race id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns race id
     *
     * @return race id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Is this race lightweight or not?
     *
     * @param lgw true if lightweight
     */
    public void setLightweight(boolean lgw)
    {
    	Iterator it = constraints.iterator();
    	while (it.hasNext()) {
    		AbstractConstraint constr = (AbstractConstraint)it.next();
    		if ((constr.getId().equals(LightweightConstraint.ID) 
    				&& (constr.getNamespace().equals(LightweightConstraint.NS)))) {
    			it.remove();
    		}
    	}
    	
    	if (lgw) {
    		AbstractConstraint constr = new LightweightConstraint();
    		constr.setValue("true");
    		addConstraint(constr);
    	}
    }

    /**
     * Is this race lightweight or not?
     *
     * @return lgw?
     */
    public boolean isLightweight()
    {
    	Set l = findConstraintsById(LightweightConstraint.ID, LightweightConstraint.NS);
		return (l != null);
    }

    /**
     * Sets long race identifier
     *
     * @param identifier long identifier
     */
    public void setLongIdentifier(String identifier)
    {
        longIdentifier = identifier;
    }

    /**
     * Returns long race description. If no long identifier is set
     * this method renders one out of the shortidentifier
     *
     * @return
     */
    public String getLongIdentifier()
    {
        return (longIdentifier != null) ? longIdentifier
                                        : renderName(shortIdentifier);
    }

    /**
     * Returns race properties
     *
     * @return race properties
     */
    public Set getConstraints()
    {
        return constraints;
    }

    /**
     * Sets race remarks
     *
     * @param string remarks
     */
    public void setRemarks(String string)
    {
        remarks = string;
    }

    /**
     * Returns race remarks
     *
     * @return race remarks
     */
    public String getRemarks()
    {
        return remarks;
    }

    /**
     * Sets short identifier
     *
     * @param identifier short identifier
     */
    public void setShortIdentifier(String identifier)
    {
        shortIdentifier = identifier;
    }

    /**
     * Returns short identifier
     *
     * @return
     */
    public String getShortIdentifier()
    {
        return shortIdentifier;
    }

    /**
     * Add race category (Leistungsklasse)
     *
     * @param clazz
     */
    public void addLevel(int level)
    {
		AbstractConstraint constr = new LevelConstraint();
		constr.setValue(Integer.toString(level));

		if (!constraints.contains(constr)) {
			addConstraint(constr);	
		}
    }

	/**
	 * Remove race level
	 * 
	 * @param clazz
	 */
	public void removeLevel(int level) {
		AbstractConstraint constr = new LevelConstraint();
		constr.setValue(Integer.toString(level));
		
		// this works because of AbstractConstraint.equals()/hashCode()
		if (constraints.contains(constr)) {
			constraints.remove(constr);	
		}
	}
	
	public void setAgeGroup(int min, int max) {
    	Iterator it = constraints.iterator();
    	while (it.hasNext()) {
    		AbstractConstraint constr = (AbstractConstraint)it.next();
    		if ((constr.getId().equals(AgeGroupConstraint.ID) 
    				&& (constr.getNamespace().equals(LightweightConstraint.NS)))) {
    			it.remove();
    		}
    	}
		
    	AgeGroupConstraint constr = new AgeGroupConstraint();
		constr.setMinAge(min);
		constr.setMaxAge(max);
		addConstraint(constr);	
		
	}
	
	public int getMinAge() {
		AgeGroupConstraint constr = (AgeGroupConstraint)findFirstConstraintsById(AgeGroupConstraint.ID, AgeGroupConstraint.NS);
		if (constr == null) {
			return -1;
		}
		else {
			return constr.getMinAge();
		}
	}
	
	public int getMaxAge() {
		AgeGroupConstraint constr = (AgeGroupConstraint)findFirstConstraintsById(AgeGroupConstraint.ID, AgeGroupConstraint.NS);
		if (constr == null) {
			return -1;
		}
		else {
			return constr.getMaxAge();
		}
	}

	/**
     * Add race constraint
     *
     * @param property race property
     */
    public void addConstraint(AbstractConstraint constraint)
    {
        constraints.add(constraint);
    }

    /**
     * Parses short identifier and renders a long out of it TODO: Move to
     * XRegattaUtils
     *
     * @param shortIdentifier short identifier
     *
     * @return
     */
    public static String renderName(String shortIdentifier)
    {
        StringBuffer name = new StringBuffer();
        StringTokenizer st = new StringTokenizer(shortIdentifier, " ");

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

            if (st.hasMoreTokens()) {
                name.append(" ");
            }
        }

        return name.toString();
    }

    /**
     * Returns a JDOM subtree, representing this object's content
     *
     * @return jdom element
     */
    public Element getElement()
    {
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
        el.addContent(new Element("long-identifier", Invitation.NAMESPACE).setText(
                getLongIdentifier()));

        // shotcut
        el.addContent(new Element("short-identifier", Invitation.NAMESPACE).setText(
                this.shortIdentifier));

        // date
        if (date != null) {
            el.addContent(date.getElement());
        }

        // gender
        if (gender != null) {
            el.addContent(new Element("gender", Invitation.NAMESPACE).setText(
                    this.gender));
        }

        // distance
        el.addContent(new Element("distance", Invitation.NAMESPACE).setText("" +
                this.distance).setAttribute("unit", distanceUnit));

        // classes
        /*Element classes = new Element("classes", Invitation.NAMESPACE);

        for (int i = 0; i < getCategories().length; i++) {
            classes.addContent(new Element("class", Invitation.NAMESPACE).setText(
                    "" + getCategories()[i]));
        }

        el.addContent(classes);*/

        // contraints
        Element constraints = new Element("constraints", Invitation.NAMESPACE);
        Iterator constraintsIt = getConstraints().iterator();

        while (constraintsIt.hasNext()) {
            AbstractConstraint constr = (AbstractConstraint) constraintsIt.next(); 
        	constraints.addContent(new Comment(constr.getDescription()));
            constraints.addContent(constr.getElement());
        }

        el.addContent(constraints);

/*		// age groups
		Element groups = new Element("age-groups", Invitation.NAMESPACE);
		for (int i = 0; i < getAgeGroups().length; i++) {
			groups.addContent(new Element("age-group", Invitation.NAMESPACE).setText(
					"" + getAgeGroups()[i]));
		}
		el.addContent(groups);*/

        // costs
        if (costs != null) {
            el.addContent(costs.getElement());
        }

        // remarks
        if (remarks != null) {
            el.addContent(new Element("remarks", Invitation.NAMESPACE).setText(
                    remarks));
        }

        return el;
    }

    /**
     * Sets race gender
     *
     * @param string gender
     */
    public void setGender(String string)
    {
        gender = string;
    }

    /**
     * Returns race gender
     *
     * @return gender
     */
    public String getGender()
    {
        return gender;
    }
    
    /**
     * Finds constraints by id and namespace.
     * Returns null if nothing was found.
     * 
     * @param id
     * @param namespace
     * @return
     */
    private Set findConstraintsById(String id, String namespace) {
    	Set result = new HashSet();
    	
    	Iterator it = constraints.iterator();
    	while (it.hasNext()) {
    		AbstractConstraint constr = (AbstractConstraint)it.next();
    		if ((constr.getId() == id) && (constr.getNamespace() == namespace)) {
    			result.add(constr);
    		}
    	}
    	
    	return (result.size() != 0) ? result : null;
    }

    /**
     * Finds the first constraint by id and namespace.
     * Returns null if nothing was found.
     * 
     * @param id
     * @param namespace
     * @return
     */
    private AbstractConstraint findFirstConstraintsById(String id, String namespace) {
    	Iterator it = constraints.iterator();
    	while (it.hasNext()) {
    		AbstractConstraint constr = (AbstractConstraint)it.next();
    		if ((constr.getId() == id) && (constr.getNamespace() == namespace)) {
    			return constr;
    		}
    	}
    	return null;
    }

    /**
	 */
	public class ConstraintComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			AbstractConstraint c1 = (AbstractConstraint)o1;
			AbstractConstraint c2 = (AbstractConstraint)o2;
			String s1 = c1.getNamespace() + c1.getId() + c1.getValue();
			String s2 = c2.getNamespace() + c2.getId() + c2.getValue();
			return s1.compareTo(s2);
		}
	}

}
