package br.com.economy.model;

import java.util.Date;

public class ModelTableGeral {
	private String subategory;
	private String category;
	private float value;
	private String description;
	private String date;
	
	public String getSubategory() {
		return subategory;
	}
	public void setSubategory(String subategory) {
		this.subategory = subategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public ModelTableGeral(String subategory, String category, float value, String description, String date) {
		super();
		this.subategory = subategory;
		this.category = category;
		this.value = value;
		this.description = description;
		this.date = date;
	}
	
	
}
