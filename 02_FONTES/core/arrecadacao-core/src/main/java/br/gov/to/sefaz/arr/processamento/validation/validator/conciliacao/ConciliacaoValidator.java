package br.gov.to.sefaz.arr.processamento.validation.validator.conciliacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.ResumoStr;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoConciliacaoEnum;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Componente com as validações do processo de conciliação de arquivos.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 13/07/2016 15:40:00
 */
@Component
public class ConciliacaoValidator {

    /**
     * Analisa os valores e retorna a situação correspondente da conciliação
     * {@link br.gov.to.sefaz.arr.persistence.enums.SituacaoConciliacaoEnum}.
     *
     * @param resumoStr entidade que representa a conciliação.
     * @return a situação da conciliação.
     */
    public SituacaoConciliacaoEnum getSituacao(ResumoStr resumoStr) {
        if (!Objects.equals(resumoStr.getQuantidadeRecepcionada(), resumoStr.getQuantidadeArrecadada())) {
            return SituacaoConciliacaoEnum.NAO_CONCILIADO;
        }

        Long quantidadeRecepcionadParcial = resumoStr.getQuantidadeRecepcionadParcial();
        Long quantidadeArrecadadoParcial = resumoStr.getQuantidadeArrecadadoParcial() - 1;

        if (!Objects.equals(quantidadeRecepcionadParcial, quantidadeArrecadadoParcial)) {
            return SituacaoConciliacaoEnum.NAO_CONCILIADO;
        }

        if (!(Objects.equals(resumoStr.getValorRecepcionadoParcial(), BigDecimal.ZERO)
                && quantidadeRecepcionadParcial == 0)
                && !Objects.equals(resumoStr.getValorRecepcionadoParcial(), resumoStr.getValorArrecadadoParcial())) {
            return SituacaoConciliacaoEnum.NAO_CONCILIADO;
        }

        if (!Objects.equals(resumoStr.getValorRecepcionado(), resumoStr.getValorArrecadado())) {
            return SituacaoConciliacaoEnum.NAO_CONCILIADO;
        }

        if (!Objects.equals(resumoStr.getValorArrecadado(), resumoStr.getValorLancamentoStr())) {
            return SituacaoConciliacaoEnum.NAO_CONCILIADO;
        }

        return SituacaoConciliacaoEnum.CONCILIADO;
    }

    /**
     * Retorna a data de recepção do arquivo no formato {@link java.time.LocalDateTime}.
     *
     * @param arquivosRecepcao arquivos recepcionados.
     * @return a data de recepção do arquivo.
     */
    public LocalDateTime getDataRecepcao(List<ArquivoRecepcao> arquivosRecepcao) {
        if (!arquivosRecepcao.isEmpty()) {

            for (ArquivoRecepcao arquivoRecepcao : arquivosRecepcao) {
                if (arquivoRecepcao.getCaracteristicaArquivo() == TipoArquivoEnum.CONSOLIDADO) {
                    return arquivoRecepcao.getDataProcessamento();
                }
            }
        }
        return null;
    }

}
