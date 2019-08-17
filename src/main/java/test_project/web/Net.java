package test_project.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.client.RestTemplate;

public class Net {
	
	public static Object getData(String url, Class responseType) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, responseType);
	}
	
	public static void uploadToFTP(String fileName, String server, String user, String password) {
		FTPClient client = new FTPClient();
		
		// Read the file from resources folder.
        try (InputStream is = new FileInputStream(new File("files/"+fileName))) {
            client.connect(server);
            client.login(user, password);

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
    }
	

}
