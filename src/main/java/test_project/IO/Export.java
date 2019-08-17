package test_project.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import test_project.dto.InvalidListing;

public class Export {
	 //European countries use ";" as 
    //CSV separator because "," is their digit separator
    private static final String CSV_SEPARATOR = ",";
    public static void writeToCSV(List<InvalidListing> invalidList)
    {
        try
        {	File file = new File("files/importLog.csv");
        	FileWriter fw = new FileWriter(file);
        	BufferedWriter bw = new BufferedWriter(fw);
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("importLog.csv"), "UTF-8"));
            for (InvalidListing i : invalidList)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(i.getListingId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(i.getMarketplaceName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(i.getInvalidField());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public static void createReportJSON(List<Object[]> stringList) {
    	
    	JSONArray reportArray = new JSONArray();
    	
    	for(Object[] i : stringList) {
    		JSONObject reportObject = new JSONObject();
    		for(int j=0; j<9; j++)
    			if(i[j]!=null)reportObject.put("empty"+j, i[j].toString());
    			else reportObject.put("empty"+j, "null");
    		reportArray.add(reportObject);
    	}
    	try (FileWriter file = new FileWriter("files/report.json")) {
    		 
            file.write(reportArray.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    public static void createReportNiceJSON(List<Object[]> stringList) {
    	try {
    		File file = new File("files/report.json");
    		FileWriter fw = new FileWriter(file);
    		BufferedWriter bw = new BufferedWriter(fw);
    		
    		String[] totalLabels = {"Total listing count","Total eBay listing count","Total eBay listing price","Average eBay listing price","Total Amazon listing count","Total Amazon listing price","Average Amazon listing price","Best lister email address"};
    		String[] labels = {null,"Total eBay listing count per month","Total eBay listing price per month","Average eBay listing price per month","Total Amazon listing count per month","Total Amazon listing price per month","Average Amazon listing price per month","Best lister email address of the month"};
    		Boolean total=true;
    		bw.write("[{");
    		bw.newLine();
    		
    		for(Object[] i : stringList) {
    			
    			if(total) {
    				bw.write("\t\""+i[8].toString()+"\": {");
        			bw.newLine();
    				for(int j=0; j<7; j++) {
            			bw.write("\t\t\""+totalLabels[j].toString()+"\": \""+i[j]+"\",");
            			bw.newLine();
    				}
    				bw.write("\t\t\""+totalLabels[7].toString()+"\": \""+i[7]+"\"");
        			bw.newLine();
        			bw.write("\t}");
        			bw.newLine();
        			total=false;
    			}
    			else {
    				bw.write("}, {");
    				bw.newLine();
    				bw.write("\t\""+i[8].toString()+"\": {");
        			bw.newLine();
    				for(int j=1; j<7; j++) {
    					bw.write("\t\t\""+labels[j].toString()+"\": \""+i[j]+"\",");
    					bw.newLine();
    				}
    				bw.write("\t\t\""+labels[7].toString()+"\": \""+i[7]+"\"");
    				bw.newLine();
    				bw.write("\t}");
    				bw.newLine();
    			}	
    		}
    		bw.write("}]");
    		
    		bw.flush();
            bw.close();
    	
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
