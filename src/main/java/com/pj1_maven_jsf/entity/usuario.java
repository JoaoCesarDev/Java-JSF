/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author jcrfm
 */
@Table(name="users")
@Entity
@NamedQuery(name="usuario.findUser", query= "SELECT u FROM usuario u WHERE u.username = :username AND u.password = :password")

public class usuario implements Serializable{

    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    private Boolean enabled;
    private String validation;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean isActive() {
        return enabled;
    }
    /**
     * @return the active
     */
    public Boolean getActive() {
        return enabled;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.enabled = active;
    }

    /**
     * @return the validation
     */
    public String getValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(String validation) {
        this.validation = validation;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
   
}
