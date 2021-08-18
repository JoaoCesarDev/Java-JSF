/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.repository;

import com.pj1_maven_jsf.entity.usuario;
import com.pj1_maven_jsf.model.users;
import com.pj1_maven_jsf.utils.GenerateMD5;
import com.pj1_maven_jsf.utils.utils;
import java.io.Serializable;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author jcrfm
 */
public class usersRepository implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public usuario ValidaUsuario(users users){
    
        try{
            
            Query query = utils.JpaEntidyManager().createNamedQuery("usuario.findUser");
            
            query.setParameter("username", users.getUsername());
            
            query.setParameter("password", users.getPassword());
            
            return (usuario)query.getSingleResult();
        }
        catch(Exception e){
           return null;
                       
        }
        
    
    }
    
    
    
    
    
    
    
}
