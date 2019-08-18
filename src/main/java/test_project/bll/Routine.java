package test_project.bll;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test_project.IO.Export;
import test_project.IO.Import;
import test_project.dal.DAO;
import test_project.dto.InvalidListing;
import test_project.dto.list.*;
import test_project.main.Application;
import test_project.web.Net;

public class Routine {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@SuppressWarnings("unchecked")
	public static void synchData() {
		MarketplaceList marketplaceList = Import.getMarketplaceList();
		LocationList locationList = Import.getLocationList();
		ListingStatusList statusList = Import.getListingStatusList();
		ListingList listingList = Import.getListingList();

		DAO.saveOrUpdate((List<Object>)(Object)marketplaceList);
		DAO.saveOrUpdate((List<Object>)(Object)locationList);
		DAO.saveOrUpdate((List<Object>)(Object)statusList);
		
		List<InvalidListing> invalidList = Validator.validateListing(listingList);
		Export.writeToCSV(invalidList);
		log.info("File importLog.CSV created.");
		
		DAO.saveOrUpdate((List<Object>)(Object)listingList);
		}
	
	public static void createAndUploadReport() {
		String host = null;
		String user = null;
		String pw = null;
		
		List<Object[]> stringList = DAO.getReportData("2017/01", "2019/07");
		Export.createReportJSON(stringList);
		log.info("Report created.");
		
        try (InputStream input = new FileInputStream("resources/ftp.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            
            host=prop.getProperty("ftp.host");
            user=prop.getProperty("ftp.user");
            pw=prop.getProperty("ftp.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		if(Net.uploadToFTP("report.json", host, user, pw))
			log.info("Report uploaded to the FTP server.");
		else
			log.info("Could not login into the FTP server. Report wasn't uploaded.");
	}
	
	public static void commander() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String cmd = "";
		log.info("Entered commander");
		log.info("Type \"help\" to get the list of commands");
		inputloop: 
			while(!cmd.equals("exit")) {
				try {
					cmd=reader.readLine();
					switch (cmd) {
						case"help":
							log.info("list of commands: synchronize, report, delete_all, exit");
							break;
						case"synchronize":
							synchData();
							log.info("Data synchronized.");
							break;
						case"report":
							createAndUploadReport();
							log.info("Report created and uploaded.");
							break;
						case"delete_all":
							DAO.deleteAll();
							log.info("All tables truncated.");
							break;
						case"exit":
							log.info("Exiting commander.");
					break inputloop;
				}
			} catch (IOException e) {
	            e.printStackTrace();
			}
				
		}
	}
	
	
}
