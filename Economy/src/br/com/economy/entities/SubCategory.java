package br.com.economy.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@SqlResultSetMapping(name="getSubcategories",
	entities = @EntityResult(entityClass = SubCategory.class,
	fields={
		@FieldResult(name = "id", column = "subcategoria_id"),
		@FieldResult(name = "categoria_id", column = "categoria_id"),
		@FieldResult(name = "nome", column = "nome")
	})
)

		
@Entity
@Table(name="subcategoria")
public class SubCategory implements Serializable{
	@Id
	@Column(name="subcategoria_id")
	private int id;
	
	private Integer categoria_id;
	private String nome;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	
}
