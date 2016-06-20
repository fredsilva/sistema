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

    /**
     * Retorna a concatenação do Label da situação.
     * @param situacaoEnum situação para montar o retorno.
     * @return da concatenação.
     */
    public String getSituacaoLabel(SituacaoEnum situacaoEnum) {
        return situacaoEnum.getCode() + SEPARATOR + situacaoEnum.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo conta.
     * @param tipoContaEnum situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTiposContaLabel(TipoContaEnum tipoContaEnum) {
        return tipoContaEnum.getCode() + SEPARATOR + tipoContaEnum.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo barra.
     * @param tipoBarra situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTipoBarraLabel(TipoCodigoBarraEnum tipoBarra) {
        return tipoBarra.getCode() + SEPARATOR + tipoBarra.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo convênio.
     * @param tipoConvenio situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTipoConvenioLabel(TipoConvenioEnum tipoConvenio) {
        return tipoConvenio.getCode() + SEPARATOR + tipoConvenio.getLabel();
    }

    /**
     * Retorna a concatenação do Label da forma de pagamento.
     * @param formaPagamento situação para montar o retorno.
     * @return da concatenação.
     */
    public String getFormaPagamentoLabel(FormaPagamentoEnum formaPagamento) {
        return formaPagamento.getCode() + SEPARATOR + formaPagamento.getLabel();
    }

    /**
     * Retorna a concatenação do Label da classificação da receita.
     * @param classificacaoReceita situação para montar o retorno.
     * @return da concatenação.
     */
    public String getClassificacaoReceitaLabel(ClassificacaoReceitaEnum classificacaoReceita) {
        return classificacaoReceita.getCode() + SEPARATOR + classificacaoReceita.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo de receita.
     * @param tipoReceita situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTiposReceitaLabel(TipoReceitaEnum tipoReceita) {
        return tipoReceita.getCode() + SEPARATOR + tipoReceita.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo de repasse.
     * @param tipoRepasse situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTiposRepasseLabel(TipoRepasseEnum tipoRepasse) {
        return tipoRepasse.getCode() + SEPARATOR + tipoRepasse.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo pedido de ações.
     * @param tipoPedidoAcoes situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTiposPedidoAcaoLabel(TipoPedidoAcoesEnum tipoPedidoAcoes) {
        return tipoPedidoAcoes.getCode() + SEPARATOR + tipoPedidoAcoes.getLabel();
    }

    /**
     * Retorna a concatenação do Label do tipo campo.
     * @param tipoPedidoAcoes situação para montar o retorno.
     * @return da concatenação.
     */
    public String getTiposPedidoCampoLabel(TipoPedidoCampoEnum tipoPedidoAcoes) {
        return tipoPedidoAcoes.getCode() + SEPARATOR + tipoPedidoAcoes.getLabel();
    }

    public String getGetReceitaTaxaLabel() {
        return null;
    }
}
