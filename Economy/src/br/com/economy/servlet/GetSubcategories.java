package br.com.economy.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.SubCategoryDAO;



public class GetSubcategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubCategoryDAO subCategoryDAO = new  SubCategoryDAO();
    
    public GetSubcategories() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int category =Integer.parseInt(request.getParameter("select"));
		
		String subcategories = subCategoryDAO.getSubcategories(category);
		
		 PrintWriter out = response.getWriter();
		 out.write(subcategories);
		 
	}

}
