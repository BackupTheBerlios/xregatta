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
package xregatta.common.constraint;

import org.jdom.Element;

/**
 * @author Tammo
 */
public class AgeGroupConstraint extends AbstractConstraint {
	public static final String ID = "age-group";
	
	private int minAge;
	private int maxAge;
	
	public AgeGroupConstraint() {
		super();
	}
	
	public AgeGroupConstraint(Element element) {
		super(element);
		minAge = Integer.parseInt(element.getAttributeValue("min-age"));
		maxAge = Integer.parseInt(element.getAttributeValue("max-age"));
	}
	
	/**
	 * @see xregatta.common.constraint.AbstractConstraint#getDescription()
	 */
	public String getDescription() {
		return "Altersklasse (min, max)";
	}

	/**
	 * @see xregatta.common.constraint.AbstractConstraint#getId()
	 */
	public String getId() {
		return ID;
	}
	
	public Element getElement() {
		return super.getElement().setAttribute("min-age", Integer.toString(minAge))
			.setAttribute("max-age", Integer.toString(maxAge));
	}
	
	/**
	 * @return Returns the maxAge.
	 */
	public int getMaxAge() {
		return maxAge;
	}
	/**
	 * @param maxAge The maxAge to set.
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	/**
	 * @return Returns the minAge.
	 */
	public int getMinAge() {
		return minAge;
	}
	/**
	 * @param minAge The minAge to set.
	 */
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
}
