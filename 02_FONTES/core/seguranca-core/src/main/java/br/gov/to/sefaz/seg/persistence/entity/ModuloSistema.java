package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_MODULO_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_MODULO_SISTEMA", schema = "SEFAZ_SEG")
public class ModuloSistema extends AbstractEntity<Long> {

    private static final long serialVersionUID = -1116052742950309311L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_modulo_sistema")
    @SequenceGenerator(name = "sq_modulo_sistema", schema = "SEFAZ_SEG", sequenceName = "sq_modulo_sistema",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_MODULO_SISTEMA")
    private Long identificacaoModuloSistema;

    @NotEmpty(message = "#{seg_msg['seg.gestao.manutencaoCadastroSistema.abreviacaoModulo.obrigatorio']}")
    @Size(max = 3, message = "#{seg_msg['seg.gestao.manutencaoCadastroSistema.abreviacaoModulo.tamanho']}")
    @Column(name = "ABREVIACAO_MODULO")
    private String abreviacaoModulo;

    @NotEmpty(message = "#{seg_msg['seg.gestao.manutencaoCadastroSistema.descricaoModuloSistema.obrigatorio']}")
    @Size(max = 60, message = "#{seg_msg['seg.gestao.manutencaoCadastroSistema.descricaoModuloSistema.tamanho']}")
    @Column(name = "DESCRICAO_MODULO_SISTEMA")
    private String descricaoModuloSistema;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "moduloSistema", fetch = FetchType.LAZY)
    private Set<AplicacaoModulo> aplicacaoModulos;

    public ModuloSistema() {
        // Construtor para inicialização por reflexão.
    }

    public ModuloSistema(Long identificacaoModuloSistema, String abreviacaoModulo, String descricaoModuloSistema) {
        this.identificacaoModuloSistema = identificacaoModuloSistema;
        this.abreviacaoModulo = abreviacaoModulo;
        this.descricaoModuloSistema = descricaoModuloSistema;
    }

    @Override
    public Long getId() {
        return identificacaoModuloSistema;
    }

    public Long getIdentificacaoModuloSistema() {
        return identificacaoModuloSistema;
    }

    public void setIdentificacaoModuloSistema(Long identificacaoModuloSistema) {
        this.identificacaoModuloSistema = identificacaoModuloSistema;
    }

    public String getAbreviacaoModulo() {
        return abreviacaoModulo;
    }

    public void setAbreviacaoModulo(String abreviacaoModulo) {
        this.abreviacaoModulo = abreviacaoModulo;
    }

    public String getDescricaoModuloSistema() {
        return descricaoModuloSistema;
    }

    public void setDescricaoModuloSistema(String descricaoModuloSistema) {
        this.descricaoModuloSistema = descricaoModuloSistema;
    }

    public Set<AplicacaoModulo> getAplicacaoModulos() {
        return aplicacaoModulos;
    }

    public void setAplicacaoModulos(Set<AplicacaoModulo> aplicacaoModulos) {
        this.aplicacaoModulos = aplicacaoModulos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoModuloSistema, abreviacaoModulo, descricaoModuloSistema);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModuloSistema that = (ModuloSistema) obj;
        return Objects.equals(this.identificacaoModuloSistema, that.identificacaoModuloSistema)
                && Objects.equals(this.abreviacaoModulo, that.abreviacaoModulo)
                && Objects.equals(this.descricaoModuloSistema, that.descricaoModuloSistema);
    }

    @Override
    public String toString() {
        return "ModuloSistema [identificacaoModuloSistema=" + identificacaoModuloSistema + ", abreviacaoModulo="
                + abreviacaoModulo + ", descricaoModuloSistema=" + descricaoModuloSistema + "]";
    }

}
