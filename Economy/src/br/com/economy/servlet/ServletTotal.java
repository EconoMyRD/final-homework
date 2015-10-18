package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.UsuarioDao;


public class ServletTotal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletTotal() 
    {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsuarioDao dao = new UsuarioDao();
		int user =0;
		Cookie cookies[] = request.getCookies();
		
		for(Cookie cookie : cookies){
		    if("userId".equals(cookie.getName())){
		        user = Integer.parseInt(cookie.getValue());
		    }
		}
		Float total = dao.getTotal(user);
		
		PrintWriter out = response.getWriter();
		out.write(total.toString());
	}
}
