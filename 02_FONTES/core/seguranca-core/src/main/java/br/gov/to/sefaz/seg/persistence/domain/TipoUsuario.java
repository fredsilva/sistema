package br.gov.to.sefaz.seg.persistence.domain;

import br.gov.to.sefaz.persistence.domain.CodeData;

/**
 * CodeData persistido na tabela TA_Parametro_Geral com nome LISTAGEM_TIPO_USUARIO.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
public class TipoUsuario extends CodeData<Integer, String> {

    private Long quantidadeUsuarios;

    public TipoUsuario(Integer codigoTipoUsuario, String descricaoTipoUsuario) {
        super(codigoTipoUsuario, descricaoTipoUsuario);
    }

    public TipoUsuario(Integer codigoTipoUsuario, String descricaoTipoUsuario, Long quantidadeUsuarios) {
        super(codigoTipoUsuario, descricaoTipoUsuario);
        this.quantidadeUsuarios = quantidadeUsuarios;
    }

    /**
     * Retorna o valor do atributo code do CodeData.
     * @return  código do Tipo Usuário
     */
    public Integer getCodigoTipoUsuario() {
        return getCode();
    }

    /**
     * Seta o valor do atributo code do CodeData.
     * @param codigoTipoUsuario código do Tipo Usuário
     */
    public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
        setCode(codigoTipoUsuario);
    }

    /**
     * Retorna o valor do atributo value do CodeData.
     * @return  descrição do Tipo Usuário
     */
    public String getDescricaoTipoUsuario() {
        return getValue();
    }

    /**
     * Seta o valor do atributo value do CodeData.
     * @param descricaoTipoUsuario descrição do Tipo Usuário
     */
    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        setValue(descricaoTipoUsuario);
    }

    public Long getQuantidadeUsuarios() {
        return quantidadeUsuarios;
    }

    public void setQuantidadeUsuarios(Long quantidadeUsuarios) {
        this.quantidadeUsuarios = quantidadeUsuarios;
    }
}
