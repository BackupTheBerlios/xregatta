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

/**
 * BankInformation
 * 
 * @author Tammo van Lessen
 * @version $Id: BankInformation.java,v 1.2 2003/07/25 20:06:38 vanto Exp $
 */
public class BankInformation {
	private String owner;
	private String account;	
	private String bankName;
	private String bankCode;
	private String purpose;
	/**
	 * @return
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @return
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @return
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @return
	 */
	public String getAccountOwner() {
		return owner;
	}

	/**
	 * @return
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param string
	 */
	public void setAccount(String string) {
		account = string;
	}

	/**
	 * @param string
	 */
	public void setBankCode(String string) {
		bankCode = string;
	}

	/**
	 * @param string
	 */
	public void setBankName(String string) {
		bankName = string;
	}

	/**
	 * @param string
	 */
	public void setAccountOwner(String string) {
		owner = string;
	}

	/**
	 * @param string
	 */
	public void setPurpose(String string) {
		purpose = string;
	}

	public Element getXMLTree() {
		Element el = new Element("bankinfo", Invitation.NAMESPACE);
		// name
		el.addContent(new Element("account-owner", Invitation.NAMESPACE).setText(this.owner));

		// account
		el.addContent(new Element("account", Invitation.NAMESPACE).setText(this.account));		
		
		// bankName
		el.addContent(new Element("bank-name", Invitation.NAMESPACE).setText(this.bankName));		
		
		// bankCode
		el.addContent(new Element("bank-code", Invitation.NAMESPACE).setText(this.bankCode));		
		
		// purpose
		el.addContent(new Element("purpose", Invitation.NAMESPACE).setText(this.purpose));
		return el;
	}
}
