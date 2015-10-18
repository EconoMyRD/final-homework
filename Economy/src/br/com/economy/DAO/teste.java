package br.com.economy.DAO;

import java.text.ParseException;


public class teste {
	public static void main(String[] args) throws ParseException {
		CategoriaDao dao =  new CategoriaDao();
		String str = dao.getCategories();
		System.out.println(str);	
	}
}