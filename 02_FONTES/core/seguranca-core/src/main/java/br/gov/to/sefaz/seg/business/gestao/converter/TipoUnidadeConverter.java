package br.gov.to.sefaz.seg.business.gestao.converter;


import br.gov.to.sefaz.persistence.domain.CodeData;
import br.gov.to.sefaz.seg.persistence.domain.TipoUnidade;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe que converte uma lista de {@link br.gov.to.sefaz.persistence.domain.CodeData} para uma lista de
 * {@link br.gov.to.sefaz.seg.persistence.domain.TipoUnidade}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 10/08/2016 09:56:00
 */
public class TipoUnidadeConverter implements Function<List<CodeData>, List<TipoUnidade>> {

    @Override
    public List<TipoUnidade> apply(List<CodeData> list) {
        return list.stream().map(o -> new TipoUnidade(o.getCode().toString().charAt(0), o.getValue()
                .toString())).collect(Collectors.toList());
    }
}
