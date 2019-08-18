package test_project.web;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test_project.dto.Marketplace;
import test_project.dto.list.MarketplaceList;

public class NetTest {
	
	@Before
	public void before() {
		 try {
	         File file = new File("files/test.txt");
	         file.createNewFile();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		
	}

	@Test
	public void uploadToFtpTest() {

		String host = null;
		String user = null;
		String pw = null;
		
		try (InputStream input = new FileInputStream("resources/ftp.properties")) {
			
			
            Properties prop = new Properties();
            prop.load(input);
            
            host=prop.getProperty("ftp.host");
            user=prop.getProperty("ftp.user");
            pw=prop.getProperty("ftp.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		assertTrue(Net.uploadToFTP("test.txt", host, user, pw));
		
		FTPClient client = new FTPClient();
		
        try {
            client.connect(host);
            client.login(user, pw);
            
            assertTrue(client.deleteFile("test.txt"));
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	@Test
	public void getDataTest() {
		MarketplaceList mpList = (MarketplaceList)Net.getData("https://my.api.mockaroo.com/market_test.json?key=d2cfc040", MarketplaceList.class);

		assertEquals("result",new Marketplace(1,"test1").toString(),mpList.get(0).toString());
		assertEquals("result",new Marketplace(2,"test2").toString(),mpList.get(1).toString());
	}
	
	@After
	public void after() {
		File file = new File("files/test.txt");
		file.delete();
	}

}
