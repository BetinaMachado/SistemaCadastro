package com.sistema.cadastro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
	@Table(name = "tb_clientes")
	public class Cliente {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotNull(message = "O atributo Código é Obrigatório!")
		private String codigo;
		
		@NotNull(message = "O atributo Nome é Obrigatório!")
		private String nome;

		@NotNull(message = "O atributo Endereço é Obrigatório!")
		private String endereco;

		@NotNull(message = "O atributo número é Obrigatório!")
		private String numero;

		@NotBlank(message = "O atributo bairro é Obrigatório!")
		private String bairro;
		
		@NotNull (message = "O atributo CEP é Obrigatório!")
		private int cep;

		@ManyToOne
		@JsonIgnoreProperties("cliente")
		private Municipio municipio;

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

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public int getCep() {
			return cep;
		}

		public void setCep(int cep) {
			this.cep = cep;
		}

		public Municipio getMunicipio() {
			return municipio;
		}

		public void setMunicipio(Municipio municipio) {
			this.municipio = municipio;
		}
		
		

}
