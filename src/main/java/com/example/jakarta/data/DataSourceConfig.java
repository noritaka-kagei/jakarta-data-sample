package com.example.jakarta.data;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@DataSourceDefinition(
    name = "java:app/jdbc/jakarta-data-sample",
    className = "org.postgresql.ds.PGSimpleDataSource",
    user = "tester",
    password = "password",
    databaseName = "book",
    serverName = "db",
    portNumber = 5432
)
@Singleton
@Startup
public class DataSourceConfig {
}
