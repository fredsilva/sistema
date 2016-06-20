package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link PostoTrabalho}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface PostoTrabalhoRepository extends BaseRepository<PostoTrabalho, Long> {

    String EXISTS_LOCK_REFERENCE_FUNCIONARIO =
            "SELECT CASE WHEN (COUNT(TUPT.IDENTIFICACAO_POSTO_TRABALHO) > 0) THEN 'true' "
                    + "ELSE 'false' END"
                    + " from SEFAZ_SEG.TA_USUARIO_POSTO_TRABALHO TUPT "
                    + "where TUPT.IDENTIFICACAO_POSTO_TRABALHO = :id";

    /**
     * Verifica se a Unidade Organizacional é pai de outras Unidades Organizacionais.
     *
     * @param id Identificação da Unidade Organizacional.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    @Query(value = EXISTS_LOCK_REFERENCE_FUNCIONARIO, nativeQuery = true)
    Boolean existsLockReferenceFuncionario(@Param(value = "id") Long id);
}
