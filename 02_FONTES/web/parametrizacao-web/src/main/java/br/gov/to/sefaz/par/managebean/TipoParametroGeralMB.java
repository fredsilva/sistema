package br.gov.to.sefaz.par.managebean;

import br.gov.to.sefaz.par.gestao.persistence.enums.TipoParametroGeralEnum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para os enums de arrecadação.
 *
 * @author <a href="mailto:carlos.junior@ntconsult.com.br">carlos.junior</a>
 * @since 28/06/2016 10:51:00
 */
@ManagedBean(name = "tipoParametroGeralMB")
@RequestScoped
public class TipoParametroGeralMB {

    public TipoParametroGeralEnum[] getSituacoes() {
        return TipoParametroGeralEnum.values();
    }

    /**
     * Retorna a concatenação do Label da situação.
     * @param tipoEnum situação para montar o retorno.
     * @return da concatenação.
     */
    public String getSituacaoLabel(TipoParametroGeralEnum tipoEnum) {
        return tipoEnum.getLabel();
    }
}
