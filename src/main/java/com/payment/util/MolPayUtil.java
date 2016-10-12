package com.payment.util;

import org.apache.commons.lang3.StringUtils;

import com.payment.model.MolPayRequest;

public class MolPayUtil {

	public static final String SUCCESS = "0";
	public static final String FAILURE = "-1";
	public static final String ERROR = "-2";
	public static final String MALAYSIA = "malaysia";
	public static final String MOLPAY_PAYMENT_SUCCESS = "00";
	public static final String MOLPAY_PAYMENT_FAILURE = "11";

	public static String getPaymentChannelMissingParam(MolPayRequest paymentInfo) {

		StringBuffer missingParam = new StringBuffer();
		if (StringUtils.isBlank(paymentInfo.getAmount())) {
			missingParam.append("Amount\n");
		}
		if (StringUtils.isBlank(paymentInfo.getOrderid())) {
			missingParam.append("OrderId\n");
		}
		if (StringUtils.isBlank(paymentInfo.getBill_name())) {
			missingParam.append("Bill Name\n");
		}
		if (StringUtils.isBlank(paymentInfo.getBill_email())) {
			missingParam.append("Bill Email\n");
		}
		if (StringUtils.isBlank(paymentInfo.getBill_mobile())) {
			missingParam.append("Bill Mobile\n");
		}
		if (StringUtils.isBlank(paymentInfo.getBill_desc())) {
			missingParam.append("Bill Description\n");
		}
		if (StringUtils.isBlank(paymentInfo.getCountry())) {
			missingParam.append("Country\n");
		}

		return missingParam.toString();
	}

}
