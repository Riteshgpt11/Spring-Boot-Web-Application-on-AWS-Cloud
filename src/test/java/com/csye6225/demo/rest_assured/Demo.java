package com.csye6225.demo.rest_assured;

import com.csye6225.demo.dao.UserDao;
import com.csye6225.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.springframework.context.annotation.Configuration;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration()
public class Demo {


    @Test
    public void Demo_test() {
        assertTrue(true);

    }

}
