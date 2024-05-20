package com.fiap.pos.tech.challenge.repository.entity;

import com.fiap.pos.tech.challenge.enums.StatusSinistroEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "SINISTRO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinistroEntity {
    @Id
    private UUID id;

    @Column
    private UUID idApolice;

    @Column
    private String descricao;

    @Column
    private LocalDate data;

    @Column
    private LocalTime hora;

    @Column
    private String nome;

    @Column
    private String sobrenome;

    @Column
    private String cpf;

    @Column
    private String cnh;

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
    private LocalDateTime dataHoraCriacao;

    @Column
    private StatusSinistroEnum status;

    @Column
    private String motivoPrimeiroApelo;

    @Column
    private String motivoSegundoApelo;

    @Column
    private String decisaoSinistro;

    @Column
    private String decisaoApelo;

    @Column
    private String decisaoSegundoApelo;
}
