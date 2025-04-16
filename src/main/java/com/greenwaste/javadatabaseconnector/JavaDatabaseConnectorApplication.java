package com.greenwaste.javadatabaseconnector;

import com.greenwaste.javadatabaseconnector.databasepopulator.DatabasePopulator;
import com.greenwaste.javadatabaseconnector.databaseseed.DatabaseSeed;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;

@SpringBootApplication
public class JavaDatabaseConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDatabaseConnectorApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DatabasePopulator databasePopulator) {
        return args -> {
            databasePopulator.loadMockData();
        };
    }

}
