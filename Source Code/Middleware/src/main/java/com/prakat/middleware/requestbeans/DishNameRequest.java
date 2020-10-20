package com.prakat.middleware.requestbeans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Dish Name")
public class DishNameRequest implements Serializable{
	
	private static final long serialVersionUID = 4446789919471592039L;

	@NotNull(message = "Dish Name cannot be Null")
	@ApiModelProperty(value ="Name of Dish", required = true)
	private String dishName;
	@ApiModelProperty(value ="Description of Dish")
	private String description;
	@ApiModelProperty(value ="Id of Tax")
	private int taxId;
	@NotNull(message = "Dish Price cannot be Null")
	@ApiModelProperty(value = "Status of Dish",required = true)
	private double price;
	@NotNull(message = "Extra is Mandatory")
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@Length(min = 2, max = 30, message = "Extra should be either yes or no")
	@ApiModelProperty(notes = "Extra ",allowableValues = "yes, no",required = true)
	private String extra;
	@NotNull(message = "Dish Type Id is Mandatory")
	@ApiModelProperty(value = "Id of Dish Type",required = true)
	private int dishTypeId;
	@ApiModelProperty(value = "Id of Dish Type")
	private String status;
	@ApiModelProperty(value = "Url of Image for Dish")
	private String imgUrl;

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public int getDishTypeId() {
		return dishTypeId;
	}

	public void setDishTypeId(int dishTypeId) {
		this.dishTypeId = dishTypeId;
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
}
