/* 
 * xregatta - electronic regatta standards
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

import xregatta.util.IdBroker;

/**
 * Costs
 * 
 * @author Tammo van Lessen
 * @version $Id: Costs.java,v 1.2 2003/07/25 20:06:38 vanto Exp $
 */
public class Costs {
	public static final String BOWNUMERBS = "bownumbers";
	
	private float costs = -1;
	private float fine = -1;
	private float lateFee = -1;
	private String currency = null;
	private String purpose = null;
	
	/**
	 * @return
	 */
	public float getCosts() {
		return costs;
	}

	public void setPurpose(String p) {
		purpose = p;
	}
	
	public String getPurpose() {
		return purpose;
	}
	
	/**
	 * @return
	 */
	public float getFine() {
		return fine;
	}

	/**
	 * @return
	 */
	public float getLateFee() {
		return lateFee;
	}

	/**
	 * @param f
	 */
	public void setCosts(float f) {
		costs = f;
	}

	/**
	 * @param f
	 */
	public void setFine(float f) {
		fine = f;
	}

	/**
	 * @param f
	 */
	public void setLateFee(float f) {
		lateFee = f;
	}
	
	public Element getXMLTree() {
		Element el = new Element("costs", Invitation.NAMESPACE);
		
		String objId = IdBroker.getObjectId(this);
		if (objId == null) {
			if (purpose != null) {
				objId = purpose;
			} else {
				objId = IdBroker.getNextId(this);
			}
			IdBroker.registerObject(this, objId);
			el.setAttribute("id", objId);
		} else {
			el.setAttribute("refid", objId);
			return el;
		}
		if (currency != null) {
			el.setAttribute("currency", currency);
		}

		if (getCosts() != -1) {
			el.addContent(new Element("entry", Invitation.NAMESPACE).setText(""+getCosts()));
		}
		if (getLateFee() != -1) {
			el.addContent(new Element("late-fee", Invitation.NAMESPACE).setText(""+getLateFee()));		}
		if (getFine() != -1) {
			el.addContent(new Element("fine", Invitation.NAMESPACE).setText(""+getFine()));		}
		return el;
	}

	/**
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param string
	 */
	public void setCurrency(String string) {
		currency = string;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (currency + costs + fine + lateFee).hashCode();
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Costs)) return false;
		String thisS = currency + costs + fine + lateFee;
		Costs o = (Costs)obj;
		String otherS = o.getCurrency() + o.getCosts() +o.getFine() + o.getLateFee(); 
		return thisS.equals(otherS);
	}

}
