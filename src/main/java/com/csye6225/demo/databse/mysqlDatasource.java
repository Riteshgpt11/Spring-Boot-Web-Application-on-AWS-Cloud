package com.csye6225.demo.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlDatasource {

    public static Connection getRemotedConnection(){
        String hostname1 = System.getenv("$URL");
        System.out.println(hostname1);
        if (System.getenv("URL") != null) {
            try {
                Class.forName("org.mysql.Driver");
                String dbName = "DBNAME";
                String userName = System.getenv("USERNAME");
                String password = System.getenv("PASSWORD");
                String hostname = System.getenv("URL");
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
