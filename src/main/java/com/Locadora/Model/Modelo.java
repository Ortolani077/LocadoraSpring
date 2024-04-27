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
public class Modelo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotNull
	private String nome;

	@ManyToOne
    @JoinColumn(name = "fabricante_id") // Aqui estamos associando a coluna 'fabricante_id' à entidade relacionada 'Fabricante'
    private Fabricante fabricante;


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

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricanteId) {
		this.fabricante = fabricanteId;
	}

	public Modelo(Long id, String nome, Fabricante fabricanteId) {
		super();
		this.id = id;
		this.nome = nome;
		this.fabricante = fabricanteId;
	}

	public Modelo() {
		super();
	}

}
