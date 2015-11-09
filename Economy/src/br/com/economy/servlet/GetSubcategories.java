package br.com.economy.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.economy.DAO.SubCategoryDAO;

@Path("/subcategories")
public class GetSubcategories{
	
	 SubCategoryDAO subCategoryDAO = new  SubCategoryDAO();

	@GET
	public Response getSubcategory(){
		String json = subCategoryDAO.getSubcategories(1);
		
		return Response.ok(json).build();
	}
}