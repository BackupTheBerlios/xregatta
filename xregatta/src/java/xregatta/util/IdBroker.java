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
package xregatta.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import xregatta.common.Contact;
import xregatta.invitation.Costs;
import xregatta.invitation.Date;
import xregatta.invitation.Invitation;
import xregatta.invitation.Race;


/**
 * IdBroker
 *
 * @author Tammo van Lessen
 * @version $Id: IdBroker.java,v 1.3 2003/07/26 00:47:13 vanto Exp $
 */
public class IdBroker
{
    //~ Static fields/initializers ---------------------------------------------

    private static Map idMap = new TreeMap();
    private static Map prefixMap = new HashMap();
    private static Map objectIdMap = new HashMap();

    static {
        prefixMap.put(Race.class.getName(), "ra");
        prefixMap.put(Date.class.getName(), "da");
        prefixMap.put(Contact.class.getName(), "con");
        prefixMap.put(Costs.class.getName(), "cost");
        prefixMap.put(Invitation.class.getName(), "reg");
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the current id counter for the given class
     *
     * @param clazz a registered class
     *
     * @return the id
     */
    public static String getCurrentId(Class clazz)
    {
        String classname = clazz.getName();
        String prefix = (String) prefixMap.get(classname);
        Integer id = (Integer) idMap.get(classname);

        if (id == null) {
            return null;
        }

        return prefix + "_" + id;
    }

	/**
	 * Returns the next id counter for the given class/object
	 *
	 * @param o an object
	 *
	 * @return the id
	 */
    public static String getNextId(Object o)
    {
        String classname = o.getClass().getName();
        String prefix = (String) prefixMap.get(classname);
        Integer id = (Integer) idMap.get(classname);

        if (id == null) {
            id = new Integer(1);
        } else {
            id = new Integer(id.intValue() + 1);
        }

        idMap.put(classname, id);

        return prefix + "_" + id;
    }

    /**
     * Returns the registered id of the given object
     *
     * @param o an registered object
     *
     * @return the id
     */
    public static String getObjectId(Object o)
    {
        return (String) objectIdMap.get(o);
    }

    /**
     * Registers an object with the id
     *
     * @param o an object
     * @param id an id
     */
    public static void registerObject(Object o, String id)
    {
        objectIdMap.put(o, id);
    }
}
