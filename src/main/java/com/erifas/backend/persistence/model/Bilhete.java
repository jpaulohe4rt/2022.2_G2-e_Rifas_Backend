package com.erifas.backend.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bilhete")
public class Bilhete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @ManyToOne(fetch = FetchType.LAZY)
    private Comprador comprador;
    @ManyToOne
    @JoinColumn(name = "rifa_id", nullable = false)
    @JsonIgnore
    private Rifa rifa;
    private Boolean sorteado = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Rifa getRifa() {
        return rifa;
    }

    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }

    public Boolean getSorteado() {
        return sorteado;
    }

    public void setSorteado(Boolean sorteado) {
        this.sorteado = sorteado;
    }
}
