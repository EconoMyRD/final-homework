package br.com.economy.model;

public class UserModel {
	private Integer id;
	private String email;
	private String name;
	private String password;
	private Boolean active;
	private float sale;
	
	
	
	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public float getSale() {
		return sale;
	}
	public void setSale(float sale) {
		this.sale = sale;
	}
	
	
	
}
