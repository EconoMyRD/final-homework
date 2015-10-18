package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.economy.DAO.UsuarioDao;
import br.com.economy.entities.Usuario;


public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletLogin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UsuarioDao DAO = new UsuarioDao();
		
		Integer active = DAO.verifyUser(email, password);
		
		System.out.println(active);
		if(active == 3){
			initSession(request, email, response);
		}
		PrintWriter out = response.getWriter();
		out.write(active.toString());
		
	}
	
	public void initSession(HttpServletRequest request, String email, HttpServletResponse response) throws IOException{
		UsuarioDao dao = new UsuarioDao();
		Usuario user = new Usuario();
		user = dao.getUserByEmail(email);
		
		String userId = (user.getId()).toString();
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);	
		
		
		Cookie cookie = new Cookie("userId", userId);
		response.addCookie(cookie);
	}

}
