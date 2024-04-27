package com.Locadora.Model;
import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Carro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fabricante_id") // Associa a coluna 'fabricante_id' à entidade relacionada 'Fabricante'
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "modelo_id") // Associa a coluna 'modelo_id' à entidade relacionada 'Modelo'
    private Modelo modelo;

    @Column
    @NotNull
    private String placa;

    @Column
    @NotNull
    private String cor;

    @Column
    @NotNull
    private Boolean disponivel;

    @Column
    @NotNull
    private Integer ano;

    @Column
    @NotNull
    private Double valorLocacao;

    // Getters e setters para os campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFabricanteId() {
        return fabricante != null ? fabricante.getId() : null;
    }
    
 // Método setter para o ID do fabricante
    public void setFabricanteId(Long fabricanteId) {
        if (fabricanteId != null) {
            this.fabricante = new Fabricante();
            this.fabricante.setId(fabricanteId);
        } else {
            this.fabricante = null;
        }
    }

    // Método setter para o ID do modelo
    public void setModeloId(Long modeloId) {
        if (modeloId != null) {
            this.modelo = new Modelo();
            this.modelo.setId(modeloId);
        } else {
            this.modelo = null;
        }
    }


   


	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Long getModeloId() {
        return modelo != null ? modelo.getId() : null;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(Double valorLocacao) {
        this.valorLocacao = valorLocacao;
        
    }

    // Construtores

    public Carro(Long id, Fabricante fabricante, Modelo modelo, String placa, String cor, Boolean disponivel, Integer ano, Double valorLocacao) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.disponivel = disponivel;
        this.ano = ano;
        this.valorLocacao = valorLocacao;
    }

    public Carro() {
    }
}
