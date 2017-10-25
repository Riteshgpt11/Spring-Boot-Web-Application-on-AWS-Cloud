package com.csye6225.demo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;

@Configuration
@ImportResource("classpath:/aws-config.xml")
@EnableRdsInstance(databaseName = "${database-name:}",
        dbInstanceIdentifier = "${db-instance-identifier:}",
        password = "${rdsPassword:}")
public class AwsResourceConfig {

}
