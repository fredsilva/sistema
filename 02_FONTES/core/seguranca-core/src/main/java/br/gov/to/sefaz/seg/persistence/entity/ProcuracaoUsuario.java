package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PROCURACAO_USUARIO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PROCURACAO_USUARIO", schema = "SEFAZ_SEG")
public class ProcuracaoUsuario extends AbstractEntity<Long> {

    private static final long serialVersionUID = 4454342268629733352L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SOLICITACAO_USUARIO")
    @SequenceGenerator(name = "SQ_SOLICITACAO_USUARIO", schema = "SEFAZ_SEG",
            sequenceName = "SQ_SOLICITACAO_USUARIO", allocationSize = 1)
    @Column(name = "IDENTIFICACAO_PROCUR_USUARIO")
    private Long identificacaoProcurUsuario;

    @Size(max = 11)
    @Column(name = "CPF_ORIGEM")
    private String cpfOrigem;

    @Size(max = 20)
    @Column(name = "CNPJ_ORIGEM")
    private String cnpjOrigem;

    @Size(max = 11)
    @Column(name = "CPF_PROCURADO")
    @NotEmpty(message = "#{seg_msg['seg.geral.procuracaoUsuario.cpfProcurado.vazio']}")
    private String cpfProcurado;

    @JoinColumn(name = "CPF_ORIGEM", referencedColumnName = "CPF_CNPJ",
            updatable = false, insertable = false)
    @OneToOne
    private ListagemCpfProcuracao cpfOrigemProcuracao;

    @JoinColumn(name = "CNPJ_ORIGEM", referencedColumnName = "CPF_CNPJ",
            updatable = false, insertable = false)
    @OneToOne
    private ListagemCpfProcuracao cnpjOrigemProcuracao;

    @JoinColumn(name = "IDENTIFICACAO_PROCUR_USUARIO", referencedColumnName = "IDENTIFICACAO_PROCUR_USUARIO",
            updatable = false, insertable = false)
    @OneToMany
    private List<ProcuracaoOpcao> procuracaoOpcoes;

    public ProcuracaoUsuario() {
        procuracaoOpcoes = new ArrayList<>();
        // Construtor para inicialização por reflexão.
    }

    public ProcuracaoUsuario(Long identificacaoProcurUsuario, String cpfOrigem, String cnpjOrigem,
            String cpfProcurado) {
        this();
        this.identificacaoProcurUsuario = identificacaoProcurUsuario;
        this.cpfOrigem = cpfOrigem;
        this.cnpjOrigem = cnpjOrigem;
        this.cpfProcurado = cpfProcurado;
    }

    @Override
    public Long getId() {
        return identificacaoProcurUsuario;
    }

    public Long getIdentificacaoProcurUsuario() {
        return identificacaoProcurUsuario;
    }

    public void setIdentificacaoProcurUsuario(Long identificacaoProcurUsuario) {
        this.identificacaoProcurUsuario = identificacaoProcurUsuario;
    }

    public String getCpfOrigem() {
        return cpfOrigem;
    }

    public void setCpfOrigem(String cpfOrigem) {
        this.cpfOrigem = cpfOrigem;
    }

    public String getCnpjOrigem() {
        return cnpjOrigem;
    }

    public void setCnpjOrigem(String cnpjOrigem) {
        this.cnpjOrigem = cnpjOrigem;
    }

    public String getCpfProcurado() {
        return cpfProcurado;
    }

    public void setCpfProcurado(String cpfProcurado) {
        this.cpfProcurado = cpfProcurado;
    }

    public List<ProcuracaoOpcao> getProcuracaoOpcoes() {
        return procuracaoOpcoes;
    }

    public void setProcuracaoOpcoes(List<ProcuracaoOpcao> procuracaoOpcoes) {
        this.procuracaoOpcoes = procuracaoOpcoes;
    }

    public ListagemCpfProcuracao getCpfOrigemProcuracao() {
        return cpfOrigemProcuracao;
    }

    public void setCpfOrigemProcuracao(ListagemCpfProcuracao cpfOrigemProcuracao) {
        this.cpfOrigemProcuracao = cpfOrigemProcuracao;
    }

    public ListagemCpfProcuracao getCnpjOrigemProcuracao() {
        return cnpjOrigemProcuracao;
    }

    public void setCnpjOrigemProcuracao(ListagemCpfProcuracao cnpjOrigemProcuracao) {
        this.cnpjOrigemProcuracao = cnpjOrigemProcuracao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoProcurUsuario, cpfOrigem, cnpjOrigem, cpfProcurado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcuracaoUsuario that = (ProcuracaoUsuario) obj;
        return Objects.equals(this.identificacaoProcurUsuario, that.identificacaoProcurUsuario)
                && Objects.equals(this.cpfOrigem, that.cpfOrigem) && Objects.equals(this.cnpjOrigem, that.cnpjOrigem)
                && Objects.equals(this.cpfProcurado, that.cpfProcurado);
    }

    @Override
    public String toString() {
        return "ProcuracaoUsuario [identificacaoProcurUsuario=" + identificacaoProcurUsuario + ", cpfOrigem="
                + cpfOrigem + ", cnpjOrigem=" + cnpjOrigem + ", cpfProcurado=" + cpfProcurado + "]";
    }

}
