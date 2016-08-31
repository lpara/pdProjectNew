/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.List;

/**
 * @author Lucas 
 * @author Luan 
 *
 * @param <T> - Entidade Persistente
 * @param <ID> - Chave Primaria
 */
public interface DAO <T, ID> {

	T salvar(T entidade);
	
	void deletar(T entidade);
	
	long contar();

	List<T> buscar();
	
	List<T> buscar(int offset, int max);
	
}