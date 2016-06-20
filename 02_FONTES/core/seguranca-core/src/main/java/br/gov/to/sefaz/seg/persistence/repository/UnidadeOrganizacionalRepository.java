package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link UnidadeOrganizacional}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Repository
public interface UnidadeOrganizacionalRepository extends BaseRepository<UnidadeOrganizacional, Long> {

    String EXISTS_LOCK_REFERENCE_PAI =
            "SELECT CASE WHEN (COUNT(TUO.UNID_ORGANIZAC_PAI) > 0) THEN 'true' ELSE 'false' END"
                    + " from SEFAZ_SEG.TA_UNIDADE_ORGANIZACIONAL TUO "
                    + "where TUO.UNID_ORGANIZAC_PAI = :id";

    String EXISTS_LOCK_REFERENCE_POSTO_TRABALHO =
            "SELECT CASE WHEN (COUNT(TPT.IDENTIFICACAO_UNID_ORGANIZAC) > 0) THEN 'true' "
                    + "ELSE 'false' END"
                    + " from SEFAZ_SEG.TA_POSTO_TRABALHO TPT "
                    + "where TPT.IDENTIFICACAO_UNID_ORGANIZAC = :id";

    /**
     * Verifica se a Unidade Organizacional é pai de outras Unidades Organizacionais.
     *
     * @param id Identificação da Unidade Organizacional.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    @Query(value = EXISTS_LOCK_REFERENCE_PAI, nativeQuery = true)
    Boolean existsLockReferencePai(@Param(value = "id") Long id);

    /**
     * Verifica se a Unidade Organizacional tem referências de posto trabalho antes de deletar.
     *
     * @param id Identificação da Unidade Organizacional.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    @Query(value = EXISTS_LOCK_REFERENCE_POSTO_TRABALHO, nativeQuery = true)
    Boolean existsLockReferencePostoTrabalho(@Param(value = "id") Long id);

}
