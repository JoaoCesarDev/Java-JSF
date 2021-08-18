package com.pj1_maven_jsf.entity;

import com.pj1_maven_jsf.entity.usuario;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-04T15:21:39")
@StaticMetamodel(pessoaEntity.class)
public class pessoaEntity_ { 

    public static volatile SingularAttribute<pessoaEntity, Integer> codigo;
    public static volatile SingularAttribute<pessoaEntity, String> endereco;
    public static volatile SingularAttribute<pessoaEntity, String> nome;
    public static volatile SingularAttribute<pessoaEntity, usuario> usuario;
    public static volatile SingularAttribute<pessoaEntity, String> sexo;
    public static volatile SingularAttribute<pessoaEntity, LocalDateTime> dataCadastro;
    public static volatile SingularAttribute<pessoaEntity, String> email;
    public static volatile SingularAttribute<pessoaEntity, String> origemCadastro;

}