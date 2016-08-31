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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="store")
public class Loja {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="loja_seq")
	@SequenceGenerator(name="loja_seq", sequenceName="store_store_id_seq")
	@Column(name="store_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="manager_staff_id", referencedColumnName="staff_id")
	private Equipe gerenteEquipe;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Endereco endereco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipe getGerenteEquipe() {
		return gerenteEquipe;
	}

	public void setGerenteEquipe(Equipe gerenteEquipe) {
		this.gerenteEquipe = gerenteEquipe;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}

