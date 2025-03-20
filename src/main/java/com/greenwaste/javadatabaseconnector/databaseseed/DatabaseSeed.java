package com.greenwaste.javadatabaseconnector.databaseseed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DatabaseSeed {

    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_DATABASE_CREATOR = "Scripts/DatabaseSQL/DatabaseCreation.sql";
    private static final String SQL_DATABASE_RESET = "Scripts/DatabaseSQL/DatabaseReset.sql";


    public DatabaseSeed(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void resetDatabase() throws IOException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(SQL_DATABASE_RESET);

        if (inputStream == null) {
            return;
        }

        String sql = new String(inputStream.readAllBytes());

        jdbcTemplate.execute(sql);

    }


    public void createDatabase() throws IOException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(SQL_DATABASE_CREATOR);

        if (inputStream == null) {
            return;
        }

        String sql = new String(inputStream.readAllBytes());

        jdbcTemplate.execute(sql);

    }

}
