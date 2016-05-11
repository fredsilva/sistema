package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para os enums de arrecadação.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@ManagedBean(name = "enumsMB")
@RequestScoped
public class EnumsMB {

    public SituacaoEnum[] getSituacoes() {
        return SituacaoEnum.values();
    }

    public TipoContaEnum[] getTiposConta() {
        return TipoContaEnum.values();
    }

}
