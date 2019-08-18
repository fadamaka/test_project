package test_project.dal;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test_project.dto.Listing;
import test_project.dto.ListingStatus;
import test_project.dto.Location;
import test_project.dto.Marketplace;

public class DAOTest {
	private Session session = null;
	
	@Before
	public void before() {
		session = DAO.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(new Marketplace(1,"test_1"));
		session.save(new Marketplace(2,"test_2"));
		session.save(new Location(UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), "test1", "test1", "test1", "test1", "test1", "test1","test1"));
		session.save(new Location(UUID.fromString("8770f23b-cc8a-4682-903b-2e1977658a67"), "test2", "test2", "test2", "test2", "test2", "test2","test2"));
		session.save(new ListingStatus(1, "test_1"));
		session.save(new ListingStatus(2, "test_2"));
		session.save(new Listing(	UUID.fromString("2aac60e1-df0c-46eb-bb2b-ae3a2072cb1f"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(100).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,5,1).getTime(), "test1@test.net"));

		session.save(new Listing(	UUID.fromString("b006c7d8-55f0-4421-a4bb-340df10c3d99"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(50).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,6,1).getTime(), "test2@test.net"));

		session.save(new Listing(	UUID.fromString("bbfb737c-b492-40df-bfa3-397912ffb0d1"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(60).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,7,1).getTime(), "test3@test.net"));

		session.save(new Listing(	UUID.fromString("4cdedc08-dd2d-4cd8-9b54-fefe9cac240a"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(150).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,7,1).getTime(), "test3@test.net"));

		session.save(new Listing(	UUID.fromString("01fc1794-a342-4de4-8fcd-6e8c9ecfed13"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(80).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,7,1).getTime(), "test3@test.net"));

		session.save(new Listing(	UUID.fromString("86f532c3-e8e8-4632-879f-dfa74b59b027"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(150).floatValue(), "HUF",
									3, 1, 1, new GregorianCalendar(2019,7,1).getTime(), "test2@test.net"));

		session.save(new Listing(	UUID.fromString("ea6380bd-e540-4b22-9d14-674b402589f9"), "Description", "Title", 
									UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), new Double(150).floatValue(), "HUF",
									3, 1, 2, new GregorianCalendar(2019,4,1).getTime(), "test2@test.net"));
		session.getTransaction().commit();

	}

	@Test
	public void getIdListIntegerTest() {
		List<Integer> idList = DAO.getIdListInteger("marketplace");
		assertEquals(0,1,idList.get(0));
		assertEquals(0,2,idList.get(1));
	}
	@Test
	public void getIdListUUIDasStringTest() {
		List<String> idList = DAO.getIdListUUIDAsString("location");
		assertEquals("result","52ea143e-cb45-43af-981e-92cedb89f7a8",idList.get(0));
		assertEquals("result","8770f23b-cc8a-4682-903b-2e1977658a67",idList.get(1));
	}
	@Test
	public void getIdTableAsListTest() {
		@SuppressWarnings("unchecked")
		List<Marketplace> marketplaceList = (List<Marketplace>)(Object)DAO.getTableAsList("marketplace", Marketplace.class);
		assertEquals("result",new Marketplace(1,"test_1").toString(),marketplaceList.get(0).toString());
		assertEquals("result",new Marketplace(2,"test_2").toString(),marketplaceList.get(1).toString());
	}
	
	@Test
	public void getReportDataTest() {
		List<String[]> expected = new ArrayList<String[]>();
		expected.add(new String[]{"7","6","590.00","98.33","1","150.00","150.00","test2@test.net","Total"});
		expected.add(new String[]{null,"4","440.00","110.00",null,null,null,"test3@test.net","2019/08"});
		expected.add(new String[]{null,"1","50.00","50.00",null,null,null,"test2@test.net","2019/07"});
		expected.add(new String[]{null,"1","100.00","100.00",null,null,null,"test1@test.net","2019/06"});
		expected.add(new String[]{null,null,null,null,"1","150.00","150.00","test2@test.net","2019/05"});
		expected.add(new String[]{null,null,null,null,null,null,null,null,"2019/04"});
		List<Object[]> report= DAO.getReportData("2019/04", "2019/08");
		
		for(int i=0; i<6; i++) 
			for(int j=0; j<9; j++) 
				if(report.get(i)[j]!=null)
					assertEquals("result"+"i:"+i+" j:"+j,expected.get(i)[j],report.get(i)[j].toString());
				else
					assertNull("result"+"i:"+i+" j:"+j,expected.get(i)[j]);
			
				
		
	}
	@Test
	public void deleteAllTest() {
		session.beginTransaction();
		String sql = "select count(id) from test_project.";
		assertEquals(new BigInteger("7"),session.createNativeQuery(sql+"listing").getSingleResult());
		assertEquals(new BigInteger("2"),session.createNativeQuery(sql+"marketplace").getSingleResult());
		assertEquals(new BigInteger("2"),session.createNativeQuery(sql+"listing_status").getSingleResult());
		assertEquals(new BigInteger("2"),session.createNativeQuery(sql+"location").getSingleResult());
		
		DAO.deleteAll();
		

		assertEquals(new BigInteger("0"),session.createNativeQuery(sql+"listing").getSingleResult());
		assertEquals(new BigInteger("0"),session.createNativeQuery(sql+"marketplace").getSingleResult());
		assertEquals(new BigInteger("0"),session.createNativeQuery(sql+"listing_status").getSingleResult());
		assertEquals(new BigInteger("0"),session.createNativeQuery(sql+"location").getSingleResult());

		session.getTransaction().commit();
	}
	
	@Test
	public void connectionTest() {
		assertTrue(DAO.isConnected());
	}
	

	@After
	public void after() {
		String sql = 	"delete from test_project.listing where 1=1;\r\n" + 
						"delete from test_project.marketplace where 1=1;\r\n" + 
						"delete from test_project.listing_status where 1=1;\r\n" + 
						"delete from test_project.location where 1=1;";
		session.beginTransaction();
		session.createNativeQuery(sql).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
