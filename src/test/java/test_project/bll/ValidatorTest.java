package test_project.bll;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test_project.dal.DAO;
import test_project.dto.InvalidListing;
import test_project.dto.Listing;
import test_project.dto.ListingStatus;
import test_project.dto.Location;
import test_project.dto.Marketplace;
import test_project.dto.list.ListingList;

public class ValidatorTest {
	
    @Before
    public void before() {
    	
	}
	 
	
	@Test
	public void validatorTest() {
		Listing testListing = new Listing(UUID.fromString("397d4df7-4363-4209-8878-2afc8e5b33c5"),"Description.","Title.",
				UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"),new Double(595.89).floatValue(),"HUF",23,1,1,new Date(),"test@test.net");
		Marketplace testMarketplace = new Marketplace(1, "Test marketplace");
		Location testLocation = new Location(UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"), "test", "test", "test", "test", "test", "test","test");
		ListingStatus listingStatus = new ListingStatus(1, "test status");
	 
		SessionFactory sf = DAO.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(testMarketplace);
		session.save(testLocation);
		session.save(listingStatus);
		session.save(testListing);
		session.getTransaction().commit();
		
		
		ListingList testList = new ListingList();
		
		for(int i=0;i<12;i++)
		testList.add(new Listing(UUID.fromString("b5be4bcd-6232-4bfd-ab00-2ad23602dca5"),"Description.","Title.",
				UUID.fromString("52ea143e-cb45-43af-981e-92cedb89f7a8"),new Double(595.89).floatValue(),"HUF",23,1,1,new Date(),"test@test.net"));
		
		testList.get(1).setId(UUID.fromString("397d4df7-4363-4209-8878-2afc8e5b33c5"));
		testList.get(2).setDescription(null);
		testList.get(3).setTitle(null);
		testList.get(4).setLocationId(UUID.fromString("397d4df7-4363-4209-8878-2afc8e5b33c5"));
		testList.get(5).setListingPrice(new Double(595.8922312).floatValue());
		testList.get(6).setCurrency("DS");
		testList.get(7).setQuantity(0);
		testList.get(8).setListingStatus(-1);
		testList.get(9).setMarketplace(-1);
		testList.get(10).setUploadTime(null);
		testList.get(11).setOwnerEmailAddress("test.test@hu"); 
		
		String[] result = {	" ID needs to be unique UUID.",
							" Description cannot be null",
							" Title cannot be null",
							" Violates location_id foreign key.",
							" Listing_price needs to be greater than 0 and can only have 2 decimals.",
							" Currency lenght needs to be 3.",
							" Quantity needs to be greater than 0.",
							" Violates listing_status_id foreign key.",
							" Violates marketplace_id foreign key.",
							" Upload_time cannot be null.",
							" Email address needs to be valid."};

		
		List<InvalidListing> invalidList= Validator.validateListing(testList);

		for(int i=0; i<11; i++)
			assertEquals("result",result[i],invalidList.get(i).getInvalidField());
		
		
		session.beginTransaction();
		session.delete(testListing);
		session.delete(testMarketplace);
		session.delete(testLocation);
		session.delete(listingStatus);
		session.getTransaction().commit();
		session.close();
		}
	
	@After
	public void after() {
			
		DAO.close();
	}
}


