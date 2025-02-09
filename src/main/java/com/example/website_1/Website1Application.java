package com.example.website_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class Website1Application {

	public static void main(String[] args) {
		SpringApplication.run(Website1Application.class, args);
	}

}
