package br.gov.to.sefaz.seg.business.gestao.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:08:00
 */
public class CadastroSenhaFilter {

    private String cpfUsuario;
    private String nomeCompletoUsuario;
    private SituacaoSolicitacaoEnum situacaoSolicitacao;
    private LocalDate dataSolicitacao;

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

    public SituacaoSolicitacaoEnum getSituacaoSolicitacao() {
        return situacaoSolicitacao;
    }

    public void setSituacaoSolicitacao(SituacaoSolicitacaoEnum situacaoSolicitacao) {
        this.situacaoSolicitacao = situacaoSolicitacao;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDate() {
        return Optional.ofNullable(dataSolicitacao).map(LocalDate::atStartOfDay).orElse(null);
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
