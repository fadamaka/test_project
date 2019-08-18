package test_project.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.client.RestTemplate;

public class Net {
	
	@SuppressWarnings("unchecked")
	public static Object getData(String url, @SuppressWarnings("rawtypes") Class responseType) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, responseType);
	}
	
	public static Boolean uploadToFTP(String fileName, String server, String user, String password) {
		FTPClient client = new FTPClient();
		Boolean loggedIn = false;
		
		// Read the file from resources folder.
        try (InputStream is = new FileInputStream(new File("files/"+fileName))) {
            client.connect(server);
            loggedIn = client.login(user, password);
            
            if(loggedIn)
            // Store file to server
            	client.storeFile(fileName, is);
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
        return loggedIn;
    }
}
