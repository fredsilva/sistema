package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_USUARIO_PRINCIPAL_EMPRESA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_USUARIO_PRINCIPAL_EMPRESA", schema = "SEFAZ_SEG")
public class UsuarioPrincipalEmpresa extends AbstractEntity<Long> {

    private static final long serialVersionUID = 6717758278394618207L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_USUARIO_PRINCIP")
    private Long identificacaoUsuarioPrincip;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CNPJ_EMPRESA")
    private String cnpjEmpresa;

    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    public UsuarioPrincipalEmpresa() {
        // Construtor para inicialização por reflexão.
    }

    public UsuarioPrincipalEmpresa(Long identificacaoUsuarioPrincip, String cnpjEmpresa, String cpfUsuario) {
        this.identificacaoUsuarioPrincip = identificacaoUsuarioPrincip;
        this.cnpjEmpresa = cnpjEmpresa;
        this.cpfUsuario = cpfUsuario;
    }

    @Override
    public Long getId() {
        return identificacaoUsuarioPrincip;
    }

    public Long getIdentificacaoUsuarioPrincip() {
        return identificacaoUsuarioPrincip;
    }

    public void setIdentificacaoUsuarioPrincip(Long identificacaoUsuarioPrincip) {
        this.identificacaoUsuarioPrincip = identificacaoUsuarioPrincip;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoUsuarioPrincip, cnpjEmpresa, cpfUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UsuarioPrincipalEmpresa that = (UsuarioPrincipalEmpresa) obj;
        return Objects.equals(this.identificacaoUsuarioPrincip, that.identificacaoUsuarioPrincip)
                && Objects.equals(this.cnpjEmpresa, that.cnpjEmpresa)
                && Objects.equals(this.cpfUsuario, that.cpfUsuario);
    }

    @Override
    public String toString() {
        return "UsuarioPrincipalEmpresa [identificacaoUsuarioPrincip=" + identificacaoUsuarioPrincip + ", cnpjEmpresa="
                + cnpjEmpresa + ", cpfUsuario=" + cpfUsuario + "]";
    }

}
