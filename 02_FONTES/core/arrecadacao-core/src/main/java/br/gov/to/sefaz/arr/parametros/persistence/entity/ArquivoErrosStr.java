package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVO_ERROS_STR do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_ERROS_STR", schema = "SEFAZ_ARR")
@IdClass(ArquivoErrosStrPK.class)
public class ArquivoErrosStr extends AbstractEntity<ArquivoErrosStrPK> {

    @Id
    @NotNull(message = "O campo Código do Arquivo STR é obrigatório e deve ser informado!")
    @Max(value = 9999999999L, message = "O campo Código do Arquivo STR deve conter no máximo 10 digitos!")
    @Min(value = 1, message = "O campo Código do Arquivo STR deve ser maior do que 0!")
    @Column(name = "ID_ARQUIVO_STR")
    private Long idArquivoStr;

    @Id
    @NotNull(message = "O campo Código do Tipo de Rejeição é obrigatório e deve ser informado!")
    @Max(value = 99, message = "O campo Código do Tipo de Rejeição deve conter no máximo 2 digitos!")
    @Min(value = 1, message = "O campo Código do Tipo de Rejeição deve ser maior do que 0!")
    @Column(name = "ID_CODIGO_REJEICAO")
    private Integer idCodigoRejeicao;

    @Override
    public ArquivoErrosStrPK getId() {
        return new ArquivoErrosStrPK(idArquivoStr, idCodigoRejeicao);
    }

    public Long getIdArquivoStr() {
        return idArquivoStr;
    }

    public void setIdArquivoStr(Long idArquivoStr) {
        this.idArquivoStr = idArquivoStr;
    }

    public Integer getIdCodigoRejeicao() {
        return idCodigoRejeicao;
    }

    public void setIdCodigoRejeicao(Integer idCodigoRejeicao) {
        this.idCodigoRejeicao = idCodigoRejeicao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArquivoErrosStr that = (ArquivoErrosStr) obj;
        return Objects.equals(idArquivoStr, that.idArquivoStr)
                && Objects.equals(idCodigoRejeicao, that.idCodigoRejeicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArquivoStr, idCodigoRejeicao);
    }

    @Override
    public String toString() {
        return "ArquivoErrosStr{" + "idArquivoStr=" + idArquivoStr + ", idCodigoRejeicao=" + idCodigoRejeicao + '}';
    }
}
