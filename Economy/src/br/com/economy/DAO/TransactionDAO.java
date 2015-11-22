package br.com.economy.DAO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import br.com.economy.entities.Transacao;
import br.com.economy.model.ModelDataTable;
import br.com.economy.model.ModelDetailedGraphic;
import br.com.economy.model.ModelGraphicGeral;
import br.com.economy.model.ModelQuery;
import br.com.economy.model.ModelTableGeral;
import br.com.economy.util.HibernateUtil;

public class TransactionDAO 
{
EntityManager em = HibernateUtil.getEntityManager();

	public String getDataForGraphic(Date dateStart, Date dateEnd, int category, int user){
			if(category > 0){
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
			else{
			Query query = em.createNativeQuery("select sum(t.valor) as value, c.nome as name from transacao t "
					+ " join subcategoria s on t.subcategoria = s.subcategoria_id "
					+ " join categoria c on s.categoria_id = c.categoria_id"
					+ " where t.usuario = ? and t.data_transacao between ? and ?"
					+ " group by c.nome");
			
				query.setParameter(1, user);			
				query.setParameter(2, dateStart);
				query.setParameter(3, dateEnd);
			
				List<Object[]> list = new ArrayList<Object[]>();
				list = query.getResultList();
				//System.out.println(list.size());
				
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				List<ModelGraphicGeral> modelQuery = new ArrayList<ModelGraphicGeral>();
				
				for (int i =0 ;i< list.size(); i++) {
					float value = Float.parseFloat(list.get(i)[0].toString());
					String name = list.get(i)[1].toString();
					
					ModelGraphicGeral model = new ModelGraphicGeral(name ,value);
					//System.out.println(date);
					modelQuery.add(model);
				}
				
				Gson gson = new  Gson();
				String json = gson.toJson(modelQuery);
				System.out.println(json);
				return json;
			}
	}
	
	
	
	public String getDataForTable(Date dateStart, Date dateEnd, int category, int user){
		if(category > 0){
			Query query = em.createNativeQuery("select  sum(t.valor) as value, s.nome as name, c.nome as nameCat,"
					+ " t.descricao as description, t.data_transacao as date,	s.subcategoria_id as subcategory, "
					+ " s.categoria_id as category from transacao t	join subcategoria s on t.subcategoria = s.subcategoria_id "
					+ " join usuario u on t.usuario = u.usuario_id join categoria c	on c.categoria_id = s.categoria_id	"
					+ " where s.categoria_id = ? and u.usuario_id = ?	and t.data_transacao between ? and ? "
					+ " group by s.nome, s.subcategoria_id, s.categoria_id, t.data_transacao, t.descricao,c.nome;");
			
			query.setParameter(1, category);
			query.setParameter(2, user);
			query.setParameter(3, dateStart);
			query.setParameter(4, dateEnd);
			
			List<Object[]> list = new ArrayList<Object[]>();
			list = query.getResultList();
			
			List<ModelDataTable> dataTable = new ArrayList<ModelDataTable>();
			
			
			
			for (int i =0 ;i< list.size(); i++) {
				float value = Float.parseFloat(list.get(i)[0].toString());
				String name = list.get(i)[1].toString();
				String nameCat = list.get(i)[2].toString();
				String description = list.get(i)[3].toString();
				String date = list.get(i)[4].toString();
				int subcategory = Integer.parseInt(list.get(i)[5].toString());
				int cat = Integer.parseInt(list.get(i)[6].toString());
				
				ModelDataTable model = new ModelDataTable(value,name,subcategory,cat,date,description,nameCat);
				
				dataTable.add(model);
			}
			
			Gson gson = new  Gson();
			String json = gson.toJson(dataTable);
			System.out.println(json.toString());
			return json;
		}
		else{
			Query query = em.createNativeQuery("select  t.valor as value, s.nome as name, c.nome as nameCat,"
					+ " t.descricao as description, t.data_transacao as date "
					+ "  from transacao t	join subcategoria s on t.subcategoria = s.subcategoria_id "
					+ " join usuario u on t.usuario = u.usuario_id join categoria c	on c.categoria_id = s.categoria_id	"
					+ " where  u.usuario_id = ?	and t.data_transacao between ? and ? ");
			
			query.setParameter(1, user);
			query.setParameter(2, dateStart);
			query.setParameter(3, dateEnd);
			
			List<Object[]> list = new ArrayList<Object[]>();
			list = query.getResultList();
			
			List<ModelTableGeral> dataTable = new ArrayList<ModelTableGeral>();
			
		
			
			for (int i =0 ;i< list.size(); i++) {
				float value = Float.parseFloat(list.get(i)[0].toString());
				String subcategory = list.get(i)[1].toString();
				String cat = list.get(i)[2].toString().toString();
				String description = list.get(i)[3].toString();
				String date = list.get(i)[4].toString();
				
				ModelTableGeral model = new ModelTableGeral(subcategory,cat,value,description,date);
				
				dataTable.add(model);
			}
			
			Gson gson = new  Gson();
			String json = gson.toJson(dataTable);
			System.out.println(json.toString());
			return json;
		}
	}
	
	
	public String getDataForDetailedGraphic(Date dateS, Date dateE, String subcategory, int user){
		System.out.println( dateS.toString() + dateE.toString() + subcategory);
		//CategoriaDao dao = new CategoriaDao();
		/*
		//when it's showing the general graphic and it'll create the detailed graphic, 
		//it's needed return the data for the normal graphic not the detailed graphic;
		//this algorithm will verify if ti's coming from the general graphic
		String categories = dao.getCategories();
		
		JsonParser parser = new JsonParser();
		JsonArray jsonCat = (JsonArray)parser.parse(categories);
		
		String dataForGraphic = null;
		if(categories.contains(subcategory)){
			for (JsonElement jsonElement : jsonCat) {
				JsonObject obj = jsonElement.getAsJsonObject();
				if(obj.get("nome").getAsString().equals(subcategory)){
					System.out.println(obj.get("id").getAsInt());
					dataForGraphic = getDataForGraphic(dateS, dateE, obj.get("id").getAsInt(), user);
				}
			};
			return dataForGraphic;
		}
		else{
		*/
			Query query = em.createNativeQuery("select t.valor as value, t.data_transacao as date, s.nome as subcategory"
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
				String subcat = list.get(i)[2].toString();
					
				ModelDetailedGraphic model = new ModelDetailedGraphic(dateString, value, subcat) ;
				modelList.add(model);
			};		
			
			Gson gson = new  Gson();
			String json = gson.toJson(modelList);
			System.out.println(json);
			return json;
		//}
	}
	
	
	public String getDataForDetailedTable(Date dateStart, Date dateEnd, String subcategory, int user) {
		Query query = em.createNativeQuery("select  t.valor as value, s.nome as name, c.nome as nameCat,"
				+ " t.descricao as description, t.data_transacao as date,	s.subcategoria_id as subcategory, "
				+ " s.categoria_id as category from transacao t	join subcategoria s on t.subcategoria = s.subcategoria_id "
				+ " join usuario u on t.usuario = u.usuario_id join categoria c	on c.categoria_id = s.categoria_id	"
				+ " where s.nome = ? and u.usuario_id = ?	and t.data_transacao between ? and ? ");
		
		query.setParameter(1, subcategory);
		query.setParameter(2, user);
		query.setParameter(3, dateStart);
		query.setParameter(4, dateEnd);
		
		List<Object[]> list = new ArrayList<Object[]>();
		list = query.getResultList();
		
		List<ModelDataTable> dataTable = new ArrayList<ModelDataTable>();
		
		for (int i =0 ;i< list.size(); i++) {
			float value = Float.parseFloat(list.get(i)[0].toString());
			String name = list.get(i)[1].toString();
			String nameCat = list.get(i)[2].toString();
			String description = list.get(i)[3].toString();
			String date = list.get(i)[4].toString();
			int subcat = Integer.parseInt(list.get(i)[5].toString());
			int cat = Integer.parseInt(list.get(i)[6].toString());
			
			ModelDataTable model = new ModelDataTable(value,name,subcat,cat,date,description,nameCat);
			
			dataTable.add(model);
		}
		
		Gson gson = new  Gson();
		String json = gson.toJson(dataTable);
		System.out.println(json.toString());
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
		try {
			em.getTransaction().begin();
			em.merge(transacao);
			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
		}
	}
	
	public void Delete(Transacao transacao)
	{
		try {
			em.getTransaction().begin();
			em.remove(transacao);
			em.getTransaction().commit();
		}
		catch (Exception e) 
		{
			em.getTransaction().rollback();
		}
		
	}

	public Transacao GetTransacaoById(Integer idtransacao)
	{
		Transacao transacao = em.find(Transacao.class, idtransacao);
		return transacao;
	}

	public ArrayList<Transacao> GetTransacaoByUser(Integer idUser)
	{
		Query query = em.createNativeQuery("SELECT * FROM transacao t where t.usuario = ?");
		query.setParameter(1, idUser);
		
	    return (ArrayList<Transacao>) query.getResultList();
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









