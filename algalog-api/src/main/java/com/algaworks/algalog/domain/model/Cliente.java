package com.algaworks.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

    //@NotNull(groups = Default.class) grupo de validação padrão
    @NotNull(groups = ValidationGroups.ClienteId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Se a classe tivesse nome diferente da coluna no banco precisaria informar com @Column(name ="")
    @NotBlank //Não pode ser nulo nem vazio
    @Size(max = 60) //Máximo 60 caracteres, colocar condizente com a coluna do banco de dados
    private String nome;

    @NotBlank
    @Email //Tem que ser escrito como email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "fone")
    private String telefone;

}
