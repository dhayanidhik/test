package com.payment.model;

public class MolPayRequest {

	private String amount;
	private String orderid;
	
	private String bill_name;
	private String bill_email;
	private String bill_mobile;
	private String bill_desc;
	private String country;
	private String vcode;
	private String currency;
	private String returnurl;
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the bill_name
	 */
	public String getBill_name() {
		return bill_name;
	}
	/**
	 * @param bill_name the bill_name to set
	 */
	public void setBill_name(String bill_name) {
		this.bill_name = bill_name;
	}
	/**
	 * @return the bill_email
	 */
	public String getBill_email() {
		return bill_email;
	}
	/**
	 * @param bill_email the bill_email to set
	 */
	public void setBill_email(String bill_email) {
		this.bill_email = bill_email;
	}
	/**
	 * @return the bill_mobile
	 */
	public String getBill_mobile() {
		return bill_mobile;
	}
	/**
	 * @param bill_mobile the bill_mobile to set
	 */
	public void setBill_mobile(String bill_mobile) {
		this.bill_mobile = bill_mobile;
	}
	/**
	 * @return the bill_desc
	 */
	public String getBill_desc() {
		return bill_desc;
	}
	/**
	 * @param bill_desc the bill_desc to set
	 */
	public void setBill_desc(String bill_desc) {
		this.bill_desc = bill_desc;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the vcode
	 */
	public String getVcode() {
		return vcode;
	}
	/**
	 * @param vcode the vcode to set
	 */
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
	/**
	 * @return the returnurl
	 */
	public String getReturnurl() {
		return returnurl;
	}
	/**
	 * @param returnurl the returnurl to set
	 */
	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}
	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}
	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MolPayRequest [amount=" + amount + ", orderid=" + orderid
				+ ", bill_name=" + bill_name + ", bill_email=" + bill_email
				+ ", bill_mobile=" + bill_mobile + ", bill_desc=" + bill_desc
				+ ", country=" + country + ", vcode=" + vcode + ", currency="
				+ currency + ", returnurl=" + returnurl + "]";
	}

}
