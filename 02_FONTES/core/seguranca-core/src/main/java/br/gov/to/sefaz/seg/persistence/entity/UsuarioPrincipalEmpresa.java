package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.business.service.validation.custom.Cnpj;
import br.gov.to.sefaz.business.service.validation.custom.Cpf;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO_PRINCIPAL_EMPRESA")
    @SequenceGenerator(name = "SQ_USUARIO_PRINCIPAL_EMPRESA", schema = "SEFAZ_SEG",
            sequenceName = "SQ_USUARIO_PRINCIPAL_EMPRESA",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_USUARIO_PRINCIP")
    private Long identificacaoUsuarioPrincip;

    @Cnpj(message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cnpj.incorreto']}")
    @NotEmpty(message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cnpj.vazio'}")
    @Size(max = 14, message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cnpj.tamanho']}")
    @Column(name = "CNPJ_EMPRESA")
    private String cnpjEmpresa;

    @Cpf(message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cpf.incorreto']}")
    @NotEmpty(message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cpf.vazio'}")
    @Size(max = 14, message = "#{seg_msg['seg.usuarioPrincipalEmpresa.cpf.tamanho']}")
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @Transient
    private String nomeEmpresa;

    @Transient
    private String nomeUsuario;

    @Transient
    @Cnpj
    private String eCnpj;

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

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getECnpj() {
        return eCnpj;
    }

    public void setECnpj(String eCnpj) {
        this.eCnpj = eCnpj;
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
