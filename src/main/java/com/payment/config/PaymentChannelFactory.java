package com.payment.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payment.model.MolPayPaymentChannel;
import com.payment.util.MolPayUtil;

@Component
public class PaymentChannelFactory {

	@Autowired
	private MolPayMalaysiaPaymentChannel malaysiaPaymentChannel;

	public List<MolPayPaymentChannel> getPaymentChannelsByCountry(String channelCountry) {
		if (MolPayUtil.MALAYSIA.equalsIgnoreCase(channelCountry)) {
			return malaysiaPaymentChannel.getPaymentChannels();
		} else {
			return null;
		}
	}

}
