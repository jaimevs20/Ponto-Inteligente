package com.jvs.api.pontointeligente.services.implementation;

import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvs.api.pontointeligente.entities.Empresa;
import com.jvs.api.pontointeligente.repositories.EmpresaRepository;
import com.jvs.api.pontointeligente.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final org.jboss.logging.Logger log = LoggerFactory.logger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarCNPJ(String cnpj) {
		log.info("Buscando uma empresa. CNPJ: "+ cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Criando a empresa: "+ empresa);
		return this.empresaRepository.save(empresa);
	}
	
	

}
