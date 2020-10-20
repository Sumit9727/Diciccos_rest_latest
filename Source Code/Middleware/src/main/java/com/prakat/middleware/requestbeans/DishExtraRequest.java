package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Dish Extra")
public class DishExtraRequest implements Serializable{
	
	private static final long serialVersionUID = -1377901003426711958L;

	@NotNull(message = "Dish Extra Name cannot be Null")
	@ApiModelProperty(value = "Dish Extra Name",required = true)
	private String name;
	@ApiModelProperty(value ="Description")
	private String description;
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value ="Required", allowableValues = "yes,no")
	private String isRequired;
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value ="Radio", allowableValues = "yes,no")
	private String isRadio;
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Is Checkbox should be either yes or no")
	@ApiModelProperty(value ="Checkbox", allowableValues = "yes,no")
	private String isCheckbox;
	@NotNull(message = "Dish Name Id is Mandatory")
	@ApiModelProperty(value ="Dish Name Id", required = true)
	private int dishNameId;
	@ApiModelProperty(value ="Dish Extra Id of Parent")
	private int parentId;
	@ApiModelProperty(value ="Price in Dollar")
	private Double price;
	@ApiModelProperty(value ="Status of Dish Extra")
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(String isRadio) {
		this.isRadio = isRadio;
	}

	public String getIsCheckbox() {
		return isCheckbox;
	}

	public void setIsCheckbox(String isCheckbox) {
		this.isCheckbox = isCheckbox;
	}

	public int getDishNameId() {
		return dishNameId;
	}

	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
