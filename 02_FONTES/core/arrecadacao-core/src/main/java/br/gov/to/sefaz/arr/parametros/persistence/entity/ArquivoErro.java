package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela TA_ARQUIVO_ERRO do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_ERRO", schema = "SEFAZ_ARR")
public class ArquivoErro extends AbstractEntity<Long> {

    @Id
    @NotNull
    @Column(name = "ID_ERRO_DETALHE_ARQUIVO", nullable = false)
    private Long idErroDetalheArquivo;

    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "VALOR_LINHA", nullable = false, length = 2000)
    private String valorLinha;

    @JoinColumn(name = "ID_DETALHE_ARQUIVO", referencedColumnName = "ID_DETALHE_ARQUIVO", nullable = false)
    @ManyToOne(optional = false)
    private ArquivoDetalhePagos arquivoDetalhePagos;

    @JoinColumn(name = "ID_CODIGO_REJEICAO", referencedColumnName = "ID_CODIGO_REJEICAO", nullable = false)
    @ManyToOne(optional = false)
    private TipoRejeicaoArquivos tipoRejeicaoArquivos;

    public ArquivoErro() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoErro(Long idErroDetalheArquivo) {
        this.idErroDetalheArquivo = idErroDetalheArquivo;
    }

    public ArquivoErro(Long idErroDetalheArquivo, String valorLinha) {
        this.idErroDetalheArquivo = idErroDetalheArquivo;
        this.valorLinha = valorLinha;
    }

    @Override
    public Long getId() {
        return idErroDetalheArquivo;
    }

    public Long getIdErroDetalheArquivo() {
        return idErroDetalheArquivo;
    }

    public void setIdErroDetalheArquivo(Long idErroDetalheArquivo) {
        this.idErroDetalheArquivo = idErroDetalheArquivo;
    }

    public ArquivoDetalhePagos getArquivoDetalhePagos() {
        return arquivoDetalhePagos;
    }

    public void setArquivoDetalhePagos(ArquivoDetalhePagos arquivoDetalhePagos) {
        this.arquivoDetalhePagos = arquivoDetalhePagos;
    }

    public TipoRejeicaoArquivos getTipoRejeicaoArquivos() {
        return tipoRejeicaoArquivos;
    }

    public void setTipoRejeicaoArquivos(TipoRejeicaoArquivos tipoRejeicaoArquivos) {
        this.tipoRejeicaoArquivos = tipoRejeicaoArquivos;
    }

    public String getValorLinha() {
        return valorLinha;
    }

    public void setValorLinha(String valorLinha) {
        this.valorLinha = valorLinha;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArquivoErro that = (ArquivoErro) obj;
        return Objects.equals(idErroDetalheArquivo, that.idErroDetalheArquivo)
                && Objects.equals(valorLinha, that.valorLinha)
                && Objects.equals(arquivoDetalhePagos, that.arquivoDetalhePagos)
                && Objects.equals(tipoRejeicaoArquivos, that.tipoRejeicaoArquivos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idErroDetalheArquivo, valorLinha, arquivoDetalhePagos, tipoRejeicaoArquivos);
    }

    @Override
    public String toString() {
        return "ArquivoErro{" + "idErroDetalheArquivo=" + idErroDetalheArquivo + ", valorLinha='" + valorLinha + '}';
    }
}
