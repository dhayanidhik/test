package com.payment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.payment.handler.MolPayHandler;
import com.payment.model.MolPayRequest;
import com.payment.model.MolPayResponse;
import com.payment.model.PaymentGatewayResponse;

@RestController
public class MolPayController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private MolPayHandler paymentChannelHandler;

	@RequestMapping(path = "/pg/pc", method = RequestMethod.GET)
	public @ResponseBody
	PaymentGatewayResponse sayHello(
			@RequestParam(value = "name", required = false, defaultValue = "MYR") String name) {
		log.debug("debug level log=" + counter.incrementAndGet());
		log.info("info level log");
		log.error("error level log");

		// Log a formatted string with parameters
		log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);
		PaymentGatewayResponse response = new PaymentGatewayResponse();
		// List<String> channels=
		// paymentChannelHandler.getPaymentChannels(name);
		response.setStatus("0");
		response.setMessage("Success");
		// response.setChannels();
		return response;

		// return "Credit-Card";
	}

	//@CrossOrigin
	@RequestMapping(path = "/pg/molpay/channel", method = RequestMethod.POST)
	public @ResponseBody
	PaymentGatewayResponse getPaymentChannels(
			@RequestBody MolPayRequest paymentInfo,
			@RequestParam(value = "country", required = false, defaultValue = "malaysia") String channelCountry) {
		long requestId = counter.incrementAndGet();
		log.info("[RID#{}] and Get {} Payment channels for {}", requestId,
				channelCountry, paymentInfo);
		PaymentGatewayResponse response = new PaymentGatewayResponse();
		response = paymentChannelHandler.getPaymentChannels(channelCountry,
				paymentInfo);
		log.info("[RID#{}] and Get {} Payment channels Response:{}", requestId,
				channelCountry, response);
		return response;
	}

	//@CrossOrigin
	@RequestMapping(path = "/pg/molpay/gateway_status", method = RequestMethod.POST)
	public @ResponseBody
	PaymentGatewayResponse getPaymentStatus(
			@RequestBody MolPayResponse molPayResponse) {
		long requestId = counter.incrementAndGet();
		PaymentGatewayResponse response = new PaymentGatewayResponse();
		log.info("[RID#{}] and check the payment status for {}", requestId,
				molPayResponse);
		response = paymentChannelHandler.validateTransaction(molPayResponse);
		return response;
	}

	//@CrossOrigin
	@RequestMapping(path = "/pg/report", method = RequestMethod.GET)
	public @ResponseBody
	PaymentGatewayResponse getReport(
			@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "txID", required = true) String txID) {

		String url = "https://www.onlinepayment.com.my/MOLPay/q_by_tid.php";
		Map<String, String> s = new HashMap<String, String>();
		s.put("amount", amount);
		s.put("txID", txID);
		s.put("domain", "test7662");
		String skey = DigestUtils.md5DigestAsHex((txID + "test7662"
				+ "6fccd17f30bbbaecad6bf43742bd9d2b" + amount).getBytes());
		s.put("skey", skey);
		s.put("type", "1");

		System.out.println("Query By=" + s);

		ResponseEntity<String> entity = new RestTemplate().getForEntity(url,
				String.class, s);
		System.out.println("report entity=" + entity.getBody());
		PaymentGatewayResponse response = new PaymentGatewayResponse();
		// List<String> channels=
		// paymentChannelHandler.getPaymentChannels(name);
		response.setStatus(entity.getStatusCode().name());
		response.setMessage("Success");
		// response.setChannels();
		return response;

		// return "Credit-Card";

		// https://www.onlinepayment.com.my/MOLPay/API/gatequery/index.php?amount=108.10&txID=162585&domain=test7662&skey=3e4e4084aa263269df4590395f0e570b

		// amount=108.10, domain=test7662, txID=162585,
		// skey=3e4e4084aa263269df4590395f0e570b
	}

	//@CrossOrigin
	@RequestMapping(path = "/pg/molpay/q_by_tid", method = RequestMethod.GET)
	public @ResponseBody
	PaymentGatewayResponse getReportByTransactionId(
			@RequestParam(value = "tid", required = true) String txID,
			@RequestParam(value = "amount", required = true) String amount) {
		//https://www.onlinepayment.com.my/MOLPay/q_by_tid.php?txID=9134278&amount=61.50&skey=4348a0ff1a65a0b4e734b39f6e0972e0&domain=ibookcourt
		String url = "https://www.onlinepayment.com.my/MOLPay/q_by_tid.php";
		Map<String, String> s = new HashMap<String, String>();
		s.put("amount", amount);
		s.put("txID", txID);
		s.put("domain", "ibookcourt");
		String skey = DigestUtils.md5DigestAsHex((txID + "ibookcourt"
				+ "369c826a5488858d565bef3b752b9c51" + amount).getBytes());
		s.put("skey", skey);
		//s.put("type", "0");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("amount", amount)
		        .queryParam("txID", txID)
		        .queryParam("domain", "ibookcourt")
		        .queryParam("skey", skey);
		HttpHeaders headers = new HttpHeaders();        
        HttpEntity<?> reqentity = new HttpEntity<Object>(headers);

		System.out.println("Query By=" + s);

		ResponseEntity<String> entity = new RestTemplate().getForEntity(builder.build().encode().toUri(),
				String.class);
		System.out.println("report entity=" + entity.getBody());
		PaymentGatewayResponse response = new PaymentGatewayResponse();
		response.setStatus(entity.getStatusCode().name());
		response.setMessage("Success");
		return response;
	}

}
