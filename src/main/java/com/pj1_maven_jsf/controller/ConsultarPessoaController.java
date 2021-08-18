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

@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject transient
    private personModel pessoaModel;        
    
    @Produces
    private List<personModel> pessoas;
    
    @Inject transient
    private personRepository pessoaRepository;

    public List<personModel> getPessoas(){
        return pessoas;
    }
    
    public void setPessoas(List<personModel>pessoas){
        this.pessoas = pessoas;
    }
    
    public personModel getPersonModel(){
        return pessoaModel;
    }
    
    public void setPessoaModel(personModel pessoaModel){
        this.pessoaModel = pessoaModel;
    }

    /***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */

    @PostConstruct
    public void init(){
        //RETORNAR AS PESSOAS CADASTRADAS
        this.pessoas = pessoaRepository.GetPessoas();
    }

/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param personModel
	 */
	public void Editar(personModel personModel){
 
               if(personModel.getSexo().equals("Masculino"))
		personModel.setSexo("M");
                else
                personModel.setSexo("F");
		this.pessoaModel = personModel;
                
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.pessoaRepository.AlterarRegistro(this.pessoaModel);	
  
		/*RECARREGA OS REGISTROS*/
		this.init();
                
	}
        
        public void ExcluirPessoa(){
        
            this.pessoaRepository.ExcluirRegistro(this.pessoaModel);
            
            this.init();
           // this.pessoas.remove(personModel);
        
        }
        public void Excluir(personModel personModel){
            this.pessoaModel = personModel;
        }
}
