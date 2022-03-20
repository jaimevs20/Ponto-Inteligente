package com.jvs.api.pontointeligente.services.implementation;

import com.jvs.api.pontointeligente.entities.Lancamento;
import com.jvs.api.pontointeligente.repositories.LancamentoRepository;
import com.jvs.api.pontointeligente.services.LancamentoService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private static final org.jboss.logging.Logger log = LoggerFactory.logger(LancamentoServiceImpl.class);

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
        log.info("Buscando Lancamento do funcionario: "+ funcionarioId);

        return this.lancamentoRepository.findByFuncionarioId(funcionarioId,pageRequest);
    }

    @Override
    public Optional<Lancamento> buscarPorId(Long id) {
        log.info("Buscando Lançamento pelo Id: "+ id);

        return this.lancamentoRepository.findById(id);
    }

    @Override
    public Lancamento persistirLancamento(Lancamento lancamento) {
        log.info("Salvando lançamento... "+ lancamento);

        return this.lancamentoRepository.save(lancamento);
    }

    @Override
    public void removerLancamento(Long id) {
        log.info("Removendo lançamento... "+ id);

        this.lancamentoRepository.deleteById(id);
    }
}
