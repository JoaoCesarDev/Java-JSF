/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.controller;

import com.pj1_maven_jsf.model.personModel;
import com.pj1_maven_jsf.repository.personRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jdom.CDATA;
import org.primefaces.model.StreamedContent;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author jcrfm
 */
@Named(value="exportarRegistrosXmlController")
@RequestScoped
public class ExportarRegistrosXmlController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject transient
    personRepository pessoaRepository;
    
    private StreamedContent arquivoDownload;
    
    /***
	 * REALIZA O DOWNLOAD DO ARQUIVO XML
	 * @return
     * @throws java.lang.Throwable
	 */
	public StreamedContent getArquivoDownload() throws Throwable {
 
		this.DownlaodArquivoXmlPessoa();
 
		return arquivoDownload;
	}
    
    /***
	 * GERANDO ARQUIVO XML PARA EXPORTAÇÃO.
	 * @return
	 */
	private File GerarXmlPessoas(){
        
           //MASCARA PARA FORMATAR A DATA QUE VAI SER ADICIONADA NO ARQUIVO XML
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
            
            List<personModel> pessoasModel = pessoaRepository.GetPessoas();
            
            //ELEMENTO RAIZ DO NOSSO ARQUIVO XML
            Element elementPessoas = new Element("Pessoas");
 
            Document documentoPessoas = new Document(elementPessoas);
                    
                    for(personModel pessoa : pessoasModel){
			//MONTANDO AS TAGS DO XML COM OS SEUS VALORES
			Element elementPessoa = new Element("Pessoa");			
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()+"\n"));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()+"\n"));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()+"\n"));
 
			//FORMATANDO A DATA
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);
 
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada +"\n"));
 
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()+"\n"));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()+"\n"));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()+"\n"));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsers().getUsername()+"\n"));
 
			elementPessoas.addContent(elementPessoa);
		};
           
                XMLOutputter xmlGerado = new XMLOutputter();
                
                try {
 
 
			/*GERANDO O NOME DO ARQUIVO*/
			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
 
			//CAMINHO ONDE VAI SER GERADO O ARQUIVO XML
			File arquivo = new File("/temp/".concat(nomeArquivo));
 
			FileWriter fileWriter =  new FileWriter(arquivo);
 
                        
			xmlGerado.output(documentoPessoas, fileWriter);
 
			return arquivo;
 
		} catch (Exception ex) {
 
			ex.printStackTrace();
		}		
 
		return null;
            
        }
    
    public void DownlaodArquivoXmlPessoa() throws Throwable{
 
		File arquivoXml = this.GerarXmlPessoas();
               
		InputStream inputStream;
 
		try {
 			inputStream = new FileInputStream(arquivoXml.getPath());
 
			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName());
 
		} catch (FileNotFoundException e) {
                    throw e.fillInStackTrace();
		}
 
	}
    
    
    
    
    
}
