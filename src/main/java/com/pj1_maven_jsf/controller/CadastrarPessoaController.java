/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.controller;

import com.pj1_maven_jsf.controller.usersController;
import com.pj1_maven_jsf.model.personModel;
import com.pj1_maven_jsf.repository.personRepository;
import com.pj1_maven_jsf.utils.utils;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jcrfm
 */
@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {
 
	@Inject
	personModel personModel;
 
	@Inject
	usersController usuarioController;
 
	@Inject
	personRepository pessoaRepository;
 
        private UploadedFile file;
 
	public UploadedFile getFile() {
		return file;
	}
 
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public personModel getPessoaModel() {
		return personModel;
	}
 
	public void setPessoaModel(personModel personModel) {
		this.personModel = personModel;
	}
       
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarNovaPessoa(){
 
		personModel.setUsuarioModel(this.usuarioController.getusersSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
		personModel.setOrigemCadastro("I");
 
		pessoaRepository.SalvarNovoRegistro(this.personModel);
 
		this.personModel = null;
 
		utils.MensagemInfo("Registro cadastrado com sucesso");
 
        }
        /**
	 * REALIZANDO UPLOAD DE ARQUIVO
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
	 */
	 public void UploadRegistros() throws ParserConfigurationException, SAXException, IOException{
 
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 
		 try {

			 if(this.getFile() == null){
				 utils.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }
 
			 DocumentBuilder builder = factory.newDocumentBuilder();
 
	                 Document doc = builder.parse(this.file.getInputstream());
 
	                 Element element = doc.getDocumentElement();
 
	                 NodeList nodes = element.getChildNodes();
 
	                for (int i = 0; i < nodes.getLength(); i++) {
 
	        	     Node node  = nodes.item(i);
 
	        	    if(node.getNodeType() == Node.ELEMENT_NODE){
 
	        		 Element elementPessoa =(Element) node;
 
	        		 //PEGANDO OS VALORES DO ARQUIVO XML
	        		 String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();
 
                                 personModel newpessoamodel = new personModel();
                                 
	        		 newpessoamodel.setUsuarioModel(this.usuarioController.getusersSession());
	        		 newpessoamodel.setEmail(email);
	        		 newpessoamodel.setEndereco(endereco);
	        		 newpessoamodel.setNome(nome);
	        		 newpessoamodel.setOrigemCadastro("X");
	        		 newpessoamodel.setSexo(sexo);
 
	        		 //SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
	        		 pessoaRepository.SalvarNovoRegistro(newpessoamodel);
	        	   }
	              }
 
		     utils.MensagemInfo("Registros cadastrados com sucesso!");
 
		} catch (ParserConfigurationException | SAXException | IOException e) {
                    throw e;
		}
 	 }
         
         
}
