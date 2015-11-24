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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.economy.DAO.UserDao;
import br.com.economy.email.Email;
import br.com.economy.entities.Usuario;


@Path("/user")
public class User {
       
	UserDao DAO = new UserDao();
    
    @GET
	@Path("/active/{email}")
	public Response activeUser(@PathParam("email") String email){
		DAO.activateUser(email);
		//TODO redirect to this url
		java.net.URI location = null;
		try {
			location = new java.net.URI("http://economybr.cf");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	    return Response.temporaryRedirect(location).build();

		
	}
    
    @GET
    @Path("/login/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    //TODO change this method to POST
	public String login(@PathParam("email")String email, @PathParam("password")String password, @Context HttpServletRequest request, @Context HttpServletResponse response){
//    	String email = request.getParameter("email");
//    	String password = request.getParameter("password");
    	
    	System.out.println(email + password);
		Integer active = DAO.verifyUser(email, password);
		
		System.out.println(active);
		Integer userId = null;
		if(active  == 3){
			Usuario user = DAO.getUserByEmail(email);
			userId = user.getId();
		}
		
		String jsonString = "{\"active\": " + active + ", \"userId\": " + userId + "}";
		System.out.println(jsonString);
		Gson gson  = new Gson();

		String json = gson.toJson(jsonString);
		System.out.println(json);
//		if(active == 3){
//			try {
//				initSession(email, request, response);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		return json;
	}
    
    
	
   /* 
	public void initSession( String email, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException{
		Usuario user = new Usuario();
		user = DAO.getUserByEmail(email);
		
		String userId = (user.getId()).toString();
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);	
		
		
		Cookie cookie = new Cookie("userId", userId);
		response.addCookie(cookie);
	}
	
	*/

	
	@POST
	@Path("/sendEmail")
	public void sendEmail( String data) throws ServletException, IOException {
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(data);
		
		String message = "Olá " + json.get("nome").getAsString() +
				"\n\nPara ativar sua conta clique no link abaixo."
				+ "\nVocê pode entrar em sua conta utilizando seu email e sua senha: " + json.get("senha").getAsString() + 
				"\n\n http://economybr.cf/resources/user/active/" + json.get("email").getAsString();
		Email sender = new Email();
		sender.sendMail("ricardo.jonas.faria@gmail.com", json.get("email").getAsString(), "ativação de sua conta Economy", message);
	}
	
	@POST
	@Path("/create")
	public int create(String user) throws ServletException, IOException 
	{

		System.out.println(user);
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(user);
		String email = json.get("email").getAsString();
		String name = json.get("nome").getAsString();
		String password = json.get("senha").getAsString();
		
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
	@Path("/sale/{userId}")
	public float getSale(@PathParam("userId")int user, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException 
	{
//		UserDao dao = new UserDao();
//		int user =0;
//		Cookie cookies[] = request.getCookies();
//		
//		for(Cookie cookie : cookies){
//		    if("userId".equals(cookie.getName())){
//		        user = Integer.parseInt(cookie.getValue());
//		    }
//		}
		 
		System.out.println(user);
		Float total = DAO.getTotal(user);
		
		return total;
	}
}
