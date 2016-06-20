package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Repository
public interface PlanoContasRepository extends BaseRepository<PlanoContas, Long> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(pl.id_planocontas) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_plano_contas pl"
            + " INNER JOIN sefaz_arr.ta_receitas rec ON rec.id_plano_contas = pl.id_planocontas"
            + " WHERE pl.id_planocontas = :id";

    /**
     * Valida se já existe um PlanoContas.
     * @param codigo identificação do PlanoContas.
     * @return verdadeiro ou falso.
     */
    @Query("SELECT CASE WHEN COUNT(pc.codigoPlanoContas) > 0 THEN true ELSE false END FROM PlanoContas pc"
            + " WHERE pc.codigoPlanoContas = :codigo")
    Boolean existsByCodigo(@Param("codigo") String codigo);

    /**
     * Busca o PlanoContas pelo código.
     * @param codigo do PlanoContas.
     * @return código selecionado no banco.
     */
    @Query("SELECT pc.idPlanocontas FROM PlanoContas pc WHERE pc.codigoPlanoContas = :codigo")
    Long findIdByCodigo(@Param("codigo") String codigo);

    /**
     * Busca referências para o PlanoContas no banco de dados.
     * @param id identificação do PlanoContas.
     * @return verdadeiro ou falso.
     */
    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param("id") Long id);

    /**
     * Atualiza a situação.
     * @param id identificação do PlanoContas.
     * @param situacao nova.
     * @return código do banco de dados.
     */
    @Modifying
    @Query("UPDATE PlanoContas SET situacao = :situacao WHERE idPlanocontas = :id")
    int updateSituacao(@Param("id") Long id, @Param("situacao") SituacaoEnum situacao);
}
