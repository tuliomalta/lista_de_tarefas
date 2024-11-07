package com.tuliomalta.lista_tarefas.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal custo;

    @Column(name = "data_limite", nullable = false)
    private LocalDate dataLimite;

    @Column(name = "ordem_apresentacao", nullable = false, unique = true)
    private int ordemApresentacao;

    public Tarefa(Long id, String nome, BigDecimal custo, LocalDate dataLimite, int ordemApresentacao) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
        this.dataLimite = dataLimite;
        this.ordemApresentacao = ordemApresentacao;
    }

    public Tarefa() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public int getOrdemApresentacao() {
        return ordemApresentacao;
    }

    public void setOrdemApresentacao(int ordemApresentacao) {
        this.ordemApresentacao = ordemApresentacao;
    }
}
