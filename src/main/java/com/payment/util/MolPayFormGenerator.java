package com.payment.util;

import org.springframework.util.DigestUtils;

import com.payment.model.MolPayRequest;

public class MolPayFormGenerator {

	/**
	 * @param args
	 */
	public static void xmain(String[] args) {

		String action = "https://www.onlinepayment.com.my/MOLPay/pay/ibookcourt/index.php";
		String amount = "108.10";
		String orderid = "16882";
		String bill_name = "Vimal Raj";
		String bill_email = "sgvwork2016@gmail.com";
		String bill_mobile = "919094300840";
		String bill_desc = "Booking for 1 hours";
		String country = "MY";
		String vcode = "5d89f0b1794a48ec30efd7e85691b731";
		String cur = "RM";
		String returnurl = "http://www.ibookcourt.com/gateway_status?tx_hash=6IR43TCz";
		
		
		String imageLocation = "images/payment_options/credit_card.png";
		String altText = "Make payments with Credit Card";
		String paymentText = "Pay with Credit Card";
		//getMolPayFormTemplate();
		String password = "123456";
		System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
		
	}

	public static String getMolPayFormTemplate() {
		StringBuffer form = new StringBuffer(
				"<form method=\"post\" name=\"ePayment\" action=\"{action}\">\n");
		form.append(getInputHidden("amount"));
		form.append(getInputHidden("orderid"));
		form.append(getInputHidden("bill_name"));
		form.append(getInputHidden("bill_email"));
		form.append(getInputHidden("bill_mobile"));
		form.append(getInputHidden("bill_desc"));
		form.append(getInputHidden("currency"));
		form.append(getInputHidden("returnurl"));
		form.append(getInputHidden("vcode"));
		form.append(getInputHidden("country"));
		form.append("<input type=\"submit\" border=\"2\" style=\"background-image: url({imageLocation});"
				+ "background-repeat:no-repeat;background-position:center;background-color:#fff;vertical-align:text-bottom;\" "
				+ "value=\"\" name=\"submit\" id=\"submit\" alt=\"{altText}\"><br>\n");
		form.append("<input type=\"submit\" border=\"2\" style=\"font-size:9px;height:17px\" "
				+ "value=\"{paymentText}\" name=\"submit\" id=\"submit\" alt=\"{altText}\">\n");
		form.append("</form>");
		System.out.println(form);
		return form.toString();
	}
	
	public static String getMolPayForm(MolPayRequest paymentInfo) {
		StringBuffer form = new StringBuffer(
				"<form method=\"post\" name=\"ePayment\" action=\"{action}\">\n");
		form.append(getInputHidden("amount",paymentInfo.getAmount()));
		form.append(getInputHidden("orderid",paymentInfo.getOrderid()));
		form.append(getInputHidden("bill_name",paymentInfo.getBill_name()));
		form.append(getInputHidden("bill_email",paymentInfo.getBill_email()));
		form.append(getInputHidden("bill_mobile",paymentInfo.getBill_mobile()));
		form.append(getInputHidden("bill_desc",paymentInfo.getBill_desc()));
		form.append(getInputHidden("currency",paymentInfo.getCurrency()));
		form.append(getInputHidden("returnurl",paymentInfo.getReturnurl()));
		form.append(getInputHidden("vcode",paymentInfo.getVcode()));
		form.append(getInputHidden("country",paymentInfo.getCountry()));
		
		//Is this value shouldn't be set in calling application?
		form.append("<input type=\"submit\" border=\"2\" style=\"background-image: url({imageLocation});"
				+ "background-repeat:no-repeat;background-position:center;background-color:#fff;vertical-align:text-bottom;\" "
				+ "value=\"\" name=\"submit\" id=\"submit\" alt=\"{altText}\"><br>\n");
		
		form.append("<input type=\"submit\" border=\"2\" style=\"font-size:9px;height:17px\" "
				+ "value=\"Pay with {paymentText}\" name=\"submit\" id=\"submit\" alt=\"Make payments with {altText}\">\n");
		
		form.append("</form>");
		return form.toString();
	}

	private static String getInputHidden(String hiddenName) {
		String str = "<input type=\"hidden\" name=\"" + hiddenName
				+ "\" value=\"{" + hiddenName + "}\">\n";
		return str;
	}
	
	private static String getInputHidden(String hiddenName,String hiddenValue) {
		String str = "<input type=\"hidden\" name=\"" + hiddenName
				+ "\" value=\"" + hiddenValue + "\">\n";
		return str;
	}
}
