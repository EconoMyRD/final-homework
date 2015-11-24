package br.com.economy.model;

public class ModelDataTable {
	private float value;
	private String name;
	private int subcategory;
	private int category;
	private String date;
	private String description;
	private String nameCat;
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelDataTable(float value, String name, int subcategory, int categoria, String date, String description,
			String nameCat, int id) {
		super();
		this.value = value;
		this.name = name;
		this.subcategory = subcategory;
		this.category = categoria;
		this.date = date;
		this.description = description;
		this.nameCat = nameCat;
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameCat() {
		return nameCat;
	}

	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}

	public float getValue() {
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

	public int getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}

	public int getCategory() {
		return category;
	}

	public void setCategoria(int categoria) {
		this.category = categoria;
	}

	
}
