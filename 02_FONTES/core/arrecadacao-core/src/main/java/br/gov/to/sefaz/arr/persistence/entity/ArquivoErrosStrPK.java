package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe para mapeamento de pk composta de {@link ArquivoErrosStr}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
public class ArquivoErrosStrPK implements Serializable {

    private static final long serialVersionUID = 1424080568395187331L;

    private Long idArquivoStr;
    private Integer idCodigoRejeicao;

    public ArquivoErrosStrPK() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoErrosStrPK(Long idArquivoStr, Integer idCodigoRejeicao) {
        this.idArquivoStr = idArquivoStr;
        this.idCodigoRejeicao = idCodigoRejeicao;
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
        ArquivoErrosStrPK that = (ArquivoErrosStrPK) obj;
        return Objects.equals(idArquivoStr, that.idArquivoStr)
                && Objects.equals(idCodigoRejeicao, that.idCodigoRejeicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArquivoStr, idCodigoRejeicao);
    }

    @Override
    public String toString() {
        return "ArquivoErrosStrPK{" + "idArquivoStr=" + idArquivoStr + ", idCodigoRejeicao=" + idCodigoRejeicao + '}';
    }
}
