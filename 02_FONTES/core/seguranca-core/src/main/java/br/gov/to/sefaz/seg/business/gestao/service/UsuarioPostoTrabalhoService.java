package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalhoPK;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Contrato de acesso do serviço de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
@Service
public interface UsuarioPostoTrabalhoService extends CrudService<UsuarioPostoTrabalho, UsuarioPostoTrabalhoPK> {

    /**
     * Remove referência do {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} com o
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho#identificacaoPostoTrabalho}.
     * @param cpf cpf do usuário.
     * @param identificacaoPostoTrabalho id do Posto de Trabalho.
     * @return {@link Optional} de UsuarioPostoTrabalho.
     */
    Optional<UsuarioPostoTrabalho> removeUsuarioPostoTrabalho(String cpf, Integer identificacaoPostoTrabalho);

    /**
     * Salva ou atualiza o {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho} pelo
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho#cpfUsuario} e
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho#identificacaoPostoTrabalho}.
     * @param cpf cpf do usuário.
     * @param identificacaoPostoTrabalho id do posto de trabalho.
     */
    UsuarioPostoTrabalho saveOrUpdate(String cpf, Integer identificacaoPostoTrabalho);

}
