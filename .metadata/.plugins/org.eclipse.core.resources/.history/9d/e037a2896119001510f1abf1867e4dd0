package br.com.economy.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.economy.DAO.TransactionDAO;
import br.com.economy.DAO.UsuarioDao;
import br.com.economy.entities.Transacao;

public class ServletTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TransactionDAO transacaoDAO = new TransactionDAO();
	UsuarioDao usuarioDAO =  new UsuarioDao();
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{

	
		// get values from the parameter request
		String description = request.getParameter("description");
		String valueString = request.getParameter("value");
		String date_transactionString = request.getParameter("date_transaction");
		int category =Integer.parseInt(request.getParameter("category"));
		Date date_register = new Date();
		Date date_transaction = new Date();
		float value;
		int subcategory;
		int userId=0;

		Cookie cookies[] = request.getCookies();
		if (cookies != null)
		{
			//int user = Integer.parseInt(cookie[0].userid.getValue());	
			
			for(Cookie cookie : cookies){
			    if("userId".equals(cookie.getName())){
			        userId = Integer.parseInt(cookie.getValue());
			    }
			}
			System.out.println("user =" + userId);
			//int user = 1;

			//System.out.println("date_transactionString" + date_transactionString);
			try 
			{
				// conversions

				value = Float.parseFloat(valueString);
				subcategory = Integer.parseInt(request.getParameter("subcategory"));

				//			out.println(description + " " + value + " "
				//					+ date_transactionString);
				// conversion for date

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
				date_transaction = sdf.parse(date_transactionString);
//				System.out.println("date_transacrion" + date_transaction);
//				System.out.println("date_register" + date_register);
//				System.out.println(subcategory);

				persistOnDataBase(value,date_transaction, date_register, description,subcategory,userId, category);

			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			response.sendRedirect("../WebContent/html/index.html");
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

	public ServletTransaction() {
		super();

	}
}
