package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;

import java.util.List;

/**
 * Fachada para acesso a dados de procuração.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/08/2016 10:31:00
 */
public interface ProcuracaoUsuarioFacade extends CrudFacade<ProcuracaoUsuario, Long> {

    /**
     * Retorna as Opções da aplicação que um usuario tem permissão de acessar, baseado no perfil.
     * @return as Opções da aplicação
     */
    List<OpcaoAplicacao> getOpcoesFromUsuario();

    /**
     * Seleciona os CPFs e CNPJs do usuario logado na sessão.
     *
     * @return identificações do usuario logado
     */
    List<ListagemCpfProcuracao> findAllCpfProcuracaoFromUsuario();

    /**
     * Retorna o nome do usuario baseado no CPF.
     *
     * @param usuarioCpf cpf do usuario
     * @return nome do usuario
     * @throws br.gov.to.sefaz.exception.BusinessException se o usuario não existe na base de dados
     */
    String findUsuarioNomeById(String usuarioCpf);

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
