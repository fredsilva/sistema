package br.gov.to.sefaz.seg.persistence.domain;

import br.gov.to.sefaz.persistence.domain.CodeData;

/**
 * CodeData persistido na tabela TA_Parametro_Geral com nome LISTAGEM_TIPO_UNIDADE.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 10/08/2016 10:30:37
 */
public class TipoUnidade extends CodeData<Character, String> {

    public TipoUnidade(Character codigoTipoUnidade, String descricaoTipoUnidade) {
        super(codigoTipoUnidade, descricaoTipoUnidade);
    }

    /**
     * Retorna o valor do atributo code do CodeData.
     * @return  código do Tipo Usuário
     */
    public Character getCodigoTipoUnidade() {
        return getCode();
    }

    /**
     * Seta o valor do atributo code do CodeData.
     * @param codigoTipoUnidade código do Tipo Usuário
     */
    public void setCodigoTipoUnidade(Character codigoTipoUnidade) {
        setCode(codigoTipoUnidade);
    }

    /**
     * Retorna o valor do atributo value do CodeData.
     * @return  descrição do Tipo Usuário
     */
    public String getDescricaoTipoUnidade() {
        return getValue();
    }

    /**
     * Seta o valor do atributo value do CodeData.
     * @param descricaoTipoUnidade descrição do Tipo Usuário
     */
    public void setDescricaoTipoUnidade(String descricaoTipoUnidade) {
        setValue(descricaoTipoUnidade);
    }

}
