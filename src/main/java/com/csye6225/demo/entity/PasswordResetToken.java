package com.csye6225.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken implements Serializable {
    private static final int EXPIRATION = 2;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tokenId", unique = true, nullable = false)
    private Long tokenId;
    @Column(name = "token", nullable = false)
    @NotNull
    private String token;     //targetEntity = User.class,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private User user;
    @Column(name = "expiryDate", nullable = false)
    @NotNull
    private Date expiryDate;

    public PasswordResetToken() {

    }
    public PasswordResetToken(String token, User user) {
        this.user = user;
        this.token = token;
    }

    public Long getId() {
        return tokenId;
    }

    public void setId(Long id) {
        this.tokenId = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
