package com.csye6225.demo.dao;

import com.csye6225.demo.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PasswordDao extends CrudRepository<PasswordResetToken, Long> {

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    PasswordResetToken findPasswordResetTokenByToken(String token);
}
