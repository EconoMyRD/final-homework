package br.com.economy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletLogoff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletLogoff() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        Cookie cookies[] = request.getCookies();
//        if (cookie != null) 
//        {
//           cookie[0].setMaxAge(0);
//           response.addCookie(cookie[0]);
//        }
        for(Cookie cookie : cookies){
        	
		   // if("userId".equals(cookie.getName())){
		        cookie.setMaxAge(0);
		        cookie.setPath("/");
		      //  response.addCookie(cookie);	
		        HttpSession session = request.getSession();
		        session.invalidate();		        
		    //}
		}
	}

}
