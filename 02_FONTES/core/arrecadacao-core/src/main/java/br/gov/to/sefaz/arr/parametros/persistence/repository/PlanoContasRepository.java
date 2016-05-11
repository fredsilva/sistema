package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Repository
public interface PlanoContasRepository extends CrudRepository<PlanoContas, Long> {

    // from PlanoContas p where p.codigoPlanoContas = :codigoPlanoContas AND
    // p.nomeConta = :nomeConta AND p.tipoConta = :tipoConta
    @Query("SELECT p FROM PlanoContas p WHERE p.codigoPlanoContas = :codigoPlanoContas "
            + "AND p.nomeConta = :nomeConta " + "AND p.codigoContabil = :codigoContabil "
            + "AND p.tipoConta = :tipoConta")
    List<PlanoContas> find(@Param("codigoPlanoContas") String codigoPlanoContas,
            @Param("nomeConta") String nomePlano, @Param("codigoContabil") String codigoContabil,
            @Param("tipoConta") TipoContaEnum tipoConta);

}
