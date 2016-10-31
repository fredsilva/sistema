package br.gov.to.sefaz.util.barcode.domain;

/**
 * Origem do Sistema que está solicitando a emissão do Dare.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 14/09/2016 11:08:00
 */
public enum OrigemSistemaEnum {

    SIAT("1"), DARE_E("2");

    private String codigo;

    OrigemSistemaEnum(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
