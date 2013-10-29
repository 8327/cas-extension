package com.fingolfintek.model;

import javax.annotation.Generated;
import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "USER_INFO")
public class UserAuthenticationInfo {

    private Long id;
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy = AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 64, unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 256, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
