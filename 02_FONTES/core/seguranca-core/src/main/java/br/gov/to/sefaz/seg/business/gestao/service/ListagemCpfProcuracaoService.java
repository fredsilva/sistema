package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao;

import java.util.List;

/**
 * Serviço de acesso a entidade {@link br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/09/2016 14:51:00
 */
public interface ListagemCpfProcuracaoService {

    /**
     * Busca todos os CPF e CNPJ de um usuário baseado no CPF.
     *
     * @param cpf cpf do usuário
     * @return cpf e cnpj do usuario
     */
    List<ListagemCpfProcuracao> findByCpf(String cpf);
}
