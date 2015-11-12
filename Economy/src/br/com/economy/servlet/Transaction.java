package br.com.economy.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;

import br.com.economy.DAO.TransactionDAO;
import br.com.economy.DAO.UserDao;
import br.com.economy.entities.Transacao;

@Path("/transaction")
public class Transaction {

	TransactionDAO transacaoDAO = new TransactionDAO();
	UserDao usuarioDAO =  new UserDao();
	
	@POST
	public void setTransaction(@PathParam("description") String description, @PathParam("value") float value,
			@PathParam("date_transaction") String date_transactionString, @PathParam("category") int category,
			@PathParam("subactegory") int subcategory, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		Date date_register = new Date();
		Date date_transaction = new Date();
		int userId=0;

		Cookie cookies[] = request.getCookies();
		if (cookies != null)
		{
			
			for(Cookie cookie : cookies){
			    if("userId".equals(cookie.getName())){
			        userId = Integer.parseInt(cookie.getValue());
			    }
			}
			System.out.println("user =" + userId);

			try 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
				date_transaction = sdf.parse(date_transactionString);

				persistOnDataBase(value,date_transaction, date_register, description,subcategory,userId, category);

			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			response.sendRedirect("../html/index.html");
		}
	}

	private void persistOnDataBase(float value,Date date_transaction,Date date_register,
			String description, int subcategory,int user, int category) {

		// set the object transaction
		Transacao transaction = new Transacao();
		transaction.setValor(value);
		transaction.setDataTransacao(date_transaction);
		transaction.setDataRegistro(date_register);
		transaction.setDescricao(description);
		transaction.setSubcategoriaId(subcategory);
		//instanciar objeto subcategoria com dados do banco para setar este atributo 
		transaction.setUsuarioId(user);
		//System.out.println("lets go insert");
		
		//persist on data base
		if(transacaoDAO.verifyCategory(category)){		//true = +
			usuarioDAO.setTotalMore(value, user);
		}
		else{
			usuarioDAO.setTotalMinus(value, user);
		}
		//persist on data base
		transacaoDAO.Insert(transaction);

	}

	
	@GET
	@Path("/relatory")
	public String relatory(@QueryParam("dateStart") String dateStart, @QueryParam("dateEnd") String dateEnd,
			@QueryParam("category") int category, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(dateEnd + dateStart);
		TransactionDAO DAO = new TransactionDAO();
		
		Date dateE = new Date();
		Date dateS = new Date();
		
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
			dateS = sdf.parse(dateStart);
			dateE = sdf.parse(dateEnd);
			
		} catch (ParseException e) {
			//impossible have wrong parameters
			e.printStackTrace();
		}
//		System.out.println(category);
		System.out.println(dateStart);
		System.out.println(dateEnd);
		String jsonGraphic = DAO.getDataForGraphic(dateS, dateE, category, userId);
		
		String jsonTable = DAO.getDataForTable(dateS, dateE, category, userId);
		
		Gson gson = new Gson();
		List<String> list = new  ArrayList<String>();
		list.add(jsonGraphic);
		list.add(jsonTable);
		
		String json = gson.toJson(list);
		return json;
	}
	
	
	
	@GET
	@Path("/relatory/detailed")
	public String detailedRelatory(@PathParam("dateStart") String dateStart, @PathParam("dateEnd") String dateEnd,
			@PathParam("subcategory") String subcategory,
			@Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws ServletException, IOException {
		
		TransactionDAO DAO = new TransactionDAO();
		
		Date dateE = new Date();
		Date dateS = new Date();
		
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
		
		SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
		try {
			dateS = sdf.parse(dateStart);
			dateE = sdf.parse(dateEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String jsonGraphic = DAO.getDataForDetailedGraphic(dateS, dateE, subcategory, userId);
		
		String jsonTable = DAO.getDataForDetailedTable(dateS, dateE, subcategory, userId);
		
		List<String> list = new ArrayList<String>();
		list.add(jsonGraphic);
		list.add(jsonTable);
		
		Gson gson =  new Gson();
		String json = gson.toJson(list);
		return json;
	}

}
