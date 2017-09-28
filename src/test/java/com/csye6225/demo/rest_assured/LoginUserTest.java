package com.csye6225.demo.rest_assured;

import com.csye6225.demo.dao.UserDao;
import com.csye6225.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@ActiveProfiles("dev")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginUserTest {

   /* @Autowired
    private UserDao userDao;

    // Login user
    @Test
    public void testLoginUser() throws Exception {
        //User exists
        User u = userDao.findUserByEmailId("yogita_j508@gmail.com");
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();

        if(pass.matches("Hello12", u.getPassword())){
            assertNotNull(u);
        }
        else{
           System.out.println("Password does not match");
        }

    }

    // User does not exists
    @Test
    public void testLoginUser_fail() throws Exception {
        User u = userDao.findUserByEmailIdAndPassword("Yogi_test_777@gmail.com","yogitaj508");
        assertNull(u);


    }*/
}
