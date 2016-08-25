package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Classe de filtro para a tela de atribuição de perfil.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/08/2016 15:30:00
 */
public class AtribuirPerfilFilter {

    private String cpfUsuario;
    private String nomeCompletoUsuario;
    private Integer codigoTipoUsuario;
    private Long codigoPerfil;
    private Long codigoUnidadeOrganizacional;
    private Integer codigoPostoTrabalho;

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

    public Integer getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public Long getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(Long codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public Long getCodigoUnidadeOrganizacional() {
        return codigoUnidadeOrganizacional;
    }

    public void setCodigoUnidadeOrganizacional(Long codigoUnidadeOrganizacional) {
        this.codigoUnidadeOrganizacional = codigoUnidadeOrganizacional;
    }

    public Integer getCodigoPostoTrabalho() {
        return codigoPostoTrabalho;
    }

    public void setCodigoPostoTrabalho(Integer codigoPostoTrabalho) {
        this.codigoPostoTrabalho = codigoPostoTrabalho;
    }
}
