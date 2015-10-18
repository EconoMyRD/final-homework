package br.com.economy.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOG")
public class Log implements Serializable
{
	@Id
	@Column(name="LOG_ID")
	private Integer id;
	@Column(name="TRANSACAO")
	private Integer transacaId;
	private String acao;
	
	public Log() 
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
	public Integer getTransacaId() 
	{
		return transacaId;
	}
	public void setTransacaId(Integer transacaId) 
	{
		this.transacaId = transacaId;
	}
	public String getAcao() 
	{
		return acao;
	}
	public void setAcao(String acao) 
	{
		this.acao = acao;
	}
	
	
	
}
