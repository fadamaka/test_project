package test_project.dal;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;


import java.util.List;
import java.util.stream.Collectors;

public class DAO {
	
	private static SessionFactory sessionFactory=configure();
	
	 public static void close() {
		    if (sessionFactory != null) 
		            sessionFactory.close();
		}
	
	private static SessionFactory configure() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
				.configure("hibernate.cfg.xml").build();

		// Create a metadata sources using the specified service registry.
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

	
		return metadata.getSessionFactoryBuilder().build();
	}
	
	public static List<Integer> getIdListInteger (String tableName) {
		Session session = sessionFactory.openSession();
		
		List<BigDecimal> bigDecimalList = session.createNativeQuery("select id from test_project."+tableName).list();
		List<Integer> integerList = bigDecimalList.stream().map(BigDecimal::intValue).collect(Collectors.toList());
		
		
		session.close();
		return integerList;
	}
	
	public static List<String> getIdListUUIDAsString (String tableName) {
		Session session = sessionFactory.openSession();
		
		List<String> integerList = session.createNativeQuery("select id::::character varying from test_project."+tableName).list();
		
		
		session.close();
		return integerList;
	}
	
	public static List<Object> getTableAsList(String tableName, Class c){
		Session session = sessionFactory.openSession();
		
		List<Object> list = session.createNativeQuery("select * from test_project."+tableName, c).list();
		
		session.close();
		
		return list;
	}
	
	public static List<Object[]> getReportData(String start,String end){
		Session session = sessionFactory.openSession();
		Query q = session.getNamedQuery("report");
		q.setParameter(1, Integer.parseInt(start.substring(0, 4)));
		q.setParameter(2, Integer.parseInt(end.substring(0, 4)));
		q.setParameter(3, start);
		q.setParameter(4, end);
		List<Object[]> reportList=q.list();
		session.close();
		return reportList;
	}
	
	public static void saveOrUpdate(List<Object> list) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(Object i : list)
			session.saveOrUpdate(i);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void deleteAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getNamedQuery("deleteAll").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public static Boolean isConnected() {
		try {
		if(sessionFactory!=null)
		return sessionFactory.openSession().isConnected();}
		catch(Exception e) {
			//e.printStackTrace();
			return false;
		}
		return false;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		DAO.sessionFactory = sessionFactory;
	}
}
