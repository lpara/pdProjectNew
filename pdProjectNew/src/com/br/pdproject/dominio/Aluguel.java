/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="rental")
public class Aluguel {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="aluguel_seq")
	@SequenceGenerator(name="aluguel_seq", sequenceName="rental_rental_id_seq")
	@Column(name="rental_id")
	private int id;
	
	@Column(name="rental_date")
	private Timestamp dataAluguel;
	
	@OneToOne
	@JoinColumn(name="inventory_id")
	private Inventario inventario;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Cliente cliente;
	
	@Column(name="return_date")
	private Timestamp dataDevolucao;
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Equipe equipe;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(Timestamp dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Timestamp getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Timestamp dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}

