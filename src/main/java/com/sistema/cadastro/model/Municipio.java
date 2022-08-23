package com.sistema.cadastro.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_municipios")
public class Municipio{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo CÃ³digo Ã© ObrigatÃ³rio!")
	private String codigo;
	
	@NotNull(message = "O atributo Nome Ã© ObrigatÃ³rio!")
	private String nome;
	
	@NotNull(message = "O atributo UF Ã© ObrigatÃ³rio!")
	private String uf;
	
	@OneToMany(mappedBy = "municipio", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("municipio")
	private List<Cliente> cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}
	
	
}