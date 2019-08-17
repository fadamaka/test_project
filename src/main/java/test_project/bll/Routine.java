package test_project.bll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import test_project.IO.Export;
import test_project.IO.Import;
import test_project.dal.DAO;
import test_project.dto.InvalidListing;
import test_project.dto.list.*;
import test_project.web.Net;

public class Routine {

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
		
		DAO.saveOrUpdate((List<Object>)(Object)listingList);
		}
	
	public static void createAndUploadReport() {
		List<Object[]> stringList = DAO.getReportData("2017/01", "2019/0e7");
		Export.createReportNiceJSON(stringList);
		Net.uploadToFTP("report.json", "localhost", "test_project", "12345");
	}
	
	public static void commander() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String cmd = "";
		System.out.println();
		inputloop: 
			while(!cmd.equals("exit")) {
				try {
					cmd=reader.readLine();
					switch (cmd) {
						case"help":
							System.out.println("list of commands: synchronize, exit");
							break;
						case"synchronize":
							synchData();
							System.out.println("Data synchronized.");
							break;
						case"report":
							createAndUploadReport();
							System.out.println("Report created and uploaded.");
							break;
						case"deleteAll":
							DAO.deleteAll();
							System.out.println("All tables truncated.");
							break;
						case"exit":
							System.out.println("exiting");
					break inputloop;
				}
			} catch (IOException e) {
	            e.printStackTrace();
			}
				
		}
	}
}
