package com.greenwaste.javadatabaseconnector;

import com.greenwaste.javadatabaseconnector.databasepopulator.DatabasePopulator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaDatabaseConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDatabaseConnectorApplication.class, args);
    }
/*
    @Bean
    CommandLineRunner run(DatabasePopulator databasePopulator) {
        return args -> {
            databasePopulator.loadMockData();
        };
    }
*/
}
