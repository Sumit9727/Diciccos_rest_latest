package com.prakat.middleware.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "orders")
@ApiModel(description = "All details about the order")
public class Order implements Serializable{
	private static final long serialVersionUID = -6510271487727285145L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	@Column(name = "order_reference_id")
	private String orderReferenceId;
	@Column(name = "order_timeStamp")
	private Date timeStamp;
	@Column(name = "user_id")
	private int userId;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<DishDetails> dishDetailsList;
	@Column(name = "dishes_total_quantity")
	private int dishTotalQuantity;
	@Column(name = "order_discount")
	private int orderDiscount;
	@Column(name = "tax") 
	private double tax;
	@Column(name = "sub_amount") 
	private double sumAmount;
	@Column(name = "total_amount")
	private double totalAmount;
	@Column(name = "payment_mode_id")
	@Range(min = 1,message = "Paymement Mode id should be valid")
	private int paymentModeId;
	@Column(name = "payment_status")
	@Pattern(regexp = "successful|pending|failed", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String paymentStatus;
	@Column(name = "order_status")
	private int orderStatus;
	@Column(name = "suggestions")
	@Size(max = 200 , message =  "Suggesstions should be of maximum 200 ")
	@ApiModelProperty(notes = "Suggesstions should be of maximum 200 ")
	private String suggestions;
	@Column(name = "delivery_address_id")
	private int deleveryAddressId;
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
	public List<DishDetails> getDishDetailsList() {
		return dishDetailsList;
	}
	public void setDishDetailsList(List<DishDetails> dishDetailsList) {
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
