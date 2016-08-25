package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class SolicitacaoUsuarioRepository extends BaseRepository<SolicitacaoUsuario, Long> {

    /**
     * Busca a solicitação para o CPF passado por parâmetro.
     * @param cpfUsuario cpf do {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}.
     * @return {@link SolicitacaoUsuario}
     */
    public SolicitacaoUsuario findOneByCpf(String cpfUsuario) {
        return findOne(select -> select.where().equal("cpfUsuario", cpfUsuario));
    }
}
