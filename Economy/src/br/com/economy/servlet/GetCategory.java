package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.CategoriaDao;


public class GetCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CategoriaDao DAO  = new CategoriaDao();
    
    public GetCategory() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String json = DAO.getCategories();
		
		PrintWriter out = response.getWriter();
		
		out.write(json);
	}
}
