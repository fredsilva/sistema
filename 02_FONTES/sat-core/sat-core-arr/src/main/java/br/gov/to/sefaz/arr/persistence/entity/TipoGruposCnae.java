package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnumConverter;
import br.gov.to.sefaz.common.model.SerializableEntity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade dos tipos de grupos cnae.
 *
 * @author roger.gouveia
 */
@Entity
@Table(name = "ta_tipo_grupos_cnaes")
public class TipoGruposCnae implements SerializableEntity<Integer> {
    @Id
    @Column(name = "id_grupo_cnae")
    @NotNull(message = "O campo Grupo Cnae é obrigatório e deve ser informado!")
    @Max(value = 99, message = "O campo Código deve conter no máximo 2 digitos!")
    @Min(value = 1, message = "O campo Código deve ser maior do que 0!")
    private Integer idGrupoCnae;

    @Column(name = "descricao_grupo")
    @NotEmpty(message = "O campo Descrição do Grupo é obrigatório e não foi informado!")
    @Size(max = 100, message = "O campo Descrição do Grupo deve possuir no máximo 100 caracteres!")
    private String descricaoGrupo;

    @Column(name = "situacao")
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Range(min = 1, max = 2, message = "O campo Situação deve ser Ativo ou Cancelado!")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @OneToMany(targetEntity = PlanoContas.class, mappedBy = "idGrupoCnae")
    private List<PlanoContas> planoContasList;

    public TipoGruposCnae() {
        // Construtor para inicialização por reflexão.
    }

    public TipoGruposCnae(Integer idGrupoCnae, String descricaoGrupo, SituacaoEnum situacao) {
        this.idGrupoCnae = idGrupoCnae;
        this.descricaoGrupo = descricaoGrupo;
        this.situacao = situacao;
    }

    @Override
    public Integer getId() {
        return idGrupoCnae;
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

    public List<PlanoContas> getPlanoContasList() {
        return planoContasList;
    }

    public void setPlanoContasList(List<PlanoContas> planoContasList) {
        this.planoContasList = planoContasList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoGruposCnae that = (TipoGruposCnae) obj;
        return idGrupoCnae == that.idGrupoCnae && situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupoCnae, situacao);
    }

    @Override
    public String toString() {
        return "Bancos{" + "idGrupoCnae=" + idGrupoCnae + ", descricaoGrupo='" + descricaoGrupo + "', situacao="
                + situacao + '}';
    }
}
