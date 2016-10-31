package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * View Bean de Débitos sem Pagemento Parcial referente ao painel Dados do Pagamento do Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/08/2016 15:30:00
 */
public class DebitoSemPagamentoParcialViewBean {

    private List<DareEPagamento> dareEPagamentos;
    private Long idDocumento;
    private String informacaoComplementar;

    public DebitoSemPagamentoParcialViewBean() {
        dareEPagamentos = new ArrayList<>();
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }

    public List<DareEPagamento> getDareEPagamentos() {
        return dareEPagamentos;
    }

    public void setDareEPagamentos(List<DareEPagamento> dareEPagamentos) {
        this.dareEPagamentos = dareEPagamentos;
    }

    public List<DareEPagamento> getDareEPagamentosSelected() {
        return getDareEPagamentos().stream().filter(dareEPagamento ->
                Objects.isNull(dareEPagamento.getSelected()) ? false
                        : dareEPagamento.getSelected()).collect(Collectors.toList());
    }

}
