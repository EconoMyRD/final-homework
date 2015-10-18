package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.UsuarioDao;
import br.com.economy.entities.Usuario;

public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UsuarioDao dao = new UsuarioDao();
    
    
    public ServletCliente() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		int confirm = dao.verifyEmail(email);
		if(confirm == 0){
			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setAtivo(false);
			usuario.setSaldo(0f);
			
			dao.Insert(usuario);
			out.write("1");						//it can save the user on database
		}
		else{
			out.write("0");						// it cann't save
		}
	}
}
