package br.gov.to.sefaz.seg.business.gestao.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.Pattern;

/**
 * Classe de filtro para a tela de manutenção de usuário.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 08/07/2016 15:11:00
 */
public class ManterUsuarioSistemaFilter {

    @Pattern(regexp = "|.{5}.*",
            message = "#{seg_msg['seg.gestao.manterUsuarioSistema.filter.tamanho.nomeCompletoUsuario']}")
    private String nomeCompletoUsuario;
    private String cpfUsuario;
    private LocalDate dataCriacao;
    private SituacaoUsuarioEnum situacaoUsuario;
    private Long codigoUnidadeOrganizacional;
    private Integer codigoPostoTrabalho;
    private Integer codigoTipoUsuario;

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getLocalDataCriacao() {
        return Optional.ofNullable(dataCriacao).map(LocalDate::atStartOfDay).orElse(null);
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public SituacaoUsuarioEnum getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(SituacaoUsuarioEnum situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Integer getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }
}
