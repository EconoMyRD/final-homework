package br.com.economy.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.economy.DAO.CategoriaDao;
import br.com.economy.DAO.SubCategoryDAO;
@Path("/category")
public class Category {
       
	CategoriaDao DAO  = new CategoriaDao();

	@GET
	@Produces("application/json")
	public Response getCategory(){
		String json = DAO.getCategories();		
		
		return Response.ok(json,MediaType.APPLICATION_JSON).build();
	}
	

	@GET
	@Path("/sub/{category}")
	@Produces("application/json")
	public String getSubcategory(@PathParam("category") int category){
		SubCategoryDAO subCategoryDAO = new  SubCategoryDAO();
		String json = subCategoryDAO.getSubcategories(category);
		
		return json;
	}
	
	
}