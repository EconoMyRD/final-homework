package br.com.economy.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable
{
	@Id
	@Column(name="USUARIO_ID")//usuario_id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id")
	@SequenceGenerator(name = "user_id", allocationSize = 1, sequenceName = "usuario_id_seq")
	private Integer id;
	private String email;
	private String nome;
	private String senha;
	private Boolean ativo;
	private float saldo;
	
	public Usuario() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getSenha() 
	{
		return senha;
	}
	public void setSenha(String senha)
	{
		this.senha = senha;
	}
	public Boolean getAtivo() 
	{
		return ativo;
	}
	public void setAtivo(Boolean ativo) 
	{
		this.ativo = ativo;
	}
	
	public float getSaldo() 
	{
		return saldo;
	}
	public void setSaldo(float saldo) 
	{
		this.saldo = saldo;
	}
}
