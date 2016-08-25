package br.gov.to.sefaz.seg.business.gestao.service.filter;

import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;

/**
 * Filtro para a classe {@link UsuarioPerfil} para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 04/07/2016 14:34:00
 */
public class AtivarInativarPerfilFilter {

    private Long codigoUnidadeOrganizacional;
    private Integer codigoPostoTrabalho;
    private String nomeCompletoUsuario;
    private String cpfUsuario;

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
