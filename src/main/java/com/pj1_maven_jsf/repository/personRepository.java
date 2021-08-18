/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.repository;

import com.pj1_maven_jsf.entity.pessoaEntity;
import com.pj1_maven_jsf.entity.usuario;
import com.pj1_maven_jsf.model.personModel;
import com.pj1_maven_jsf.model.users;
import com.pj1_maven_jsf.utils.utils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jcrfm
 */
public class personRepository {
    
        @Inject
	pessoaEntity pessoaEntity;
 
	EntityManager entityManager;
 
        private pessoaEntity GetPessoa(int codigo){
        
            entityManager = utils.JpaEntidyManager();
           
            return entityManager.find(pessoaEntity.class,codigo);
            
        }
        
        /***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param personModel
	 */
        public void AlterarRegistro(personModel personModel){
        
        entityManager = utils.JpaEntidyManager();
        
        pessoaEntity pessoaEntity = this.GetPessoa(personModel.getCodigo());
        pessoaEntity.setEmail(personModel.getEmail());
        pessoaEntity.setEndereco(personModel.getEndereco());
        pessoaEntity.setNome(personModel.getNome());
        pessoaEntity.setSexo(personModel.getSexo());
        
        entityManager.merge(pessoaEntity);
        
        }
        
        /***
	* MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
        * @param personModel
	 * 
         * 
	 */
	public void SalvarNovoRegistro(personModel personModel){
 
		entityManager =  utils.JpaEntidyManager();
 
		pessoaEntity = new pessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(personModel.getEmail());
		pessoaEntity.setEndereco(personModel.getEndereco());
		pessoaEntity.setNome(personModel.getNome());
		pessoaEntity.setOrigemCadastro(personModel.getOrigemCadastro());
		pessoaEntity.setSexo(personModel.getSexo());
 
		usuario usuario = entityManager.find(usuario.class, personModel.getUsers().getId()); 
 
		pessoaEntity.setUsuarioEntity(usuario);
 
		entityManager.persist(pessoaEntity);
        }
        /***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * @return
	 */
        public List<personModel> GetPessoas(){
            
            List<personModel> pessoasModel = new ArrayList<personModel>();
            
            entityManager =  utils.JpaEntidyManager();
            
            Query query = entityManager.createNamedQuery("pessoaEntity.findAll");
            
            @SuppressWarnings("unchecked")
            Collection<pessoaEntity> pessoasEntity = (Collection<pessoaEntity>)query.getResultList();
            
            personModel personModel = null;
            
            for(pessoaEntity pessoaEntity : pessoasEntity){
            
                personModel = new personModel();
                personModel.setCodigo(pessoaEntity.getCodigo());
                personModel.setDataCadastro(pessoaEntity.getDataCadastro());
                personModel.setEmail(pessoaEntity.getEmail());
                personModel.setEndereco(pessoaEntity.getEndereco());
                personModel.setNome(pessoaEntity.getNome());
                
                if(pessoaEntity.getOrigemCadastro().equals("X"))
                    personModel.setOrigemCadastro("XML");
                else
                    personModel.setOrigemCadastro("INPUT");
                if(pessoaEntity.getSexo().equals("M"))
                    personModel.setSexo("Masculino");
                else
                    personModel.setSexo("Feminino");
                
                usuario usuarioEnyity = pessoaEntity.getUsuarioEntity();
                
                users usuarioModel = new users();
                
                usuarioModel.setUsername(usuarioEnyity.getUsername());
                
                personModel.setUsuarioModel(usuarioModel);
                
                pessoasModel.add(personModel);
            }
            
            return pessoasModel;
        }
        
        public void ExcluirRegistro(personModel personModel){
        
            entityManager = utils.JpaEntidyManager();
            
            pessoaEntity pessoaEntity = this.GetPessoa(personModel.getCodigo());
            
            entityManager.remove(pessoaEntity);
        
        }
        
        /***
	 * RETORNA OS TIPOS DE PESSOA AGRUPADOS
	 * @return
	 */
        
	public Hashtable<String, Integer> GetOrigemPessoa(){
 
		Hashtable<String, Integer> hashtableRegistros = new Hashtable<String,Integer>(); 
 
		entityManager =  utils.JpaEntidyManager();
 
		Query query = entityManager.createNamedQuery("pessoaEntity.GroupByOrigemCadastro");
 
		@SuppressWarnings("unchecked")
		Collection<Object[]> collectionRegistros  = (Collection<Object[]>)query.getResultList();
 
		for (Object[] objects : collectionRegistros) {
 
 
			String tipoPessoa = (String)objects[0];
			int totalDeRegistros = ((Number)objects[1]).intValue();
 
			if(tipoPessoa.equals("X"))
				tipoPessoa = "XML";
			else
				tipoPessoa = "INPUT";
 
			hashtableRegistros.put(tipoPessoa, totalDeRegistros);
 
		}
 
		return hashtableRegistros;
 
	}
}
