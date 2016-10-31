package br.gov.to.sefaz.arr.parametros.business.service.filter;

import br.gov.to.sefaz.arr.persistence.enums.TipoContaEnum;

/**
 * Objeto para envio de dados para filtros de busca para o Managed Bean de Plano de contas.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/05/2016 10:51:00
 */
public class PlanoContasFilter {

    private String codigoPlanoContas;
    private String nomeConta;
    private String codigoContabil;
    private TipoContaEnum tipoConta;

    public String getCodigoPlanoContas() {
        return codigoPlanoContas;
    }

    public void setCodigoPlanoContas(String codigoPlanoContas) {
        this.codigoPlanoContas = codigoPlanoContas;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getCodigoContabil() {
        return codigoContabil;
    }

    public void setCodigoContabil(String codigoContabil) {
        this.codigoContabil = codigoContabil;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }
}
