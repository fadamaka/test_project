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
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> { 
			log.info("Testing database connection.");
			if(DAO.isConnected()) {
				log.info("Database is connected.");
				log.info("Data synchronization started.");
				Routine.synchData();
				log.info("Data synched successfully");
				
				Routine.createAndUploadReport();
				
				if(args.length!=0 && args[0].equals("1"))
					Routine.commander();
			
				DAO.close();
				log.info("Exiting application...");
			}
			else log.info("Database connection not set up properly.");
		};
	}
}
