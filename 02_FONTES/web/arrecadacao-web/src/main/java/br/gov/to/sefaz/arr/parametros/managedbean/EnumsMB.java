package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.persistence.enums.ClassificacaoReceitaEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoReceitaEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoRepasseEnum;
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

    private static final String SEPARATOR = " - ";

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

    public String getSituacaoLabel(SituacaoEnum situacaoEnum) {
        return situacaoEnum.getCode() + SEPARATOR + situacaoEnum.getLabel();
    }

    public String getTiposContaLabel(TipoContaEnum tipoContaEnum) {
        return tipoContaEnum.getCode() + SEPARATOR + tipoContaEnum.getLabel();
    }

    public String getTipoBarraLabel(TipoCodigoBarraEnum tipoBarra) {
        return tipoBarra.getCode() + SEPARATOR + tipoBarra.getLabel();
    }

    public String getTipoConvenioLabel(TipoConvenioEnum tipoConvenio) {
        return tipoConvenio.getCode() + SEPARATOR + tipoConvenio.getLabel();
    }

    public String getFormaPagamentoLabel(FormaPagamentoEnum formaPagamento) {
        return formaPagamento.getCode() + SEPARATOR + formaPagamento.getLabel();
    }

    public String getClassificacaoReceitaLabel(ClassificacaoReceitaEnum classificacaoReceita) {
        return classificacaoReceita.getCode() + SEPARATOR + classificacaoReceita.getLabel();
    }

    public String getTiposReceitaLabel(TipoReceitaEnum tipoReceita) {
        return tipoReceita.getCode() + SEPARATOR + tipoReceita.getLabel();
    }

    public String getTiposRepasseLabel(TipoRepasseEnum tipoRepasse) {
        return tipoRepasse.getCode() + SEPARATOR + tipoRepasse.getLabel();
    }

    public String getTiposPedidoAcaoLabel(TipoPedidoAcoesEnum tipoPedidoAcoes) {
        return tipoPedidoAcoes.getCode() + SEPARATOR + tipoPedidoAcoes.getLabel();
    }

    public String getTiposPedidoCampoLabel(TipoPedidoCampoEnum tipoPedidoAcoes) {
        return tipoPedidoAcoes.getCode() + SEPARATOR + tipoPedidoAcoes.getLabel();
    }

    public String getGetReceitaTaxaLabel() {
        return null;
    }
}
