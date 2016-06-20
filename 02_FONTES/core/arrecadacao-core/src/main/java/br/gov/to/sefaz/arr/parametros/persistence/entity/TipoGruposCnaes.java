package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_TIPO_GRUPOS_CNAES dos Tipos de grupos de CNAE's.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_TIPO_GRUPOS_CNAES", schema = "SEFAZ_ARR")
public class TipoGruposCnaes extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -1357304753703199748L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.tipoGruposCnaes.codigo.obrigatorio']}")
    @Max(value = 99, message = "#{arr_msg['parametros.tipoGruposCnaes.codigo.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.tipoGruposCnaes.codigo.minimo']}")
    @Column(name = "ID_GRUPO_CNAE", nullable = false)
    private Integer idGrupoCnae;

    @NotEmpty(message = "#{arr_msg['parametros.tipoGruposCnaes.descricaoGrupo.obrigatorio']}")
    @Size(max = 100, message = "#{arr_msg['parametros.tipoGruposCnaes.descricaoGrupo.maximo']}")
    @Column(name = "DESCRICAO_GRUPO", nullable = false, length = 100)
    private String descricaoGrupo;

    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "#{arr_msg['parametros.tipoGruposCnaes.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @OneToMany
    @JoinColumn(name = "ID_GRUPO_CNAE", referencedColumnName = "ID_GRUPO_CNAE", insertable = false, updatable = false)
    private Collection<GruposCnae> gruposCnae;

    public TipoGruposCnaes() {
        // Construtor para inicialização por reflexão.
        gruposCnae = new ArrayList<>();
    }

    public TipoGruposCnaes(
            Integer idGrupoCnae, String descricaoGrupo, SituacaoEnum situacao,
            Collection<GruposCnae> gruposCnae) {
        this.idGrupoCnae = idGrupoCnae;
        this.descricaoGrupo = descricaoGrupo;
        this.situacao = situacao;
        this.gruposCnae = gruposCnae;
    }

    @Override
    public Integer getId() {
        return getIdGrupoCnae();
    }

    public Integer getIdGrupoCnae() {
        return idGrupoCnae;
    }

    public void setIdGrupoCnae(Integer idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Collection<GruposCnae> getGruposCnae() {
        return gruposCnae;
    }

    public void setGruposCnae(Collection<GruposCnae> gruposCnae) {
        this.gruposCnae = gruposCnae;
    }

    public String getCompositeName() {
        return getId() + " - " + getDescricaoGrupo();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoGruposCnaes that = (TipoGruposCnaes) obj;
        return Objects.equals(idGrupoCnae, that.idGrupoCnae) && Objects.equals(descricaoGrupo, that.descricaoGrupo)
                && situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupoCnae, descricaoGrupo, situacao);
    }

    @Override
    public String toString() {
        return "TipoGruposCnaes{" + "idGrupoCnae=" + idGrupoCnae + ", descricaoGrupo='" + descricaoGrupo + '\''
                + ", situacao=" + situacao + '}';
    }
}
