package com.jvs.api.pontointeligente.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jvs.api.pontointeligente.entities.Empresa;
import com.jvs.api.pontointeligente.entities.Funcionario;
import com.jvs.api.pontointeligente.entities.Lancamento;
import com.jvs.api.pontointeligente.enums.PerfilEnum;
import com.jvs.api.pontointeligente.enums.TipoEnum;
import com.jvs.api.pontointeligente.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		
		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();
		
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorFuncionario() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarPorFuncionarioPaginado() {
		PageRequestDto page = new PageRequestDto();
		Page<Lancamento> lancamentos = (Page<Lancamento>) this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException{
		Funcionario funcionario = new Funcionario();
		
		funcionario.setCpf("123465897810");
		funcionario.setEmail("jaime@teste.com");
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
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setFuncionario(funcionario);
		
		return lancamento;
	}
}
