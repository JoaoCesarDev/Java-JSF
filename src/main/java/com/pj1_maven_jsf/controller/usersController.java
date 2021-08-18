/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.controller;

import com.pj1_maven_jsf.entity.usuario;
import com.pj1_maven_jsf.model.users;
import com.pj1_maven_jsf.repository.usersRepository;
import com.pj1_maven_jsf.utils.utils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jcrfm
 */

@Named(value="usuarioController")
@SessionScoped
public class usersController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private users users;
    
    @Inject
    private usersRepository usersRepository; 
    
    @Inject
    private usuario usuario;
    
    public users getUsers(){
        return users;
    }
    
    public void setUsers(users users){
        this.users = users;
    }
    
    public users getusersSession(){
    
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        return (users)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
    
    }
    
    public String Logout(){
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "/index.xhtml?faces-redirect=true";
    
    }
    
    public String efetuarLogin(){
        try{
        if(StringUtils.isEmpty(users.getUsername())||StringUtils.isBlank(users.getUsername())){
            
            utils.Mensagem("Favor Informar o Login!");
            return null;
        
        }
        else if(StringUtils.isEmpty(users.getUsername())||StringUtils.isBlank(users.getPassword())){
            
            utils.Mensagem("Favor Informar a Senha!");
             return null;
        
        }
        else{
            
            usuario = usersRepository.ValidaUsuario(users);
            if(usuario != null){
            
                users.setPassword(null);
                users.setId(usuario.getId());
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
 
		facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", users);
 
 
		return "/sistema/home?faces-redirect=true";
            }
            else
            {
                utils.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
                return null;
            }
        }
    }catch(Exception e){
        throw e;
       
    }
    
    }
}