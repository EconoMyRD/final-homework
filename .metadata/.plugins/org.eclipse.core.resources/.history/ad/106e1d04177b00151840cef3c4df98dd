package br.com.economy.DAO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.economy.entities.ModelDetailedGraphic;
import br.com.economy.entities.ModelQuery;
import br.com.economy.entities.Transacao;
import br.com.economy.util.HibernateUtil;

import com.google.gson.Gson;

public class TransactionDAO 
{
EntityManager em = HibernateUtil.getEntityManager();

	
	public String getDataForGraphic(Date dateStart, Date dateEnd, int category, int user){
			
			Query query = em.createNativeQuery("select  sum(t.valor) as value, s.nome as name, "
				+ " s.subcategoria_id as subcategoria, s.categoria_id as categoria "
				+ " from transacao t "
				+ " join subcategoria s "
				+ " on t.subcategoria = s.subcategoria_id "
				+ " join usuario u "
				+ " on t.usuario = u.usuario_id "
				+ " where s.categoria_id = ? "
				+ " and u.usuario_id = ?"
				+ " and t.data_transacao between ? and ? "
				+ " group by s.nome, s.subcategoria_id, s.categoria_id");
		
		//	System.out.println(user);
		query.setParameter(1, category);
		query.setParameter(2, user);			//catch  user from cookie
		query.setParameter(3, dateStart);
		query.setParameter(4, dateEnd);
		
		List<Object[]> list = new ArrayList<Object[]>();
		list = query.getResultList();
		//System.out.println(list.size());
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ModelQuery> modelQuery = new ArrayList<ModelQuery>();
		
		for (int i =0 ;i< list.size(); i++) {
			float value = Float.parseFloat(list.get(i)[0].toString());
			String name = list.get(i)[1].toString();
			int categoria = Integer.parseInt(list.get(i)[3].toString());
			int subcategory = Integer.parseInt(list.get(i)[2].toString());

			ModelQuery model = new ModelQuery(value, name, subcategory, categoria);
			//System.out.println(date);
			modelQuery.add(model);
		}
		
		
		Gson gson = new  Gson();
		String json = gson.toJson(modelQuery);
		System.out.println(json);
		return json;
	}
	
	
	public String getDataForDetailedGraphic(Date dateS, Date dateE, String subcategory, int user){
		System.out.println( dateS.toString() + dateE.toString() + subcategory);
		Query query = em.createNativeQuery("select t.valor as value, t.data_transacao as date "
				+ " from transacao t "
				+ " join subcategoria s "
				+ " on t.subcategoria = s.subcategoria_id "
				+ " join usuario u "
				+ " on u.usuario_id = t.usuario "
				+ " where s.nome = ? "
				+ " and u.usuario_id = ?"
				+ " and t.data_transacao between ? and ? ");
		
		System.out.println(user);
		query.setParameter(1, subcategory);
		query.setParameter(2, user);   //get user from cookie
		query.setParameter(3, dateS);
		query.setParameter(4, dateE);
		
		List<Object[]> list = new ArrayList<Object[]>();
		list = query.getResultList();
		//System.out.println(list.size());
		
		List<ModelDetailedGraphic> modelList = new ArrayList<ModelDetailedGraphic>();
		
		for (int i =0 ;i< list.size(); i++) {
			float value = Float.parseFloat(list.get(i)[0].toString());
			String dateString= list.get(i)[1].toString();
				
			ModelDetailedGraphic model = new ModelDetailedGraphic(dateString, value) ;
			modelList.add(model);
		};		
		
		Gson gson = new  Gson();
		String json = gson.toJson(modelList);
		System.out.println(json);
		return json;
	}
		
		
	public void Insert(Transacao transacao)
	{
		em.getTransaction().begin();
		em.persist(transacao);
		em.getTransaction().commit();
	}

	public void Update(Transacao transacao)
	{
		em.getTransaction().begin();
		em.merge(transacao);
		em.getTransaction().commit();
	}

	public Transacao GetTransacaoById(Integer idtransacao)
	{
		Transacao transacao = em.find(Transacao.class, idtransacao);
		return transacao;
	}

	
	public TransactionDAO() 
	{
	}


	public boolean verifyCategory(int category) {
		Query query = em.createNativeQuery("select c.categoria_id from categoria c where c.tipo = 1");	//retorna as categorias de credito
		
		List<Integer> list=  new ArrayList<Integer>();
		list = query.getResultList();
		
		if(list.contains(category)){
			return true;
		}
		else{
			return false;			
		}
	}
}









