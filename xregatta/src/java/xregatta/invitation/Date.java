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

import org.jdom.Element;

/**
 * Date
 * 
 * @author Tammo van Lessen
 * @version $Id: Date.java,v 1.1 2003/07/25 19:54:35 vanto Exp $
 */
public class Date {

	private java.util.Date date;
	private int type;
	
	/**
	 * @return
	 */
	public Element getXMLTree() {
		return new Element("date");
	}

}
