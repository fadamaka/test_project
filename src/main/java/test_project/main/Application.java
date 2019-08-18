package test_project.main;


import test_project.bll.Routine;
import test_project.dal.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	*/

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> { 
			if(DAO.isConnected()) {
				Routine.synchData();
				log.info("Data synched succesfully");
				Routine.createAndUploadReport();
				log.info("Report created and uploaded to ftp.");
			
				//Routine.commander();
			
				DAO.close();
				log.info("Exiting...");}
			else log.info("Database connection not set up properly.");
		};
	}
}
