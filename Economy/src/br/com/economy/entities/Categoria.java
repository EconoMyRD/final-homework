package br.com.economy.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@SqlResultSetMapping(name="getCategories",
entities = @EntityResult(entityClass = Categoria.class,
fields={
	@FieldResult(name = "categoria_id", column = "categoria_id"),
	@FieldResult(name = "tipo", column = "tipo"),
	@FieldResult(name = "nome", column = "nome")
})
)

@Entity
@Table(name="CATEGORIA")
public class Categoria implements Serializable
{
	@Id
	@Column(name="categoria_id")
	private Integer id;
	
	
	private Integer tipo;
	
	private String nome;
	
	public Categoria() 
	{
	}
	
	
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getTipo() 
	{
		return tipo;
	}

	public void setTipo(Integer tipo) 
	{
		this.tipo = tipo;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}
}
