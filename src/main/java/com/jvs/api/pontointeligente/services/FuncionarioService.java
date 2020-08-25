package com.jvs.api.pontointeligente.services;

import java.util.Optional;

import com.jvs.api.pontointeligente.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Busca um funcionário pelo CPF
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> findByCpf(String cpf);
	
	/**
	 * Busca um funcionário pelo Email
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> findByEmail(String email);
	
	/**
	 * Cadastra um novo funcionário
	 * 
	 * @param funcionario
	 * @return Funcionario 
	 */
	Funcionario persistir(Funcionario funcionario);
}
