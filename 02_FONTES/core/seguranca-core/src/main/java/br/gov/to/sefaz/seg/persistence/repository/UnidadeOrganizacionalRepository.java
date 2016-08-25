package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Repository
public class UnidadeOrganizacionalRepository extends BaseRepository<UnidadeOrganizacional, Long> {

    /**
     * Verifica se a Unidade Organizacional é pai de outras Unidades Organizacionais.
     *
     * @param id Identificação da Unidade Organizacional.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    public boolean existsLockReferencePai(Long id) {
        return exists(select -> select.where().equal("unidOrganizacPai", id));
    }

    /**
     * Verifica se a Unidade Organizacional tem referências de posto trabalho antes de deletar.
     *
     * @param id Identificação da Unidade Organizacional.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    public boolean existsLockReferencePostoTrabalho(Long id) {
        return exists("uo", select -> select.whereId(id)
                .and().exists(hqlSelect(PostoTrabalho.class, "pt")
                        .where().equalColumns("pt.identificacaoUnidOrganizac", "uo.identificacaoUnidOrganizac")));
    }

}
