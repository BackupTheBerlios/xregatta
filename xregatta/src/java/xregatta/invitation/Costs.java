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

import org.jdom.Element;

import xregatta.util.IdBroker;


/**
 * Costs
 *
 * @author Tammo van Lessen
 * @version $Id: Costs.java,v 1.3 2003/07/26 00:47:13 vanto Exp $
 */
public class Costs
{
    //~ Static fields/initializers ---------------------------------------------

    public static final String BOWNUMERBS = "bownumbers";

    //~ Instance fields --------------------------------------------------------

    private String currency = null;
    private String purpose = null;
    private float entryFee = -1;
    private float fine = -1;
    private float lateFee = -1;
    private float costs = -1;

    //~ Methods ----------------------------------------------------------------

    /**
     * Sets the entry fee
     *
     * @param f entry fee
     */
    public void setEntryFee(float f)
    {
        entryFee = f;
    }

    /**
     * Returns the entry fee
     *
     * @return entry fee
     */
    public float getEntryFee()
    {
        return entryFee;
    }

    /**
     * Sets the currency
     *
     * @param string currency
     */
    public void setCurrency(String string)
    {
        currency = string;
    }

    /**
     * Returns the currency
     *
     * @return currency
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * Returns a jdom element representing this object's content
     *
     * @return jdom element
     */
    public Element getElement()
    {
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
			// exit
            return el;
        }

        if (currency != null) {
            el.setAttribute("currency", currency);
        }

		if ((purpose != null) || (costs != -1)) {
			el.addContent(new Element("purpose", Invitation.NAMESPACE).setText(purpose));
			el.addContent(new Element("costs", Invitation.NAMESPACE).setText(""+costs));
			// exit
			return el;
		}
		
        if (getEntryFee() != -1) {
            el.addContent(new Element("entry-fee", Invitation.NAMESPACE).setText("" +
                    getEntryFee()));
        }

        if (getLateFee() != -1) {
            el.addContent(new Element("late-fee", Invitation.NAMESPACE).setText(
                    "" + getLateFee()));
        }

        if (getFine() != -1) {
            el.addContent(new Element("fine", Invitation.NAMESPACE).setText("" +
                    getFine()));
        }

        return el;
    }

    /**
     * Sets fine (Reuegeld)
     *
     * @param f
     */
    public void setFine(float f)
    {
        fine = f;
    }

    /**
     * Returns fine (Reuegeld)
     *
     * @return
     */
    public float getFine()
    {
        return fine;
    }

    /**
     * Sets the late fee (Nachmeldegeld)
     *
     * @param f late fee
     */
    public void setLateFee(float f)
    {
        lateFee = f;
    }

    /**
     * Returns the late fee (Nachmeldegeld)
     *
     * @return late fee
     */
    public float getLateFee()
    {
        return lateFee;
    }

    /**
     * Sets the costs for a purpose
     * IF THIS PROPERTY IS SET, ONLY THESE 2 VALUES GETS RENDERED TO XML
     *
     * @param p purpose
     * @param c costs
     */
    public void setPurposeCosts(String p, float c)
    {
        purpose = p;
        costs = c;
    }

    /**
     * Returns the purpose
     *
     * @return purpose
     */
    public String getPurpose()
    {
        return purpose;
    }

	/**
	 * Returns the costs
	 *
	 * @return costs
	 */
	public float getCosts()
	{
		return costs;
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Costs)) {
            return false;
        }

        String thisS = currency + entryFee + fine + lateFee + purpose +costs;
        Costs o = (Costs) obj;
        String otherS = o.getCurrency() + o.getEntryFee() + o.getFine() +
            o.getLateFee() + getPurpose() + getCosts();

        return thisS.equals(otherS);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return (currency + entryFee + fine + lateFee + purpose + costs).hashCode();
    }
}
