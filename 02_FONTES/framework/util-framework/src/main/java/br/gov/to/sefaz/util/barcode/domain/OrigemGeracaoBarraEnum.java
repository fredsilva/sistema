package br.gov.to.sefaz.util.barcode.domain;

/**
 * Origem da Geração do Código de Barras.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 14/09/2016 11:32:00
 */
public enum OrigemGeracaoBarraEnum {

    DARE("6", "0027"), GNRE("8", "0316");

    private String idValor;
    private String codOrgao;

    OrigemGeracaoBarraEnum(String idValor, String codOrgao) {
        this.idValor = idValor;
        this.codOrgao = codOrgao;
    }

    public String getIdValor() {
        return idValor;
    }

    public void setIdValor(String idValor) {
        this.idValor = idValor;
    }

    public String getCodOrgao() {
        return codOrgao;
    }

    public void setCodOrgao(String codOrgao) {
        this.codOrgao = codOrgao;
    }
}
