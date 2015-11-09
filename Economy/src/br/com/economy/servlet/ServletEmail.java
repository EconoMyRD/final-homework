package br.com.economy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.email.Email;


public class ServletEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletEmail() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String message = "Olá " + name +
				"\n\nPara ativar sua conta clique no link abaixo."
				+ "\nVocê pode entrar em sua conta utilizando seu email e sua senha: " + password + 
				"\n\n http://localhost:8080/Economy/servletActivateUser?email=" + email;
		Email sender = new Email();
		sender.sendMail("ricardo.jonas.faria@gmail.com", email, "ativação de sua conta Economy", message);
	}
}
