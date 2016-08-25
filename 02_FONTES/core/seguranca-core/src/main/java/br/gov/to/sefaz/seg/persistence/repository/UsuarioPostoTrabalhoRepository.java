package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalhoPK;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class UsuarioPostoTrabalhoRepository extends BaseRepository<UsuarioPostoTrabalho, UsuarioPostoTrabalhoPK> {

    /**
     * Verifica se o {@link br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho} está sendo utilizado por um
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}.
     *
     * @param idPostoTrabalho Identificação do posto trabalho.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    public boolean existsLockReferenceFuncionario(Integer idPostoTrabalho) {
        return exists(select -> select.where().equal("identificacaoPostoTrabalho", idPostoTrabalho));
    }
}
