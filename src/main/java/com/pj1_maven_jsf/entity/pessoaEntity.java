/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
 
@Entity
@Table(name="tb_pessoa")

@NamedQueries({@NamedQuery(name = "pessoaEntity.findAll",query="Select p from pessoaEntity p"),
               @NamedQuery(name = "pessoaEntity.GroupByOrigemCadastro",query="Select p.origemCadastro, count(p) as total FROM pessoaEntity p GROUP BY p.origemCadastro")})

public class pessoaEntity implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Integer codigo;
 
	@Column(name = "nm_pessoa")
	private String  nome;
 
	@Column(name = "fl_sexo")
	private String  sexo;
 
	@Column(name = "dt_cadastro")
	private LocalDateTime	dataCadastro;
 
	@Column(name = "ds_email")
	private String  email;
 
	@Column(name = "ds_endereco")
	private String  endereco;
 
	@Column(name = "fl_origemCadastro")
	private String  origemCadastro;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private usuario usuario;
 
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getOrigemCadastro() {
		return origemCadastro;
	}
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}
	public usuario getUsuarioEntity() {
		return usuario;
	}
	public void setUsuarioEntity(usuario usuario) {
		this.usuario = usuario;
	}  
}
