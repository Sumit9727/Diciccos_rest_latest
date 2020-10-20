package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Menu Type")
public class MenuTypeRequest implements Serializable{
	
	private static final long serialVersionUID = -4101816759767926896L;
	@NotNull(message = "Menu Name is Mandatory")
	@ApiModelProperty(value = "Name of Menu",required = true)
	private String menuName;
	@ApiModelProperty(value = "Description of Menu")
	private String description;
	@NotNull(message = "Restaurent Id is Mandatory")
	@ApiModelProperty(value = "Id of Restaurent",required = true)
	private int restaurentId;
	@ApiModelProperty(value = "Status of Menu")
	private String status;
	@ApiModelProperty(value = "Url of Image for Menu")
	private String imgUrl;
	@Pattern(regexp = "Delivery|Takeaway|Catering", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value = "Type of Services",required = true, allowableValues = "Delivery,Takeaway,Catering", example = "Delivery")
	private String serviceType;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestaurentId() {
		return restaurentId;
	}
	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
