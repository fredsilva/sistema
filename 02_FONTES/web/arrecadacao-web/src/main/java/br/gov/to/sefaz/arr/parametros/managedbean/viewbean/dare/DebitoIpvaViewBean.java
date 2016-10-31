package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * View Bean de Débitos IPVA referente ao painel Dados do Pagamento do Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/08/2016 15:32:00
 */
public class DebitoIpvaViewBean {

    private List<DareEPagamento> dareEPagamentos;
    private Long idDocumentoRecolherIpva;
    private DebitoIpvaFilter debitoIpvaFilter;
    private String informacaoComplementar;

    public DebitoIpvaViewBean() {
        dareEPagamentos = new ArrayList<>();
        debitoIpvaFilter = new DebitoIpvaFilter();
    }


    public Long getIdDocumentoRecolherIpva() {
        return idDocumentoRecolherIpva;
    }

    public void setIdDocumentoRecolherIpva(Long idDocumentoRecolherIpva) {
        this.idDocumentoRecolherIpva = idDocumentoRecolherIpva;
    }

    public DebitoIpvaFilter getDebitoIpvaFilter() {
        return debitoIpvaFilter;
    }

    public void setDebitoIpvaFilter(DebitoIpvaFilter debitoIpvaFilter) {
        this.debitoIpvaFilter = debitoIpvaFilter;
    }

    public List<DareEPagamento> getDareEPagamentos() {
        return dareEPagamentos;
    }

    public void setDareEPagamentos(List<DareEPagamento> dareEPagamentos) {
        this.dareEPagamentos = dareEPagamentos;
    }

    public List<DareEPagamento> getDareEPagamentosSelected() {
        return getDareEPagamentos().stream().filter(dareEPagamento ->
                Objects.isNull(dareEPagamento.getSelected()) ? false :
                        dareEPagamento.getSelected()).collect(Collectors.toList());
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }
}
