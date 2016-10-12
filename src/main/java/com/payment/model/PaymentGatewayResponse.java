package com.payment.model;

import java.util.List;

import com.payment.model.BaseResponse;
import com.payment.model.MolPayResponse;

public class PaymentGatewayResponse extends BaseResponse {
	private List<String> channels;
	private MolPayResponse molPayResponse;

	/**
	 * @return the channels
	 */
	public List<String> getChannels() {
		return channels;
	}

	/**
	 * @param channels
	 *            the channels to set
	 */
	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	/**
	 * @return the molPayResponse
	 */
	public MolPayResponse getMolPayResponse() {
		return molPayResponse;
	}

	/**
	 * @param molPayResponse
	 *            the molPayResponse to set
	 */
	public void setMolPayResponse(MolPayResponse molPayResponse) {
		this.molPayResponse = molPayResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentGatewayResponse [channels=" + channels
				+ ", molPayResponse=" + molPayResponse + ", status="
				+ getStatus() + ", message=" + getMessage()
				+ ", description=" + getDescription() + "]";
	}

}
