package br.com.economy.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.economy.entities.SubCategory;
import br.com.economy.util.HibernateUtil;

import com.google.gson.Gson;

public class SubCategoryDAO {
	public String getSubcategories(int category_id){
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		Query query = em.createNativeQuery("select nome, subcategoria_id, categoria_id from "
				+ "subcategoria where categoria_id = ?", "getSubcategories");
		query.setParameter(1, category_id);
		
		//List<SubcategoriesModel> list = new ArrayList<SubcategoriesModel>();
		List<SubCategory> list = query.getResultList();
		
		//System.out.println(list.get(0).getId());
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
				
		
		//System.out.println("json:" + json);	
		
		return json;
	}
}
