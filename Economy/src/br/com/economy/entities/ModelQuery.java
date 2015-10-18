package br.com.economy.entities;


public class ModelQuery {
	double value;
	String name;
	//String date;
	int subcategory;
	int category;
	
	
	public ModelQuery(float value, String name, int subcategory, int category) {
		super();
		this.value = value;
		this.name = name;
		//this.date = date;
		this.subcategory = subcategory;
		this.category = category;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
	public long getSubcategoria() {
		return subcategory;
	}
	public void setSubcategoria(int subcategoria) {
		this.subcategory = subcategoria;
	}
	public long getCategoria() {
		return category;
	}
	public void setCategoria(int categoria) {
		this.category = categoria;
	}
	
	
}
