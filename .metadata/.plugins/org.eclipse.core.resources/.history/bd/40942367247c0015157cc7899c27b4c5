package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.TransactionDAO;

public class ServletRelatory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TransactionDAO DAO = new TransactionDAO();

    
    public ServletRelatory() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		Date dateEnd = new Date();
		Date dateStart = new Date();
		int category =0;
		
		////  get user
		Cookie cookies[] = request.getCookies();
		int userId=0;
		if (cookies != null)
		{
			for(Cookie cookie : cookies){
			    if("userId".equals(cookie.getName())){
			        userId = Integer.parseInt(cookie.getValue());
			    }
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			category = Integer.parseInt(request.getParameter("category"));
			dateStart = sdf.parse(request.getParameter("dateStart"));
			dateEnd = sdf.parse(request.getParameter("dateEnd"));
		} catch (ParseException e) {
			//impossible have wrong parameters
			e.printStackTrace();
		}
//		System.out.println(category);
		System.out.println(dateStart);
		System.out.println(dateEnd);
		String json = DAO.getDataForGraphic(dateStart, dateEnd, category, userId);
		out.write(json);
	}


	
	
	

}
