/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.controller;

import com.pj1_maven_jsf.repository.personRepository;
import java.util.Hashtable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author jcrfm
 */
@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {
    
    @Inject
    private         
    personRepository pessoaRepository;
    
    private PieChartModel pieChartModel;
    
    public PieChartModel getPieChartModel() {
	return pieChartModel;
    }
    
        @PostConstruct
	public  void init(){
 
		this.pieChartModel = new PieChartModel();
 
		this.MontaGrafico();
	}
    
        /***
	 * MONTA O GRÁFICO NA PÁGINA
	 */
	private void MontaGrafico(){
 
		//CONSULTA OS DADOS PARA MONTAR O GRÁFICO
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();
 
 
		//INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
		for(Map.Entry<String, Integer> entry : hashtableRegistros.entrySet()){
                    String chave = entry.getKey();
                    Integer valor = entry.getValue();
                    pieChartModel.set(chave,valor);
                 }
 
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
 
 
	}
}
