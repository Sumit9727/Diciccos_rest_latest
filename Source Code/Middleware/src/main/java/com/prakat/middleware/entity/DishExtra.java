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

import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "dish_extras")
@Document(indexName = "dish_extras",  shards = 1)
@ApiModel(description = "All details about the Dish Extra")
public class DishExtra implements Serializable{
	private static final long serialVersionUID = 1157460999563874562L;
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_extra_id")
	private int dishExtraId;
	@NotNull(message = "Dish Extra Name cannot be null")
	@ApiModelProperty(notes = "Dish Extra Name cannot be null")
	@Column(name = "dish_extra_name")
	private String name;
	@Column(name = "dish_extra_description")
	private String description;
	@Column(name = "is_required")
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(notes = "Is Required should be either yes or no")
	private String isRequired;
	@Column(name = "is_radio")
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE)
	@ApiModelProperty(notes = "Is Radio should be either yes or no")
	private String isRadio;
	@Column(name = "is_checkbox")
	@Pattern(regexp = "yes|no", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Is Checkbox should be either yes or no")
	@ApiModelProperty(notes = "Is Checkbox should be either yes or no")
	private String isCheckbox;
	@Column(name = "dish_name_id")
	private int dishNameId;
	@Column(name = "dish_extra_parent_id")
	private int parentId;
	@Column(name = "dish_price")
	private Double price;
	@Column(name = "dish_extra_status")
	private String status;
	public int getDishExtraId() {
		return dishExtraId;
	}
	public void setDishExtraId(int dishExtraId) {
		this.dishExtraId = dishExtraId;
	}
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
