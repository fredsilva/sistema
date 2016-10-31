package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErro;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStr;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * {@link org.springframework.stereotype.Repository} responsavel por operações de persistencia de
 * tipos de rejeição de arquivos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Repository
public class TipoRejeicaoArquivosRepository extends BaseRepository<TipoRejeicaoArquivos, Integer> {

    /**
     * Método para buscar registros que têm como referência o Tipo de Rejeição de Arquivo selecionado para deleção.
     *
     * @param id do Tipo de Rejeição de Arquivo
     * @return verdadeiro, se existirem referências, falso se não existirem.
     */
    public Boolean existsLockReference(Integer id) {
        return exists("tra", select -> select.whereId(id)
                .and().condition(where -> where
                        .exists(hqlSelect(ArquivoErro.class, "ae").columns("ae.idCodigoRejeicao")
                                .where().equalColumns("ae.idCodigoRejeicao", "tra.idCodigoRejeicao"))
                        .or().exists(hqlSelect(ArquivoErrosStr.class, "aes").columns("aes.idCodigoRejeicao")
                                .where().equalColumns("aes.idCodigoRejeicao", "tra.idCodigoRejeicao"))
                        .or().exists(hqlSelect(ArquivoRecepcao.class, "rc").columns("rc.idCodigoRejeicao")
                                .where().equalColumns("rc.idCodigoRejeicao", "tra.idCodigoRejeicao"))
                ));
    }

    /**
     * Método para atualizar a situação do Tipo de Rejeição de Arquivo.
     *
     * @param id       do Tipo de Rejeição de Arquivo.
     * @param situacao do Tipo de Rejeição de Arquivo.
     */
    public void updateSituacao(Integer id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }
}