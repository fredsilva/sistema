package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela TA_TIPO_GRUPOS_CNAES do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_TIPO_GRUPOS_CNAES", schema = "SEFAZ_ARR")
public class TipoGruposCnaes extends AbstractEntity<Integer> {

    @Id
    @Basic(optional = false)
    @NotNull(message = "O campo Grupo Cnae é obrigatório e deve ser informado!")
    @Max(value = 99, message = "O campo Código deve conter no máximo 2 digitos!")
    @Min(value = 1, message = "O campo Código deve ser maior do que 0!")
    @Column(name = "ID_GRUPO_CNAE", nullable = false)
    private Integer idGrupoCnae;

    @Basic(optional = false)
    @NotEmpty(message = "O campo Descrição do Grupo é obrigatório e não foi informado!")
    @Size(max = 100, message = "O campo Descrição do Grupo deve possuir no máximo 100 caracteres!")
    @Column(name = "DESCRICAO_GRUPO", nullable = false, length = 100)
    private String descricaoGrupo;

    @Basic(optional = false)
    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    public TipoGruposCnaes() {
        // Construtor para inicialização por reflexão.
    }

    public TipoGruposCnaes(Integer idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public TipoGruposCnaes(Integer idGrupoCnae, String descricaoGrupo, SituacaoEnum situacao) {
        this.idGrupoCnae = idGrupoCnae;
        this.descricaoGrupo = descricaoGrupo;
        this.situacao = situacao;
    }

    public Integer getIdGrupoCnae() {
        return idGrupoCnae;
    }

    @Override
    public Integer getId() {
        return getIdGrupoCnae();
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
