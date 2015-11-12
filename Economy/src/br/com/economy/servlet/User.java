package br.com.economy.servlet;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.economy.DAO.UserDao;
import br.com.economy.email.Email;
import br.com.economy.entities.Usuario;


@Path("/user")
public class User {
       
	UserDao DAO = new UserDao();
    
    @PUT
	@Path("/active/{email}")
	protected Response activeUser(@PathParam("email") String email){
		DAO.activateUser(email);
		//TODO redirect to this url
		java.net.URI location = null;
		try {
			location = new java.net.URI("http://localhost:8080/Economy");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	    return Response.temporaryRedirect(location).build();

		
	}
    
    @GET
    @Path("/login/{email}/{password}")
    //TODO change this method to POST
	public int login(@PathParam("email")String email, @PathParam("password")String password, @Context HttpServletRequest request, @Context HttpServletResponse response){
//    	String email = request.getParameter("email");
//    	String password = request.getParameter("password");
    	
    	System.out.println(email + password);
		Integer active = DAO.verifyUser(email, password);
		
		System.out.println(active);
		if(active == 3){
			try {
				initSession(email, request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return active;
		
	}
	
	public void initSession( String email, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException{
		Usuario user = new Usuario();
		user = DAO.getUserByEmail(email);
		
		String userId = (user.getId()).toString();
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);	
		
		
		Cookie cookie = new Cookie("userId", userId);
		response.addCookie(cookie);
	}
	
	
	@GET
	@Path("/logout")
	public void logout( @Context HttpServletRequest request) throws ServletException, IOException
	{
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies){
        	
		        cookie.setMaxAge(0);
		        cookie.setPath("/");
		        HttpSession session = request.getSession();
		        session.invalidate();		        
		}
	}

	
	@POST
	@Path("/sendEmail")
	public void sendEmail( @PathParam("name") String name, @PathParam("email") String email,
			@PathParam("password") String password, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ServletException, IOException {
		
		String message = "Ol� " + name +
				"\n\nPara ativar sua conta clique no link abaixo."
				+ "\nVoc� pode entrar em sua conta utilizando seu email e sua senha: " + password + 
				"\n\n http://localhost:8080/Economy/servletActivateUser?email=" + email;
		Email sender = new Email();
		sender.sendMail("ricardo.jonas.faria@gmail.com", email, "ativa��o de sua conta Economy", message);
	}
	
	@POST
	@Path("/create")
	public int create(@PathParam("email") String email, @PathParam("nome") String name, @PathParam("senha") String password) throws ServletException, IOException 
	{
		
		int confirm = DAO.verifyEmail(email);
		if(confirm == 0){
			Usuario usuario = new Usuario();
			usuario.setNome(name);
			usuario.setEmail(email);
			usuario.setSenha(password);
			usuario.setAtivo(false);
			usuario.setSaldo(0f);
			
			DAO.Insert(usuario);
			return 1;						//it can save the user on database
		}
		else{
			return 0;						// it cann't save
		}
	}
	
	
	
	
	@GET
	@Path("/sale")
	public float getSale(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException 
	{
		UserDao dao = new UserDao();
		int user =0;
		Cookie cookies[] = request.getCookies();
		
		for(Cookie cookie : cookies){
		    if("userId".equals(cookie.getName())){
		        user = Integer.parseInt(cookie.getValue());
		    }
		}
		System.out.println(user);
		Float total = dao.getTotal(user);
		
		return total;
	}
}