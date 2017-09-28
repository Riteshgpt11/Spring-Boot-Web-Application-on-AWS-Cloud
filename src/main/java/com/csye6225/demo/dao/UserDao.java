package com.csye6225.demo.dao;

import com.csye6225.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findUserByEmailId(String emailId);

    User findUserByEmailIdAndPassword(String emailId, String password);


}
