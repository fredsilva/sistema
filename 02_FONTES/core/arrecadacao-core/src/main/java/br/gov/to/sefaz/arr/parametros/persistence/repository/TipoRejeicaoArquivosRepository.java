package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoRejeicaoArquivos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} responsavel por operações de persistencia de
 * tipos de rejeição de arquivos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Repository
public interface TipoRejeicaoArquivosRepository extends CrudRepository<TipoRejeicaoArquivos, Integer> {

    String CODIGO_REJEICAO_EXISTS_IN_OTHERS_TABLES = "SELECT case when (count(*) > 0) then 'true' else 'false' end "
            + "FROM SEFAZ_ARR.TA_ARQUIVO_ERROS_STR erroStr "
            + "LEFT JOIN SEFAZ_ARR.TA_ARQUIVO_ERRO erro on erro.ID_CODIGO_REJEICAO = erroStr.ID_CODIGO_REJEICAO "
            + "LEFT JOIN SEFAZ_ARR.TA_ARQUIVO_RECEPCAO recepcao on "
            + "recepcao.ID_CODIGO_REJEICAO = erroStr.ID_CODIGO_REJEICAO WHERE erroStr.ID_CODIGO_REJEICAO = :id";

    @Query(value = CODIGO_REJEICAO_EXISTS_IN_OTHERS_TABLES, nativeQuery = true)
    Boolean existsInAnotherTable(@Param(value = "id") Integer id);

}
