package br.gov.to.sefaz.seg.business.general.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 08/06/2016 17:40:00
 */
public class UsuarioSistemaFilter {

    private String cpfUsuario;
    private String nomeCompletoUsuario;
    private SituacaoUsuarioEnum situacaoUsuario;
    private Integer tipoUsuario;
    private String codigoEstado;
    private Integer codigoMunicipio;

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeCompletoUsuario() {
        return nomeCompletoUsuario;
    }

    public void setNomeCompletoUsuario(String nomeCompletoUsuario) {
        this.nomeCompletoUsuario = nomeCompletoUsuario;
    }

    public SituacaoUsuarioEnum getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(SituacaoUsuarioEnum situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }
}
