package br.gov.to.sefaz.arr.processamento.domain.header;

import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO que representa as informações obtidas no HEADER do arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 16:14:00
 */
public class FileHeader {

    private Long codigoConvenio;
    private Long sequencialArquivoNsa;
    private Integer banco;
    private Integer agenciaBanco;
    private LocalDate dataGeracaoArquivo;
    private TipoArquivoEnum tipoArquivo;

    public Long getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(Long codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public Long getSequencialArquivoNsa() {
        return sequencialArquivoNsa;
    }

    public void setSequencialArquivoNsa(Long sequencialArquivoNsa) {
        this.sequencialArquivoNsa = sequencialArquivoNsa;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Integer getAgenciaBanco() {
        return agenciaBanco;
    }

    public void setAgenciaBanco(Integer agenciaBanco) {
        this.agenciaBanco = agenciaBanco;
    }

    public LocalDate getDataGeracaoArquivo() {
        return dataGeracaoArquivo;
    }

    public void setDataGeracaoArquivo(LocalDate dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    public TipoArquivoEnum getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivoEnum tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileHeader that = (FileHeader) o;
        return Objects.equals(codigoConvenio, that.codigoConvenio)
                && Objects.equals(sequencialArquivoNsa, that.sequencialArquivoNsa)
                && Objects.equals(banco, that.banco)
                && Objects.equals(agenciaBanco, that.agenciaBanco)
                && Objects.equals(dataGeracaoArquivo, that.dataGeracaoArquivo)
                && Objects.equals(tipoArquivo, that.tipoArquivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoConvenio, sequencialArquivoNsa, banco, agenciaBanco, dataGeracaoArquivo, tipoArquivo);
    }

    @Override
    public String toString() {
        return "FileHeader{"
                + "codigoConvenio='" + codigoConvenio + '\''
                + ", sequencialArquivoNsa='" + sequencialArquivoNsa + '\''
                + ", banco='" + banco + '\''
                + ", agenciaBanco='" + agenciaBanco + '\''
                + ", dataGeracaoArquivo=" + dataGeracaoArquivo
                + ", tipoArquivo=" + tipoArquivo
                + '}';
    }
}
