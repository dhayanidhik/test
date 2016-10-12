package com.payment.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.payment.model.MolPayPaymentChannel;
@Configuration
@ConfigurationProperties("malaysia")
@Component
public class MolPayMalaysiaPaymentChannel  implements PaymentChannel{
	
	private final List<MolPayPaymentChannel> paymentChannels = new ArrayList<MolPayPaymentChannel>();

	/**
	 * @return the paymentChannels
	 */
	public List<MolPayPaymentChannel> getPaymentChannels() {
		return paymentChannels;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MalaysiaPaymentChannelConfig [paymentChannels="
				+ paymentChannels + "]";
	}
}
