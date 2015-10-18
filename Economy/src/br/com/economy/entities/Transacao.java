package br.com.economy.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SqlResultSetMapping(name="getTransactions", entities ={ 
		@EntityResult(entityClass = Transacao.class,
		fields={
			@FieldResult(name = "transacao_id", column = "transacao_id"),
			@FieldResult(name = "subcategoria", column = "subcategoria"),
			@FieldResult(name = "usuario", column = "usuario"),
			@FieldResult(name = "data_transacao", column = "data_transacao"),
			@FieldResult(name = "descricao", column = "descricao"),
			@FieldResult(name = "valor", column = "valor"),
			@FieldResult(name = "data_inclusao", column = "data_inclusao")
			//@FieldResult(name = "categoria_id", column = "categoria_id")
		})
//	,
//	@EntityResult(entityClass = SubCategory.class,
//	fields={
//		@FieldResult(name = "id", column = "id"),
//		@FieldResult(name = "nome", column = "nome"),
//		@FieldResult(name = "categoria_id", column = "categoria_id")
//	}
	//)
}
)

@Entity
@Table(name="TRANSACAO")
public class Transacao implements Serializable 
{
	@Id
	@Column(name="transacao_id")	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_id")
	@SequenceGenerator(name = "sequence_id", allocationSize = 1, sequenceName = "transacao_id_seq")
	private Integer id;


//	@OneToOne(fetch = FetchType.LAZY,targetEntity = SubCategory.class)
//	@PrimaryKeyJoinColumn
	//private SubCategory subcategoria;
	private int subcategoria;
	@Column(name="USUARIO")
	private int usuarioId;
	@Column(name="DATA_TRANSACAO")
	@Temporal(TemporalType.DATE)
	private Date data_transacao;
	private String descricao;
	private float valor;
	@Column(name="DATA_INCLUSAO")
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	
	public Transacao() 
	{
	}
	
	
	public Integer getId() 
	{
		return id;
	}
	
	public int getSubcategoriaId() 
	{
		return subcategoria;
	}
	public void setSubcategoriaId(int subcategoriaId) 
	{
		this.subcategoria = subcategoriaId;
	}
	public int getUsuarioId() 
	{
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) 
	{
		this.usuarioId = usuarioId;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public Date getDataTransacao() 
	{
		return data_transacao;
	}
	public void setDataTransacao(Date dataTransacao) 
	{
		this.data_transacao = dataTransacao;
	}
	public float getValor() 
	{
		return valor;
	}
	public void setValor(float valor) 
	{
		this.valor = valor;
	}
	public Date getDataRegistro() 
	{
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) 
	{
		this.dataRegistro = dataRegistro;
	}
	
	
}
