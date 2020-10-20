package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.PaymentMode;
import com.prakat.middleware.requestbeans.PaymentModeRequest;
@Component
public class PaymentModeRequestBuilder {

	public PaymentMode buildRequest(@Valid PaymentModeRequest paymentMode) {
		PaymentMode request = new PaymentMode();
		request.setPaymentModeName(paymentMode.getPaymentModeName());
		return request;
	}

	public List<PaymentMode> buildRequest(@Valid List<PaymentModeRequest> paymentModeList) {
		List<PaymentMode> requestList = paymentModeList.stream().map(paymentMode -> buildRequest(paymentMode)).collect(Collectors.toList());
		return requestList;
	}

}
