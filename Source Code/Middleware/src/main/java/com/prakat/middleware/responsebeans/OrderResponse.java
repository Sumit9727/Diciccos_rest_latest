package com.prakat.middleware.responsebeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.prakat.middleware.entity.DeliveryAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@JsonPropertyOrder(value = {
"orderId","orderReferenceId","timeStamp","userId","dishNameList","dishTotalQuantity","orderDiscount","sumAmount",
"tax","totalAmount","paymentMode","paymentStatus","orderStatus","suggestions","suggestionDishList","deliveryAddress"})
@ApiModel(description = "All details about the Dish Name Response")
public class OrderResponse implements Serializable {
	@ApiModelProperty(notes = "Id of the Order Record")
	private int orderId;
	@ApiModelProperty(notes = "Reference Id for the Order record, generated by client app")
	private String orderReferenceId;
	@ApiModelProperty(notes = "Time Stamp, when order record is generated")
	private Date timeStamp;
	@ApiModelProperty(notes = "Id of User")
	private int userId;
	@ApiModelProperty(notes = "List of Dishes")
	private List<DishNameResponse> dishNameList;
	@ApiModelProperty(notes = "List of suggested Dishes")
	private List<SuggestedDishResponse> suggestedDishList;
	@ApiModelProperty(notes = "Total quantities of Dishes")
	private int dishTotalQuantity;
	@ApiModelProperty(notes = "Discount applied for Order")
	private int orderDiscount;
	@ApiModelProperty(notes = "Total price of order after including all taxes in Dollar")
	private double totalAmount;
	@ApiModelProperty(notes = "Mode of a Payment")
	private String paymentMode;
	@ApiModelProperty(notes = "Status of a Payment", allowableValues="{successful, pending, failed}")
	private String paymentStatus;
	@ApiModelProperty(notes = "Status of the Order")
	private int orderStatus;
	@ApiModelProperty(notes = "Tax applied on the Order in Dollar")
	private double tax;
	@ApiModelProperty(notes = "Total calculated amount of the Order excluding Taxes")
	private double sumAmount;
	@ApiModelProperty(notes = "Suggestions of the Order")
	private String suggestions;
	@ApiModelProperty(notes = "Delivery Address for the Order")
	private DeliveryAddress deliveryAddress;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderReferenceId() {
		return orderReferenceId;
	}
	public void setOrderReferenceId(String orderReferenceId) {
		this.orderReferenceId = orderReferenceId;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<DishNameResponse> getDishNameList() {
		return dishNameList;
	}
	public void setDishNameList(List<DishNameResponse> dishNameList) {
		this.dishNameList = dishNameList;
	}
	public List<SuggestedDishResponse> getSuggestedDishList() {
		return suggestedDishList;
	}
	public void setSuggestedDishList(List<SuggestedDishResponse> suggestedDishList) {
		this.suggestedDishList = suggestedDishList;
	}
	public int getDishTotalQuantity() {
		return dishTotalQuantity;
	}
	public void setDishTotalQuantity(int dishedTotalQuantity) {
		this.dishTotalQuantity = dishedTotalQuantity;
	}
	public int getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
