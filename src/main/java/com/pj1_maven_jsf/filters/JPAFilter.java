/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author jcrfm
 */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {
    
    private EntityManagerFactory entityManagerFactory;
 
    private String persistence_unit_name = "pj1_maven_jsf_PU";
    
   
    public JPAFilter() {
    }    
    public void destroy() {   
        
        this.entityManagerFactory.close();
    }
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
       
        /*CRIANDO UM ENTITYMANAGER*/
	EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
        /*ADICIONANDO ELE NA REQUISIÇÃO*/
        request.setAttribute("entityManager", entityManager);
        /*INICIANDO UMA TRANSAÇÃO*/
        
        EntityTransaction tx = entityManager.getTransaction();
        
	tx.begin();
 
	/*INICIANDO FACES SERVLET*/
	 chain.doFilter(request, response);
        
                       
        try {
           
            /*SE NÃO TIVER ERRO NA OPERAÇÃO ELE EXECUTA O COMMIT*/
            tx.commit();
        } catch (RuntimeException re) {
          
	//  tx.rollback();/*SE TIVER ERRO NA OPERAÇÃO É EXECUTADO O rollback*/
          throw re;
        }
        finally{
 
	  /*DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager*/
	  entityManager.close();
	}
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    public void init(FilterConfig filterConfig) {        
        /*CRIA O entityManagerFactory COM OS PARÂMETROS DEFINIDOS NO persistence.xml*/
	this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
    }

}
