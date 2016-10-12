package com.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource(value = "classpath:molpay.properties")
@Component
public class MolPayProperties {

	@Value("${payment.merchantID}")
	private String merchantId;

	@Value("${payment.url.molPay}")
	private String molPayUrl;

	@Value("${payment.returnUrl}")
	private String returnUrl;

	@Value("${payment.verifykey}")
	private String verifyKey;

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the molPayUrl
	 */
	public String getMolPayUrl() {
		return molPayUrl;
	}

	/**
	 * @param molPayUrl
	 *            the molPayUrl to set
	 */
	public void setMolPayUrl(String molPayUrl) {
		this.molPayUrl = molPayUrl;
	}

	/**
	 * @return the verifyKey
	 */
	public String getVerifyKey() {
		return verifyKey;
	}

	/**
	 * @param verifyKey
	 *            the verifyKey to set
	 */
	public void setVerifyKey(String verifyKey) {
		this.verifyKey = verifyKey;
	}

	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * @param returnUrl
	 *            the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
