package com.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;


@EntityListeners(AuditingEntityListener.class)
@Table(name = "test_entity_table")
@Entity
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "userId", nullable = false)
    private Integer userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

