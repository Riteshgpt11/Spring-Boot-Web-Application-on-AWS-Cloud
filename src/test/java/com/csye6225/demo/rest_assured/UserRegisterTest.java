package com.csye6225.demo.rest_assured;

import com.csye6225.demo.dao.UserDao;
import com.csye6225.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRegisterTest {

   /* @Autowired
    private UserDao userDao;

    @Test
    public void testUserregister(){

        //Register a user
        User u = new User();
        u.setFirstName("Yogi");
        u.setEmailId("Yogi_test_124@gmail.com");
        u.setLastName("Jain");
        u.setPassword("yogitaj508");

        //Test adding the user
        User newUser = userDao.save(u);

        //Verify the addition
        assertNotNull(u);
        assertNotNull(u.getUserId());
        assertEquals("Yogi_test_124@gmail.com", u.getEmailId());


}

    @Test
    public void testUserExists(){

        User u = userDao.findUserByEmailId("yogita_123@gmail.com");
        assertNull(u);


    }*/
}
