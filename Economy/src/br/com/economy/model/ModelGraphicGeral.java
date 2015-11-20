package br.com.economy.model;

public class ModelGraphicGeral {
	private String name;
	private float value;
	
	
	public String getCategory() {
		return name;
	}
	public void setCategory(String category) {
		this.name = category;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
	public ModelGraphicGeral(String category, float value) {
		super();
		this.name = category;
		this.value = value;
	}
	
	
}
