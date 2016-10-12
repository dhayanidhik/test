package com.payment.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import com.payment.config.MolPayProperties;
import com.payment.config.PaymentChannelFactory;
import com.payment.model.MolPayPaymentChannel;
import com.payment.model.MolPayRequest;
import com.payment.model.MolPayResponse;
import com.payment.model.PaymentGatewayResponse;
import com.payment.util.MolPayFormGenerator;
import com.payment.util.MolPayUtil;

@Component
public class MolPayHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentChannelFactory paymentChannelFactory;
	@Autowired
	MolPayProperties molPayProperties;

	public PaymentGatewayResponse getPaymentChannels(String channelCountry,
			MolPayRequest paymentInfo) {
		PaymentGatewayResponse gatewayResponse = new PaymentGatewayResponse();
		// Validate the mandatory fields exist in request.
		log.debug("Check mandatory parameters are available in {}", paymentInfo);
		String missingParam = MolPayUtil
				.getPaymentChannelMissingParam(paymentInfo);
		if (StringUtils.isNotBlank(missingParam)) {
			log.info(
					"Following mandatory parameters Missing {} in payment channel request:{}",
					missingParam, paymentInfo);
			gatewayResponse.setStatus(MolPayUtil.FAILURE);
			gatewayResponse.setMessage("Following Parameter Required:\n"
					+ missingParam);
			return gatewayResponse;
		}
		log.debug("Validation Success for Mandatory parameters in {}",
				paymentInfo);
		// Get the list of payment channel.
		log.debug("Get the list of payment channel for {}", channelCountry);
		List<MolPayPaymentChannel> paymentChannels = paymentChannelFactory
				.getPaymentChannelsByCountry(channelCountry);
		if (null == paymentChannels || paymentChannels.isEmpty()) {
			log.debug("No Payment channel available for {}", channelCountry);
			gatewayResponse.setStatus(MolPayUtil.FAILURE);
			gatewayResponse.setMessage("No Payment Channel Available for {}"
					+ channelCountry.toUpperCase());
			return gatewayResponse;
		} else {
			log.debug("{} Payment channels are available for {}",
					paymentChannels.size(), channelCountry);
			// Read parameter from molpay.properties to construct the url.
			String merchandId = molPayProperties.getMerchantId();
			String baseUrl = molPayProperties.getMolPayUrl();
			log.info("MerchantId={} and BaseUrl={}", merchandId, baseUrl);
			if (StringUtils.isBlank(merchandId) || StringUtils.isBlank(baseUrl)) {
				log.error(
						"MolPay Payment Properties MerchantId or BaseUrl is not confiured properly.Please check MerchantId={},BaseUrl={}",
						merchandId, baseUrl);
				gatewayResponse.setStatus(MolPayUtil.ERROR);
				gatewayResponse
						.setMessage("MolPay Payment Properties MerchantId or BaseUrl is not confiured properly.");
				gatewayResponse.setDescription("MerchantId=" + merchandId
						+ " and MolPay Base Url=" + baseUrl);
				return gatewayResponse;
			}

			String paymentUrl = "";
			if (baseUrl.endsWith("/")) {
				paymentUrl = baseUrl + merchandId + "/";
			} else {
				paymentUrl = baseUrl + "/" + merchandId + "/";
			}

			// Generate the verification code
			String vKeyString = paymentInfo.getAmount() + merchandId
					+ paymentInfo.getOrderid()
					+ molPayProperties.getVerifyKey();
			log.debug("Verification Code String:" + vKeyString);
			String vcode = DigestUtils.md5DigestAsHex(vKeyString.getBytes());
			log.info("Encripted Verification Code:" + vcode);
			paymentInfo.setVcode(vcode);
			// Construct the payment channel form to use directly in parent app.
			String molPayFormTemplate = MolPayFormGenerator
					.getMolPayForm(paymentInfo);
			String molPayFormForChannel = "";
			List<String> forms = new ArrayList<String>(paymentChannels.size());
			for (MolPayPaymentChannel paymentChannel : paymentChannels) {
				String action = paymentUrl + paymentChannel.getFileName();
				molPayFormForChannel = molPayFormTemplate.replace(
						"{paymentText}", paymentChannel.getChannelName());
				molPayFormForChannel = molPayFormForChannel.replace(
						"{altText}", paymentChannel.getChannelName());
				forms.add(molPayFormForChannel.replace("{action}", action));
			}
			gatewayResponse.setChannels(forms);
			gatewayResponse.setStatus(MolPayUtil.SUCCESS);
			gatewayResponse.setMessage("Successfully Retrieved the "
					+ channelCountry.toUpperCase() + " MolPay Payment channel");
			return gatewayResponse;
		}

	}

	public String xvalidateTransaction(MolPayResponse molPayResponse) {
		// tranID & orderid & status & domain & amount & currency
		String vkey0 = molPayResponse.getTranID() + molPayResponse.getOrderid()
				+ molPayResponse.getStatus() + molPayResponse.getDomain()
				+ molPayResponse.getAmount() + molPayResponse.getCurrency();
		System.out.println(vkey0);
		String key0 = DigestUtils.md5DigestAsHex(vkey0.getBytes());
		System.out.println("key0=" + key0);
		// paydate & merchantID & key0 & appcode & verifykey.
		String vkey1 = molPayResponse.getPaydate()
				+ molPayProperties.getMerchantId() + key0
				+ molPayResponse.getAppcode() + molPayProperties.getVerifyKey();
		String key1 = DigestUtils.md5DigestAsHex(vkey1.getBytes());
		System.out.println(vkey1);
		System.out.println("key1=" + key1);
		System.out.println("skey=" + molPayResponse.getSkey());
		if (molPayResponse.getSkey().equalsIgnoreCase(key1)) {
			System.out.println("skey Both are equal");
		} else {
			System.out.println("skey Both are unequal..transaction failure");
		}

		if (molPayResponse.getStatus().equalsIgnoreCase("00")) {
			/*
			 * checking the validity of cart amount & orderid. ’ if the
			 * verification test passed then can update the order status to
			 * paid. ’ you can also do further checking on the paydate as well
			 */
			System.out.println("Transaction success.");

		} else {
			/*
			 * failure action, Merchant might send query to MOLPay using
			 * merchant requery to double check payment status for that
			 * particular order.
			 */
			System.out.println("Transaction failure.");
		}

		// Merchant is to implement IPN to ack on receiving of payment status
		// regardless the payment status
		// Merchant is to implement IPN to ack on receiving of payment status
		// regardless the payment status

		String returnAckUrl = "https://www.onlinepayment.com.my/MOLPay/API/chkstat/returnipn.php";
		molPayResponse.setTreq("1");
		ResponseEntity<Map> entity = new RestTemplate().postForEntity(
				returnAckUrl, molPayResponse, Map.class, Collections.EMPTY_MAP);
		System.out.println("entity return url ack=" + entity.getStatusCode());

		return "SUCCESS";
	}

	public PaymentGatewayResponse validateTransaction(
			MolPayResponse molPayResponse) {
		acknowledgeNotification(molPayResponse);
		PaymentGatewayResponse gatewayResponse = new PaymentGatewayResponse();
		// Generate the verification with tranID & orderid & status & domain &
		// amount & currency
		String vkey0 = molPayResponse.getTranID() + molPayResponse.getOrderid()
				+ molPayResponse.getStatus() + molPayResponse.getDomain()
				+ molPayResponse.getAmount() + molPayResponse.getCurrency();
		log.debug("Verification Key:Vkey0 = " + vkey0);
		String key0 = DigestUtils.md5DigestAsHex(vkey0.getBytes());
		log.debug("Encripted Verification Key:Vkey0 = " + key0);
		// Generate the verification key with paydate & merchantID & key0 &
		// appcode & verifykey.
		String vkey1 = molPayResponse.getPaydate()
				+ molPayProperties.getMerchantId() + key0
				+ molPayResponse.getAppcode() + molPayProperties.getVerifyKey();
		log.debug("Verification Key:Vkey1 = " + vkey1);
		String key1 = DigestUtils.md5DigestAsHex(vkey1.getBytes());
		log.debug("Encripted Verification Key:Vkey1 = " + key1);
		log.debug("Security Key in MolPay Response(skey) = "
				+ molPayResponse.getSkey());
		// Check the security key from molpay is correct.
		if (!molPayResponse.getSkey().equalsIgnoreCase(key1)) {
			log.debug(
					"Invalid transaction due to mismatch of Security Key in MolPay Response (skey) = {}",
					molPayResponse.getSkey());
			gatewayResponse.setStatus(MolPayUtil.FAILURE);
			gatewayResponse.setMessage("Invalid Transaction");
			return gatewayResponse;
		}

		if (MolPayUtil.MOLPAY_PAYMENT_SUCCESS.equalsIgnoreCase(molPayResponse
				.getStatus())) {
			/*
			 * checking the validity of cart amount & orderid. if the
			 * verification test passed then can update the order status to
			 * paid. you can also do further checking on the paydate as well
			 */
			if (StringUtils.isNotBlank(molPayResponse.getOriginalAmount())) {
				if (!molPayResponse.getAmount().equalsIgnoreCase(
						molPayResponse.getOriginalAmount())) {
					log.info(
							"Amount Mismatch.Original Amount is {} and Amount from molpay reponse is {}",
							molPayResponse.getOriginalAmount(),
							molPayResponse.getAmount());
					gatewayResponse.setStatus(MolPayUtil.FAILURE);
					gatewayResponse.setMessage("Amount Mismatch");
					return gatewayResponse;
				}
			}

			if (StringUtils.isNotBlank(molPayResponse.getOriginalOrderId())) {
				if (!molPayResponse.getOrderid().equalsIgnoreCase(
						molPayResponse.getOriginalOrderId())) {
					log.info(
							"OrderId Mismatch.Original OrderId is {} and OrderId from molpay reponse is {}",
							molPayResponse.getOrderid(),
							molPayResponse.getOriginalOrderId());
					gatewayResponse.setStatus(MolPayUtil.FAILURE);
					gatewayResponse.setMessage("OrderId Mismatch");
					return gatewayResponse;
				}
			}
			log.info("Transaction Success for " + molPayResponse);
			gatewayResponse.setStatus(MolPayUtil.SUCCESS);
			gatewayResponse.setMessage("Transaction Success");
			return gatewayResponse;
		} else {
			/*
			 * failure action, Merchant might send query to MOLPay using
			 * merchant re-query to double check payment status for that
			 * particular order.
			 */
			log.info("Transaction Failure for " + molPayResponse);
			gatewayResponse.setStatus(MolPayUtil.FAILURE);
			if (StringUtils.isNotBlank(molPayResponse.getError_desc())) {
				gatewayResponse.setMessage(molPayResponse.getError_desc());
			} else {
				gatewayResponse.setMessage("Transaction Failed");
			}
			return gatewayResponse;
		}
	}

	private void acknowledgeNotification(MolPayResponse molPayResponse) {
		/*
		 * Merchant is to implement IPN to ack on receiving of payment status
		 * regardless the payment status
		 */
		try{
			String returnAckUrl = molPayProperties.getReturnUrl();
			if(StringUtils.equalsIgnoreCase(molPayResponse.getNbcb(),"1")){
				log.debug("Callback Acknowledgment:"+molPayResponse);
				new RestTemplate().postForEntity(returnAckUrl, "CBTOKEN:MPSTATOK", String.class);
			}else{				
				molPayResponse.setTreq("1");
				log.debug("ReturnUrl Acknowledgment:"+molPayResponse);
				new RestTemplate().postForEntity(returnAckUrl, molPayResponse, Map.class);
			}
		}catch(Exception e){
			log.error("Error in sending Acknowledgement to MolPay:{}",molPayResponse,e);
		}
	}
}
