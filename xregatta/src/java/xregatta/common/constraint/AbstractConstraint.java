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
public abstract class AbstractConstraint {
	public static final String NS = "http://xregatta.berlios.de/constraints/1.0";
	
	protected String value;
	protected Element element = null;
	
	public AbstractConstraint() {
	}
	
	public AbstractConstraint(Element element) {
		this.element = element;
		value = element.getText();
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public abstract String getDescription();
	public abstract String getId();
	public String getNamespace() {
		return NS;
	}
	
	public Element getElement() {
		if (element == null) {
		  element = new Element(getId(), getNamespace()).setText(value);
		}
		
		return element;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		AbstractConstraint o = (AbstractConstraint)obj;
		return (o.getId() + o.getNamespace() + o.getValue()
				== getId() + getNamespace() + getValue());
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (getId() + getNamespace() + getValue()).hashCode();
	}
}
