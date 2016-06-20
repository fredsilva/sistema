package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface TipoRejeicaoArquivosRepository extends BaseRepository<TipoRejeicaoArquivos, Integer> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (count(tra.id_codigo_rejeicao) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_tipo_rejeicao_arquivos tra WHERE tra.id_codigo_rejeicao = :id"
            + " AND (EXISTS(SELECT ae.id_codigo_rejeicao FROM sefaz_arr.ta_arquivo_erro ae"
            + "     WHERE ae.id_codigo_rejeicao = tra.id_codigo_rejeicao)"
            + " OR EXISTS(SELECT aes.id_codigo_rejeicao FROM sefaz_arr.ta_arquivo_erros_str aes"
            + "     WHERE aes.id_codigo_rejeicao = tra.id_codigo_rejeicao)"
            + " OR EXISTS(SELECT rc.id_codigo_rejeicao FROM sefaz_arr.ta_arquivo_recepcao rc"
            + "     WHERE rc.id_codigo_rejeicao = tra.id_codigo_rejeicao))";

    /**
     * Método para buscar registros que têm como referência o Tipo de Rejeição de Arquivo selecionado para deleção.
     * @param id do Tipo de Rejeição de Arquivo
     * @return verdadeiro, se existirem referências, falso se não existirem.
     */
    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param(value = "id") Integer id);

    /**
     * Método para atualizar a situação do Tipo de Rejeição de Arquivo.
     * @param id do Tipo de Rejeição de Arquivo.
     * @param situacao do Tipo de Rejeição de Arquivo.
     * @return identificação do registro atualizado.
     */
    @Modifying
    @Query("UPDATE TipoRejeicaoArquivos SET situacao = :situacao WHERE idCodigoRejeicao = :id")
    int updateSituacao(@Param("id") Integer id, @Param("situacao") SituacaoEnum situacao);
}