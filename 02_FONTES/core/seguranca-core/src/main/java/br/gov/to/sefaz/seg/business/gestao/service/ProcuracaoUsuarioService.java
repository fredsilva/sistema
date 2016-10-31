package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;

/**
 * Serviço para manipulação da entidade {@link ProcuracaoUsuario}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/08/2016 10:33:00
 */
public interface ProcuracaoUsuarioService extends CrudService<ProcuracaoUsuario, Long> {

    /**
     * Encontra uma procuração baseado no CPF do procurador e do procurado.
     *
     * @param cpfOrigem cpf do procurado
     * @param procuradorCpf cpf do procurador
     * @return procuração
     */
    ProcuracaoUsuario findProcuracaoByCpf(String cpfOrigem, String procuradorCpf);

    /**
     * Encontra uma procuração baseado no CPF do procurador e cnpj do procurado.
     *
     * @param cnpjOrigem cnpj do procurado
     * @param procuradorCpf cpf do procurador
     * @return procuração
     */
    ProcuracaoUsuario findProcuracaoByCnpj(String cnpjOrigem, String procuradorCpf);
}
