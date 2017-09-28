/**
 *rohan magare, 001231457, magare.r@husky.neu.edu
 *ritesh gupta, 001280361, gupta.rite@husky.neu.edu
 *pratiksha shetty, 00121643697, shetty.pr@husky.neu.edu
 *yogita jain, 001643815, jain.yo@husky.neu.edu
 **/
package com.csye6225.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "emailId", unique = true, nullable = false)
    @NotNull
    private String emailId;
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;
    @Column(name = "firstName", nullable = false)
    @NotNull
    private String firstName;
    @Column(name = "lastName", nullable = false)
    @NotNull
    private String lastName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
