package com.prakat.middleware.responsebuilder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.prakat.middleware.responsebeans.Pagination;
import com.prakat.middleware.responsebeans.SuccessResponse;

@Component
public class SuccessResponseBuilder {

	public SuccessResponse buildResponse(String message , Object data, Pagination pagination) {
		SuccessResponse response = new SuccessResponse();
		response.setTimeStamp(LocalDateTime.now());
		response.setMessage(message);
		response.setData(data);
		response.setPagination(pagination);
		return response ;
	}
}
