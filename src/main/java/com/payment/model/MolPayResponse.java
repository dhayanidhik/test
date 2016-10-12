package com.payment.model;

public class MolPayResponse {

	private String amount;
	private String orderid;
	private String tranID;
	private String domain;
	private String status;
	private String error_code;
	private String error_desc;
	private String skey;
	private String currency;
	private String channel;
	private String paydate;
	private String appcode;
	// Addition field from IbookCourt to validate the MolPay response further.
	private String originalAmount;
	private String originalOrderId;
	// Return Url Ack. parameter
	private String treq;
	private String nbcb;

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid
	 *            the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the tranID
	 */
	public String getTranID() {
		return tranID;
	}

	/**
	 * @param tranID
	 *            the tranID to set
	 */
	public void setTranID(String tranID) {
		this.tranID = tranID;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the error_code
	 */
	public String getError_code() {
		return error_code;
	}

	/**
	 * @param error_code
	 *            the error_code to set
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * @return the error_desc
	 */
	public String getError_desc() {
		return error_desc;
	}

	/**
	 * @param error_desc
	 *            the error_desc to set
	 */
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}

	/**
	 * @return the skey
	 */
	public String getSkey() {
		return skey;
	}

	/**
	 * @param skey
	 *            the skey to set
	 */
	public void setSkey(String skey) {
		this.skey = skey;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the paydate
	 */
	public String getPaydate() {
		return paydate;
	}

	/**
	 * @param paydate
	 *            the paydate to set
	 */
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	/**
	 * @return the appcode
	 */
	public String getAppcode() {
		return appcode;
	}

	/**
	 * @param appcode
	 *            the appcode to set
	 */
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	/**
	 * @return the originalAmount
	 */
	public String getOriginalAmount() {
		return originalAmount;
	}

	/**
	 * @param originalAmount
	 *            the originalAmount to set
	 */
	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}

	/**
	 * @return the originalOrderId
	 */
	public String getOriginalOrderId() {
		return originalOrderId;
	}

	/**
	 * @param originalOrderId
	 *            the originalOrderId to set
	 */
	public void setOriginalOrderId(String originalOrderId) {
		this.originalOrderId = originalOrderId;
	}

	/**
	 * @return the treq
	 */
	public String getTreq() {
		return treq;
	}

	/**
	 * @param treq
	 *            the treq to set
	 */
	public void setTreq(String treq) {
		this.treq = treq;
	}

	/**
	 * @return the nbcb
	 */
	public String getNbcb() {
		return nbcb;
	}

	/**
	 * @param nbcb
	 *            the nbcb to set
	 */
	public void setNbcb(String nbcb) {
		this.nbcb = nbcb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MolPayResponse [amount=" + amount + ", orderid=" + orderid
				+ ", tranID=" + tranID + ", domain=" + domain + ", status="
				+ status + ", error_code=" + error_code + ", error_desc="
				+ error_desc + ", skey=" + skey + ", currency=" + currency
				+ ", channel=" + channel + ", paydate=" + paydate
				+ ", appcode=" + appcode + ", originalAmount=" + originalAmount
				+ ", originalOrderId=" + originalOrderId + ", treq=" + treq
				+ ", nbcb=" + nbcb + "]";
	}

}
