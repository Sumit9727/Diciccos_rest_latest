package com.prakat.middleware.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "dish_names")
@Document(indexName = "dish_names",  shards = 1)
@ApiModel(description = "All details about the Dish Name")
public class DishName implements Serializable{
	private static final long serialVersionUID = -4321962714326686835L;
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_name_id")
	private int dishNameId;
	@NotNull(message = "Dish Name cannot be null")
	@ApiModelProperty(notes = "Dish Name cannot be null")
	@Column(name = "dish_name")
	private String dishName;
	@Column(name = "dish_name_description")
	private String description;
	@Column(name = "tax_id")
	private int taxId;
	@Column(name = "dish_price")
	@NotNull(message = "Dish Price cannot be null")
	@ApiModelProperty(notes = "Status cannot be null ")
	private double price;
	@Column(name = "dish_extra")
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@Length(min = 2, max = 30, message = "Extra should be either yes or no")
	@ApiModelProperty(notes = "Extra should be either yes or no")
	private String extra;
	@Column(name = "dish_type_id")
	private int dishTypeId;
	@Column(name = "dish_status")
	private String status;
	@Column(name = "dish_name_img_url")
	private String imgUrl;
	public int getDishNameId() {
		return dishNameId;
	}
	public void setDishNameId(int dishNameId) {
		this.dishNameId = dishNameId;
	}
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
