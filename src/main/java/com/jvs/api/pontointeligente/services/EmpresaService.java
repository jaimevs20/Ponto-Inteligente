package com.jvs.api.pontointeligente.services;

import java.util.Optional;


import com.jvs.api.pontointeligente.entities.Empresa;

public interface EmpresaService {
	/**
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarCNPJ(String cnpj);
	
	/**
	 * Cadastra uma empresa
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
}
