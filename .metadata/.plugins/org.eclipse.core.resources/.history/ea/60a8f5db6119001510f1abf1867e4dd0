package br.com.economy.DAO;


import javax.persistence.EntityManager;

import br.com.economy.entities.Transacao;
import br.com.economy.util.HibernateUtil;

public class TransacaoDao 
{
EntityManager em = HibernateUtil.getEntityManager();
	
	public TransacaoDao() 
	{
	// TODO Auto-generated constructor stub
	}

	
	public void Insert(Transacao transacao)
	{
		em.getTransaction().begin();
		em.persist(transacao);
		em.getTransaction().commit();
	}

	public void Update(Transacao transacao)
	{
		em.getTransaction().begin();
		em.merge(transacao);
		em.getTransaction().commit();
	}

	public Transacao GetTransacaoById(Integer idtransacao)
	{
		Transacao transacao = em.find(Transacao.class, idtransacao);
		return transacao;
	}
}
