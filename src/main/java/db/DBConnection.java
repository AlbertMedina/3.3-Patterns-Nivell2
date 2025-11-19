package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection instance;

    private String url;
    private String user;
    private String password;

    private DBConnection() {
        try {
            Properties properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(input);

            String dbName = properties.getProperty("name");
            String dbPort = properties.getProperty("port");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");

            this.url = "jdbc:mysql://localhost:" + dbPort + "/" + dbName + "?useSSL=false&serverTimezone=UTC";

        } catch (Exception e) {
            throw new RuntimeException("Error to connect with data base.", e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

