package com.jvs.api.pontointeligente.services;

import com.jvs.api.pontointeligente.entities.Funcionario;
import com.jvs.api.pontointeligente.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LancamentoService {

    /**
     * Busca a lista de lançamentos por funcionário
     *
     * @param funcionarioId
     * @param pageRequest
     * @return Page<Lancamento>
     */
    Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

    /**
     * Retorna um lançamento por Id
     *
     * @param id
     * @return Optional<Lancamento>
     */
    Optional<Lancamento>buscarPorId (Long id);

    /**
     * Persiste um Lancamento na base
     *
     * @param lancamento
     * @return Lancamento
     */
    Lancamento persistirLancamento(Lancamento lancamento);

    /**
     * Remove um lancamento
     *
     * @param id
     */
    void removerLancamento(Long id);
}
