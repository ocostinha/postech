package com.fiap.pos.tech.challenge.repository.entity;

import com.fiap.pos.tech.challenge.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "COTACAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoEntity {
    @Id
    private UUID id;

    @Column
    private String nome;

    @Column
    private String sobrenome;

    @Column
    private String cpf;

    @Column
    private String nomeMae;

    @Column
    private String nomePai;

    @Column
    private String email;

    @Column
    private Integer dddFixo;

    @Column
    private Long fixo;

    @Column
    private Integer dddCelular;

    @Column
    private Long celular;

    @Column
    private Integer dddRecado;

    @Column
    private Long recado;

    @Column
    private String logradouro;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private Long cep;

    @Column
    private String placa;

    @Column
    private String renavan;

    @Column
    private String chassi;

    @Column
    private String modelo;

    @Column
    private Integer anoFabricacao;

    @Column
    private Integer anoModelo;

    @Column
    private LocalDateTime dataHoraCriacao;

    @Column
    private LocalDateTime dataHoraExpiracao;

    @Column
    private StatusEnum status;
}
