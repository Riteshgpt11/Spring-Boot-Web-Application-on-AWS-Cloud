package com.csye6225.demo.rest_assured;

import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RestApiTest {


   /* @Test
    public void testGetHomePage() throws URISyntaxException {
        RestAssured.when().get(new URI("http://localhost:8080/")).then().statusCode(200);
    }*/


    @Test
    public void Demo_test() {
        assertTrue(true);

    }

}
