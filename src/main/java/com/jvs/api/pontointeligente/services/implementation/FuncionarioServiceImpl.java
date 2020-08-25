package com.jvs.api.pontointeligente.services.implementation;

import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvs.api.pontointeligente.entities.Funcionario;
import com.jvs.api.pontointeligente.repositories.FuncionarioRepository;
import com.jvs.api.pontointeligente.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

	private static final org.jboss.logging.Logger log = LoggerFactory.logger(EmpresaServiceImpl.class);

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public Optional<Funcionario> findByCpf(String cpf) {
		log.info("Buscando funcionário por CPF: "+ cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> findByEmail(String email) {
		log.info("Buscando funcionário por Email: "+ email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}
	
	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Cadastrando funcioários: "+ funcionario);
		return this.funcionarioRepository.save(funcionario);
	}

}
