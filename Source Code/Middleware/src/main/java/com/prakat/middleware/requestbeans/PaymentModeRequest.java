package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Payment Mode")
public class PaymentModeRequest implements Serializable{

	private static final long serialVersionUID = -6386127830946851565L;
	@NotNull(message = "Payment Mode Name is Mandatory")
	@ApiModelProperty(value = "Payment Mode",required = true)
	private String paymentModeName;

	public String getPaymentModeName() {
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}
	
}
