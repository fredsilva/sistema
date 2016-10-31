package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.dare.enums.ValorAliquotaEnum;
import br.gov.to.sefaz.arr.dare.enums.ValorMultaEnum;
import br.gov.to.sefaz.arr.persistence.enums.ClassificacaoReceitaEnum;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoRepasseEnum;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para os enums de arrecadação.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@ManagedBean(name = "arrEnumsMB")
@RequestScoped
public class ArrEnumsMB {

    public SituacaoEnum[] getSituacoes() {
        return SituacaoEnum.values();
    }

    public TipoContaEnum[] getTiposConta() {
        return TipoContaEnum.values();
    }

    public TipoCodigoBarraEnum[] getTiposBarra() {
        return TipoCodigoBarraEnum.values();
    }

    public TipoConvenioEnum[] getTiposConvenio() {
        return TipoConvenioEnum.values();
    }

    public FormaPagamentoEnum[] getFormasPagamento() {
        return FormaPagamentoEnum.values();
    }

    public ClassificacaoReceitaEnum[] getClassificacaoReceitas() {
        return ClassificacaoReceitaEnum.values();
    }

    public TipoReceitaEnum[] getTiposReceita() {
        return TipoReceitaEnum.values();
    }

    public TipoRepasseEnum[] getTiposRepasse() {
        return TipoRepasseEnum.values();
    }

    public TipoPedidoAcoesEnum[] getTiposPedidoAcoes() {
        return TipoPedidoAcoesEnum.values();
    }

    public TipoPedidoCampoEnum[] getTiposPedidoCampos() {
        return TipoPedidoCampoEnum.values();
    }

    public ValorMultaEnum[] getMultas() {
        return ValorMultaEnum.values();
    }

    public ValorAliquotaEnum[] getAliquotas() {
        return ValorAliquotaEnum.values();
    }

}
