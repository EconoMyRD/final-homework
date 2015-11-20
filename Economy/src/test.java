import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.economy.DAO.TransactionDAO;

public class test {
	public static void main(String[] args) {
		TransactionDAO dao = new TransactionDAO();
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
		
		Date dates =  new Date();
//		dates.setDate(1);
//		dates.setMonth(9);
//		dates.setYear(2015);
		Date datee =  new Date();
		try {
			dates = sdf.parse("1/9/2015");
			datee = sdf.parse("30/11/2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(dao.getDataForTable(dates, datee, 0, 1));
	}
}
