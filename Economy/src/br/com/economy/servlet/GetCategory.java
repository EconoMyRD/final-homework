package br.com.economy.servlet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import br.com.economy.DAO.CategoriaDao;

@ApplicationPath("/category")
public class GetCategory extends Application{
       
	CategoriaDao DAO  = new CategoriaDao();

	@GET
	public String getCategory(){
		String json = DAO.getCategories();		
		
		return json;
	}
}