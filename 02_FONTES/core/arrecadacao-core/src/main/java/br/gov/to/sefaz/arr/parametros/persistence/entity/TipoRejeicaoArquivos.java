package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.validator.constraints.NotEmpty;

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
 * Entidade referente a tabela TA_TIPO_REJEICAO_ARQUIVOS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_TIPO_REJEICAO_ARQUIVOS", schema = "SEFAZ_ARR")
public class TipoRejeicaoArquivos extends AbstractEntity<Integer> {

    @Id
    @NotNull(message = "#{arr_msg['parametros.tipoRejeicao.idCodigoRejeicao.obrigatorio']}")
    @Max(value = 99, message = "#{arr_msg['parametros.tipoRejeicao.idCodigoRejeicao.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.tipoRejeicao.idCodigoRejeicao.minimo']}")
    @Column(name = "ID_CODIGO_REJEICAO", nullable = false)
    private Integer idCodigoRejeicao;

    @NotEmpty(message = "#{arr_msg['parametros.tipoRejeicao.motivoRejeicao.obrigatorio']}")
    @Size(max = 200, message = "#{arr_msg['parametros.tipoRejeicao.motivoRejeicao.tamanho']}")
    @Column(name = "MOTIVO_REJEICAO", nullable = false, length = 200)
    private String motivoRejeicao;

    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "#{arr_msg['parametros.tipoRejeicao.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    public TipoRejeicaoArquivos() {
        // Construtor para inicialização por reflexão.
    }

    public TipoRejeicaoArquivos(Integer idCodigoRejeicao) {
        this.idCodigoRejeicao = idCodigoRejeicao;
    }

    public TipoRejeicaoArquivos(Integer idCodigoRejeicao, String motivoRejeicao, SituacaoEnum situacao) {
        this.idCodigoRejeicao = idCodigoRejeicao;
        this.motivoRejeicao = motivoRejeicao;
        this.situacao = situacao;
    }

    public Integer getIdCodigoRejeicao() {
        return idCodigoRejeicao;
    }

    @Override
    public Integer getId() {
        return getIdCodigoRejeicao();
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
        return Objects.equals(idCodigoRejeicao, that.idCodigoRejeicao)
                && Objects.equals(motivoRejeicao, that.motivoRejeicao) && Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCodigoRejeicao, motivoRejeicao, situacao);
    }

    @Override
    public String toString() {
        return "TipoRejeicaoArquivos{" + "idCodigoRejeicao=" + idCodigoRejeicao + ", motivoRejeicao='" + motivoRejeicao
                + '\'' + ", situacao=" + situacao + '}';
    }
}
