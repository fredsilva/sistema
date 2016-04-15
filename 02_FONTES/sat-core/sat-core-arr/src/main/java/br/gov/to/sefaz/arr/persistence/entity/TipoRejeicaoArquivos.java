package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnumConverter;
import br.gov.to.sefaz.common.model.SerializableEntity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

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
 * Entidade de persistência da tabela ta_tipo_rejeicao_arquivos.
 *
 * @author gabriel.dias
 */
@Entity
@Table(name = "ta_tipo_rejeicao_arquivos")
public class TipoRejeicaoArquivos implements SerializableEntity<Integer> {

    @Id
    @Column(name = "id_codigo_rejeicao")
    @NotNull(message = "O campo Código é obrigatório e deve ser informado!")
    @Max(value = 99, message = "O campo Código deve conter no máximo 2 digitos!")
    @Min(value = 1, message = "O campo Código deve ser maior do que 0!")
    private Integer idCodigoRejeicao;

    @Column(name = "motivo_rejeicao")
    @NotEmpty(message = "O campo Motivo da Rejeição é obrigatório e não foi informado!")
    @Size(max = 200, message = "O campo Motivo da Rejeição deve possuir no máximo 200 caracteres!")
    private String motivoRejeicao;

    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Range(min = 1, max = 2, message = "O campo Situação deve ser Ativo(1) ou Cancelado(2)!")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    public TipoRejeicaoArquivos() {
        // Construtor para inicialização por reflexão.
    }

    @Override
    public Integer getId() {
        return this.idCodigoRejeicao;
    }

    public TipoRejeicaoArquivos(int idCodigoRejeicao, String motivoRejeicao, SituacaoEnum situacao) {
        this.idCodigoRejeicao = idCodigoRejeicao;
        this.motivoRejeicao = motivoRejeicao;
        this.situacao = situacao;
    }

    public Integer getIdCodigoRejeicao() {
        return idCodigoRejeicao;
    }

    public void setIdCodigoRejeicao(Integer idCodigoRejeicao) {
        this.idCodigoRejeicao = idCodigoRejeicao;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
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
        TipoRejeicaoArquivos that = (TipoRejeicaoArquivos) obj;
        return idCodigoRejeicao == that.idCodigoRejeicao && situacao == that.situacao
                && Objects.equals(motivoRejeicao, that.motivoRejeicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCodigoRejeicao, motivoRejeicao, situacao);
    }

    @Override
    public String toString() {
        return "TipoRejeicaoArquivosEntity{" + "idCodigoRejeicao=" + idCodigoRejeicao + ", motivoRejeicao='"
                + motivoRejeicao + '\'' + ", situacao=" + situacao + '}';
    }

}
