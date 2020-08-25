package com.jvs.api.pontointeligente.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jvs.api.pontointeligente.entities.Empresa;
import com.jvs.api.pontointeligente.entities.Funcionario;
import com.jvs.api.pontointeligente.enums.PerfilEnum;
import com.jvs.api.pontointeligente.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String CPF = "5146364500100";
	private static final String EMAIL = "teste@teste.com";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}
	
	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException{
		Funcionario funcionario = new Funcionario();
		
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456789"));
		funcionario.setNome("Jaime");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		
		empresa.setRazaoSocial("Empresa de Exemplo");
		empresa.setCnpj("14523698745123");
		
		return empresa;
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
		assertEquals(CPF, funcionario.getCpf());
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailECpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("654546565456",EMAIL);
		assertNotNull(funcionario);
	}
}
