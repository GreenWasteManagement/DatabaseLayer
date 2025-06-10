package com.greenwaste.javadatabaseconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
