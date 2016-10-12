package com.payment.paymentchannel;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.payment.PaymentGatewayWSApp;
import com.payment.model.MolPayRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentGatewayWSApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentChannelConfigurationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	//@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/pg/pc", Map.class);
		System.out.println(entity.getBody());
		//then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getPaymentChannels() throws Exception {
		@SuppressWarnings("rawtypes")
		String url = "http://localhost:" + this.port + "/pg/channel";
		MolPayRequest paymentInfo = new MolPayRequest();
		paymentInfo.setAmount("108.10");
		paymentInfo.setBill_desc("Booking for 1 hours at F2");
		paymentInfo.setBill_email("sgvwork2016@gmail.com");
		paymentInfo.setBill_mobile("919094300840");
		paymentInfo.setBill_name("Vimal Raj");
		paymentInfo.setCountry("MY");
		paymentInfo.setCurrency("RM");
		paymentInfo.setOrderid("16882");
		paymentInfo.setReturnurl("http://www.ibookcourt.com/gateway_status");
		ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(url, paymentInfo, Map.class,Collections.EMPTY_MAP);
		System.out.println(entity.getBody());
		//then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
