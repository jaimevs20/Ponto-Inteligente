package com.jvs.api.pontointeligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jvs.api.pontointeligente.entities.Funcionario;


@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Funcionario findByCpf(String cpf);
	Funcionario findByEmail(String email);
	Funcionario findByCpfOrEmail(String cpf,String email);
	

}
