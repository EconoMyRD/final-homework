package br.com.economy.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import br.com.economy.entities.Categoria;
import br.com.economy.util.HibernateUtil;


public class CategoriaDao
{
	EntityManager em = HibernateUtil.getEntityManager();
	
	public CategoriaDao() 
	{
		
	}
	
	public String getCategories(){
		List<Categoria> list = new ArrayList<Categoria>();
		
		Query query = em.createNativeQuery("select nome,tipo, categoria_id from categoria", Categoria.class);
		list = query.getResultList();
		
		Gson gson =  new Gson();
		String json = gson.toJson(list);
		
		System.out.println(json);
		return json;
	}
	
	
	public Categoria GetCategoriaById(Integer idCategoria)
	{
		Categoria categoria = em.find(Categoria.class, idCategoria);
		return categoria;
	}
}
