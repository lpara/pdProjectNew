/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="address")
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="endereco_seq")
	@SequenceGenerator(name="endereco_seq", sequenceName="address_address_id_seq")
	@Column(name="address_id")
	private int id;
	
	@Column(name="address")
	private String enderecoPrincipal;
	
	@Column(name="address2")
	private String enderecoSecundario;
	
	@Column(name="district")
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private Cidade cidade;
	
	@Column(name="postal_code")
	private String cep;
	
	@Column(name="phone")
	private String telefone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setEnderecoPrincipal(String enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public String getEnderecoSecundario() {
		return enderecoSecundario;
	}

	public void setEnderecoSecundario(String enderecoSecundario) {
		this.enderecoSecundario = enderecoSecundario;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}

