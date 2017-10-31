package com.csye6225.demo.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlDatasource {

    public static Connection getRemotedConnection(){
        String hostname1 = System.getenv("spring.datasource.url");
        System.out.println("hostname: " + hostname1);
        if (hostname1 != null) {
            try {
                System.out.println("inside datasource");
                Class.forName("org.mysql.Driver");
                String dbName = "csye6225";
                String userName = System.getenv("spring.datasource.username");
                String password = System.getenv("spring.datasource.password");
                String hostname = System.getenv("spring.datasource.url");
                String port = "PORT";
                //String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                String jdbcUrl = hostname + "?user=" + userName + "&password=" + password;
                //logger.trace("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                //logger.info("Remote connection successful.");
                return con;
            }
            catch (ClassNotFoundException e) { System.out.println(e.toString());}
            catch (SQLException e) { System.out.println(e.toString());}
        }
        return null;
    }

}
