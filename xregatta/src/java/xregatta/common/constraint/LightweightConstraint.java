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


/**
 * @author Tammo van Lessen
 */
public class LightweightConstraint extends AbstractConstraint {

	public static final String ID = "lightweight";
	
	/**
	 * @see xregatta.common.constraint.AbstractConstraint#getDescription()
	 */
	public String getDescription() {
		return "Leichtgewichtsrennen (true, false)";
	}

	/**
	 * @see xregatta.common.constraint.AbstractConstraint#getId()
	 */
	public String getId() {
		return ID;
	}
}