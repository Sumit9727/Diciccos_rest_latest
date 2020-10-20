package com.prakat.middleware.requestbuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.MenuType;
import com.prakat.middleware.requestbeans.MenuTypeRequest;
@Component
public class MenuTypeRequestBuilder {

	public MenuType buildRequest(@Valid MenuTypeRequest menuType) {
		MenuType request = new MenuType();
		request.setDescription(menuType.getDescription());
		request.setImgUrl(menuType.getImgUrl());
		request.setMenuName(menuType.getMenuName());
		request.setRestaurantId(menuType.getRestaurentId());
		request.setStatus(menuType.getStatus());
		request.setServiceType(menuType.getServiceType());
		return request;
	}

	public List<MenuType> buildRequest(@Valid List<MenuTypeRequest> dishExtraList) {
		List<MenuType> requestList = dishExtraList.stream().map(menuType -> buildRequest(menuType)).collect(Collectors.toList());
		return requestList;
	}

}
