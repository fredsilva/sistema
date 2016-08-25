package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para os enums de arrecadação.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@ManagedBean(name = "segEnumsMB")
@RequestScoped
public class SegEnumsMB {

    public SituacaoUsuarioEnum[] getSituacoes() {
        return SituacaoUsuarioEnum.values();
    }

    /**
     * Retorna a concatenação do Label da situação.
     * @param situacaoEnum situação para montar o retorno.
     * @return da concatenação.
     */
    public String getSituacaoLabel(SituacaoUsuarioEnum situacaoEnum) {
        return situacaoEnum.getLabel();
    }
}
