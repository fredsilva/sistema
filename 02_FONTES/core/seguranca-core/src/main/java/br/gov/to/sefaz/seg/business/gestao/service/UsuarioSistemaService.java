package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
public interface UsuarioSistemaService extends CrudService<UsuarioSistema, String> {

    /**
     * Recupera uma lista de usuários baseado no nome e no CPF (ou em apenas um caso o outro esteja ausente).
     *
     * @param cpf CPF dos usuários que se deseja encontrar
     * @param nome Nome dos usuários que se deseja encontrar
     * @return Uma lista com todos os resultados da base dadados que fizerem match com LIKE e AND ao nome e cpf
     *         informado
     */
    Collection<UsuarioSistema> findAllByCpfAndName(Long cpf, String nome);

    /**
     * Método que executa os custons validators de Usuário Sistema.
     *
     * @param usuarioSistema Usuário a ser validado
     */
    void validate(UsuarioSistema usuarioSistema);

    /**
     * Bloqueia um usuario no sistema e seta a data de bloqueio para 2 horas no futuro.
     * @param cpf cpf do ususario
     */
    void blockUser(String cpf);

    /**
     * Desbloqueia um usuario no sistema e seta a data de bloqueio para null.
     * @param cpf cpf do ususario
     */
    void unblockUser(String cpf);
}
