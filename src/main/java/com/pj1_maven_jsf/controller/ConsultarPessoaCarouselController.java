/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.controller;

import com.pj1_maven_jsf.model.personModel;
import com.pj1_maven_jsf.repository.personRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jcrfm
 */
@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject transient
    private personRepository pessoaRepository;
    
    @Produces
    private List<personModel> pessoas;
    
    public List<personModel> getPessoas(){
        return pessoas;
    }
    
    @PostConstruct
    private void init(){
        this.pessoas = pessoaRepository.GetPessoas();
    }
    
    
    
}
