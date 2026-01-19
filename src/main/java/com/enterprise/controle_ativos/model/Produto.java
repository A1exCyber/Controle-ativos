package com.enterprise.controle_ativos.model;

import java.time.LocalDate;
import jakarta.persistence.*; // Importa todas as ferramentas de persistência
import org.springframework.format.annotation.DateTimeFormat;

@Entity // Diz que o produto agora é uma tabela
@Table(name = "produtos") // Esse é o nome da tabela que será criada...
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    private String nome;
    private String numeroSerie;
    private String status;
    private String alocadoPara;
    private String matriculaColaborador;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataRetorno;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataRetirada;

    // construtor...
    public Produto() {
    }

    // Getters and Setters....

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlocadoPara() {
        return alocadoPara;
    }

    public void setAlocadoPara(String alocadoPara) {
        this.alocadoPara = alocadoPara;
    }

    public String getMatriculaColaborador() {
        return matriculaColaborador;
    }

    public void setMatriculaColaborador(String matriculaColaborador) {
        this.matriculaColaborador = matriculaColaborador;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
