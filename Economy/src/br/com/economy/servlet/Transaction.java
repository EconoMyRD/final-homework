package br.com.economy.servlet;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xml.internal.serializer.TransformStateSetter;

import br.com.economy.DAO.TransactionDAO;
import br.com.economy.DAO.UserDao;
import br.com.economy.entities.Transacao;
import br.com.economy.model.ModelAllTransaction;
import jdk.nashorn.internal.objects.annotations.Getter;

@Path("/transaction")
public class Transaction {

	TransactionDAO transacaoDAO = new TransactionDAO();
	UserDao usuarioDAO =  new UserDao();
	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	public void setTransaction( String j) throws IOException {

		System.out.println(j);
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(j);
		
		
		String description = json.get("description").getAsString();
		float value = json.get("value").getAsFloat();
		String date_transactionString = json.get("date_transcation").getAsString();
		int category = json.get("category").getAsInt();
		int subcategory = json.get("subcategory").getAsInt();
		int userId = json.get("user").getAsInt();
		
		System.out.println(value);
		Date date_register = new Date();
		Date date_transaction = new Date();
		
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

	private void persistOnDataBase(float value,Date date_transaction, Date date_register, 
			String description,int subcategory,int user, int category) {

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
		
		//TODO verify for the subcategory 
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
			@QueryParam("category") int category, @QueryParam("userId") int userId, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(dateEnd + dateStart);
		TransactionDAO DAO = new TransactionDAO();
		
		Date dateE = new Date();
		Date dateS = new Date();
		
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
	public String detailedRelatory(@QueryParam("dateStart") String dateStart, @QueryParam("dateEnd") String dateEnd,
			@QueryParam("subcategory") String subcategory, @QueryParam("userId") int userId,
			@Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws ServletException, IOException {
		
		TransactionDAO DAO = new TransactionDAO();
		
		Date dateE = new Date();
		Date dateS = new Date();
		
		
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
	
	@PUT
	@Path("/update")
	//@Consumes("aplication/json")
	public String UpdateTransaction(String transacao)
	{
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(transacao);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data_transacao = new Date();
		Date dataRegistro = new Date();
		try {
			data_transacao = format.parse(json.get("data_transacao").getAsString());
			dataRegistro = format.parse(json.get("dataRegistro").getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer id = json.get("id").getAsInt();
		int subcategoria = json.get("subcategoria").getAsInt();
		int usuarioId = json.get("usuarioId").getAsInt();
		String descricao = json.get("descricao").getAsString();
		float valor = json.get("valor").getAsFloat();
		
		Transacao transaction = new Transacao(id, subcategoria, usuarioId, data_transacao, descricao, valor, dataRegistro);
		
		transacaoDAO.Update(transaction);
		return "ok";
	}
	
	@DELETE
	@Path("/delete/{id}")
	public String DeleteTransaction(@PathParam("id") int id)
	{
		Transacao transacao = transacaoDAO.GetTransacaoById(id);
		transacaoDAO.Delete(transacao);
		return "delete ok";
	}
	
	@GET
	@Path("/getAll/user/{user}")
	public String GetByUser(@PathParam("user") int user)
	{
		Gson gson =  new Gson();
		ModelAllTransaction model = new ModelAllTransaction();
		model.setData(transacaoDAO.GetTransacaoByUser(user));
		
		return gson.toJson(model);
	}
	
	@GET
	@Path("/get/{id}")
	public String getTransactionId(@PathParam("id") int id)
	{
		Transacao transacao = transacaoDAO.GetTransacaoById(id);
		Gson gson = new Gson();
		return gson.toJson(transacao);
	}
}
