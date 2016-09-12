/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="staff")
public class Equipe {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="equipe_seq")
	@SequenceGenerator(name="equipe_seq", sequenceName="staff_staff_id_seq")
	@Column(name="staff_id")
	private int id;
	
	@Column(name="first_name")
	private String nome;
	
	@Column(name="last_name")
	private String sobrenome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Endereco endereco;
	
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Loja loja;
	
	@Column(name="active")
	private Boolean ativo;
	
	@Column(name="username")
	private String login;
	
	@Column(name="password")
	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
