package br.com.economy.model;

public class ModelTableGeral {
	private String subategory;
	private String nameCat;
	private float value;
	private String description;
	private String date;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubategory() {
		return subategory;
	}
	public void setSubategory(String subategory) {
		this.subategory = subategory;
	}
	public String getCategory() {
		return nameCat;
	}
	public void setCategory(String category) {
		this.nameCat = category;
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
	
	public ModelTableGeral(String subategory, String category, float value, String description, String date, int id) {
		super();
		this.subategory = subategory;
		this.nameCat = category;
		this.value = value;
		this.description = description;
		this.date = date;
		this.id = id;
	}
	
	
}
