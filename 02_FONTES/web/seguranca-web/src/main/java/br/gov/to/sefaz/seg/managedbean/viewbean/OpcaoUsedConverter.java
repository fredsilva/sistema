package br.gov.to.sefaz.seg.managedbean.viewbean;

import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Conversor de {@link OpcaoAplicacao} em {@link OpcaoUsedViewBean}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/08/2016 10:58:00
 */
@Component
public class OpcaoUsedConverter {

    /**
     * Converte uma coleção de {@link OpcaoAplicacao} em uma coleção de {@link OpcaoUsedViewBean}.
     *
     * @param opcoesAplicacao opções da aplicação
     * @param isUsedFunction função para definir se uma opção esta em uso ou não
     * @return bean para exibição de uma opção de aplicação e se ela esta em uso ou não
     */
    public List<OpcaoUsedViewBean> convert(Collection<OpcaoAplicacao> opcoesAplicacao,
            Function<OpcaoAplicacao, Boolean> isUsedFunction) {
        return opcoesAplicacao.stream()
                .map(oa -> convert(oa, isUsedFunction))
                .collect(Collectors.toList());
    }

    private OpcaoUsedViewBean convert(OpcaoAplicacao opcaoAplicacao, Function<OpcaoAplicacao, Boolean> isUsedFunction) {
        return new OpcaoUsedViewBean(
                opcaoAplicacao.getAbreviacaoModulo(),
                opcaoAplicacao.getDescricaoAplicacao(),
                opcaoAplicacao.getDescripcaoOpcao(),
                opcaoAplicacao.getIdentificacaoOpcaoAplicacao(),
                isUsedFunction.apply(opcaoAplicacao));
    }
}
