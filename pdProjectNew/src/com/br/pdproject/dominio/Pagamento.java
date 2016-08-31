/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import java.math.BigDecimal;
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
@Table(name="payment")
public class Pagamento {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pagamento_seq")
	@SequenceGenerator(name="pagamento_seq", sequenceName="payment_payment_id_seq")
	@Column(name="payment_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="staff_id")
	private Equipe equipe;
	
	@Column(name="amount")
	private BigDecimal quantia;
	
	@Column(name="payment_date")
	private Timestamp dataPagamento;
	
	@ManyToOne	
	@JoinColumn(name="rental_id")
	private Aluguel aluguel;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp diaPagamento) {
		this.dataPagamento = diaPagamento;
	}
	
	public BigDecimal getQuantia() {
		return quantia;
	}

	public void setQuantia(BigDecimal quantia) {
		this.quantia = quantia;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
}

