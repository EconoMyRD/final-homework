package br.com.economy.DAO;


import javax.persistence.EntityManager;

import br.com.economy.entities.Log;
import br.com.economy.util.HibernateUtil;

public class LogDao
{
EntityManager em = HibernateUtil.getEntityManager();
	
	public LogDao() 
	{
		// TODO Auto-generated constructor stub
	}

	public void Insert(Log log)
	{
		em.getTransaction().begin();
		em.persist(log);
		em.getTransaction().commit();
	}

	public void Update(Log log)
	{
		em.getTransaction().begin();
		em.merge(log);
		em.getTransaction().commit();
	}

	public Log GetCategoriaById(Integer idLog)
	{
		Log log = em.find(Log.class, idLog);
		return log;
	}
}
