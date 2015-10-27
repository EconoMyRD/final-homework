package br.com.economy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.economy.DAO.TransactionDAO;

public class ServletDetailedGraphic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private TransactionDAO DAO =  new TransactionDAO();
    
    public ServletDetailedGraphic() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		Date dateEnd = new Date();
		Date dateStart = new Date();
		String subcategory = "";
		
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
		subcategory = request.getParameter("subcategory");
		try {
			dateStart = sdf.parse(request.getParameter("dateStart"));
			dateEnd = sdf.parse(request.getParameter("dateEnd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String jsonGraphic = DAO.getDataForDetailedGraphic(dateStart, dateEnd, subcategory, userId);
		
		String jsonTable = DAO.getDataForDetailedTable(dateStart, dateEnd, subcategory, userId);
		
		List<String> list = new ArrayList<String>();
		list.add(jsonGraphic);
		list.add(jsonTable);
		
		Gson gson =  new Gson();
		String json = gson.toJson(list);
		out.write(json);
	}

}
