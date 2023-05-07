package com.goit.crud;

import com.goit.conf.Constants;
import com.goit.crud.exception.DatasourceException;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.Properties;

import static com.goit.conf.Constants.CONNECTION_AUTOCOMMIT;
import static com.goit.conf.Constants.TRANSACTION_ISOLATION;

public class Datasource {
    private static final String DEFAULT_FILE_NAME = "application.properties";
    private Properties properties = new Properties();
    private Connection connection;

    public Datasource(String propertiesFileName) {
        try {
            properties.load(Datasource.class.getClassLoader().getResourceAsStream(propertiesFileName));
        } catch (Exception e) {
            throw new DatasourceException("Datasource failed", e);
        }
    }

    public Datasource() {
        try {
            properties.load(Datasource.class.getClassLoader().getResourceAsStream(DEFAULT_FILE_NAME));
        } catch (Exception e) {
            throw new DatasourceException("Datasource failed", e);
        }
    }

    public void openConnection() {
        try {
            NonRegisteringDriver driver = new NonRegisteringDriver();
            DriverManager.registerDriver(driver);
            String url = properties.getProperty(Constants.CONNECTION_URL);
            String username = properties.getProperty(Constants.CONNECTION_USER);
            String password = properties.getProperty(Constants.CONNECTION_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatasourceException("Open connection failed", e);
        }
    }

    public PreparedStatement prepareStatement(@Language("SQL") String query) {
        try {
            openConnection();
//            connection.createStatement().execute(getIsolationLevelQuery(properties.getProperty(TRANSACTION_ISOLATION)));
//            boolean autocommit = Boolean.parseBoolean(properties.getProperty(CONNECTION_AUTOCOMMIT));
//            connection.setAutoCommit(autocommit);
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            return preparedStatement;
        } catch (Exception e) {
            throw new DatasourceException("Can't create prepared statement", e);
        }
    }


    private String getIsolationLevelQuery(String name) {
        switch (name) {
            case "READ_UNCOMMITTED":
                return "SET GLOBAL TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
            case "REPEATABLE_READ":
                return "SET GLOBAL TRANSACTION ISOLATION LEVEL REPEATABLE READ";
            case "READ_COMMITTED":
                return "SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED";
            case "SERIALIZABLE":
                return "SET GLOBAL TRANSACTION ISOLATION LEVEL SERIALIZABLE";
            default:
                throw new DatasourceException("Unsupported isolation level:" + name);
        }
    }

    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw new DatasourceException("Connection close failed", e);
        }
    }


}
