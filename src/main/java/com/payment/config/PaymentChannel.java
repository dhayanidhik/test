package com.payment.config;

import java.util.List;

import com.payment.model.MolPayPaymentChannel;

public interface PaymentChannel {

	List<MolPayPaymentChannel> getPaymentChannels();

}
