package com.prakat.middleware.requestbeans;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Order")
public class OrderRequest implements Serializable{
	
	private static final long serialVersionUID = 3073538532319674652L;
	@NotNull(message = "Order Reference Id is Mandatory")
	@ApiModelProperty(value = "Reference Id of Order",required = true)
	private String orderReferenceId;
	@NotNull(message = "List of Dish Details is Mandatory")
	@ApiModelProperty(value = "List of Dish Details",required = true)
	private List<DishDetailsRequest> dishDetailsList;
	@ApiModelProperty(value = "Quantities of dishes in Order",required = true)
	private int dishTotalQuantity;
	@ApiModelProperty(value = "Discount on Order ",required = true)
	private int orderDiscount;
	@ApiModelProperty(value = "Total Calculated tax for Order in Dollar ",required = true)
	private double tax;
	@ApiModelProperty(value = "Total Amount of Order excluding all Taxes in  Dollar",required = true)
	private double sumAmount;
	@ApiModelProperty(value = "Total Amount of Order including all Taxes in Dollar",required = true)
	private double totalAmount;
	@NotNull(message = "Payment Mode Id is Mandatory")
	@Range(min = 1,message = "Paymement Mode id should be valid")
	@ApiModelProperty(value = "Id of Payment Mode",required = true)
	private int paymentModeId;
	@NotNull(message = "Payment Status is Mandatory")
	@Pattern(regexp = "successful|pending|failed", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(value = "Status of Payment",required = true, allowableValues = "successful,pending,failed")
	private String paymentStatus;
	@NotNull(message = "Order Status is Mandatory")
	@ApiModelProperty(value = "Status of Order",required = true)
	private int orderStatus;
	@Size(max = 200 , message =  "Suggesstions should be of Maximum 200 ")
	@ApiModelProperty(value = "Suggestions")
	private String suggestions;
	
	private int deleveryAddressId;

	public String getOrderReferenceId() {
		return orderReferenceId;
	}

	public void setOrderReferenceId(String orderReferenceId) {
		this.orderReferenceId = orderReferenceId;
	}
	public List<DishDetailsRequest> getDishDetailsList() {
		return dishDetailsList;
	}

	public void setDishDetailsList(List<DishDetailsRequest> dishDetailsList) {
		this.dishDetailsList = dishDetailsList;
	}

	public int getDishTotalQuantity() {
		return dishTotalQuantity;
	}

	public void setDishTotalQuantity(int dishTotalQuantity) {
		this.dishTotalQuantity = dishTotalQuantity;
	}

	public int getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public int getDeleveryAddressId() {
		return deleveryAddressId;
	}

	public void setDeleveryAddressId(int deleveryAddressId) {
		this.deleveryAddressId = deleveryAddressId;
	}
}
