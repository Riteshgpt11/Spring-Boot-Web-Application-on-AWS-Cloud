package com.csye6225.demo.Config;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GenerateRDSAuthToken {

    static String generateAuthToken(String region, String hostName, String port, String username) {

        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
                .credentials(new DefaultAWSCredentialsProviderChain())
                .region(region)
                .build();

        String authToken = generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(hostName)
                        .port(Integer.parseInt(port))
                        .userName(username)
                        .build());

        return authToken;
    }

    private static Connection getDBConnectionUsingIam() {


        String jdbcUrl = "jdbc:mysql://" + "rds_instance_endpoint";

        Properties mysqlConnectionProperties = new Properties();
        mysqlConnectionProperties.setProperty("verifyServerCertificate", "true");
        mysqlConnectionProperties.setProperty("useSSL", "true");

        System.setProperty("javax.net.ssl.trustStore", "path_to_truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "trustore_password");

        mysqlConnectionProperties.setProperty("user", "iam-database-user");

        // Call a method to generate an authentication token (see above example)
        String authToken = generateAuthToken();

        // Set authentication token as password
        mysqlConnectionProperties.setProperty("password", authToken);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, mysqlConnectionProperties);
        } catch (SQLException ex) {
            System.out.println("SQL Exception auth token gen: " + ex);
        } catch (Exception ex) {
            System.out.println("Auth token gen: " + ex);
        }

        return connection;

    }
}
