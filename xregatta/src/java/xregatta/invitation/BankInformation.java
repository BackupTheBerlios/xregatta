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


/**
 * BankInformation
 *
 * @author Tammo van Lessen
 * @version $Id: BankInformation.java,v 1.3 2003/07/26 00:47:13 vanto Exp $
 */
public class BankInformation
{
    //~ Instance fields --------------------------------------------------------

    private String account;
    private String bankCode;
    private String bankName;
    private String owner;
    private String purpose;

    //~ Methods ----------------------------------------------------------------

    /**
     * Sets account number
     *
     * @param string account number
     */
    public void setAccount(String string)
    {
        account = string;
    }

    /**
     * Returns account number
     *
     * @return account number
     */
    public String getAccount()
    {
        return account;
    }

    /**
     * Sets account owner
     *
     * @param string account owner
     */
    public void setAccountOwner(String string)
    {
        owner = string;
    }

    /**
     * Returns account owner
     *
     * @return account owner
     */
    public String getAccountOwner()
    {
        return owner;
    }

    /**
     * Sets bank code
     *
     * @param string bank code
     */
    public void setBankCode(String string)
    {
        bankCode = string;
    }

    /**
     * Returns bank code
     *
     * @return bank code
     */
    public String getBankCode()
    {
        return bankCode;
    }

    /**
     * Sets bank name
     *
     * @param string bank name
     */
    public void setBankName(String string)
    {
        bankName = string;
    }

    /**
     * Returns bank name
     *
     * @return bank name
     */
    public String getBankName()
    {
        return bankName;
    }

    /**
     * Returns a jdom element representing this object's content
     *
     * @return jdom element
     */
    public Element getElement()
    {
        Element el = new Element("bankinfo", Invitation.NAMESPACE);

        // name
        el.addContent(new Element("account-owner", Invitation.NAMESPACE).setText(
                this.owner));

        // account
        el.addContent(new Element("account", Invitation.NAMESPACE).setText(
                this.account));

        // bankName
        el.addContent(new Element("bank-name", Invitation.NAMESPACE).setText(
                this.bankName));

        // bankCode
        el.addContent(new Element("bank-code", Invitation.NAMESPACE).setText(
                this.bankCode));

        // purpose
        el.addContent(new Element("purpose", Invitation.NAMESPACE).setText(
                this.purpose));

        return el;
    }

    /**
     * Sets transferal purpose
     *
     * @param string purpose
     */
    public void setPurpose(String string)
    {
        purpose = string;
    }

    /**
     * Returns transferal purpose
     *
     * @return purpose
     */
    public String getPurpose()
    {
        return purpose;
    }
}
