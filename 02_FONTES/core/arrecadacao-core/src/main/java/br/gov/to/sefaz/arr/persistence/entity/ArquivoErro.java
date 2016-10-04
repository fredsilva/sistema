package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVO_ERRO do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_ERRO", schema = "SEFAZ_ARR")
public class ArquivoErro extends AbstractEntity<Long> {

    private static final long serialVersionUID = -7803383271860941274L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARQUIVO_ERRO")
    @SequenceGenerator(name = "SQ_ARQUIVO_ERRO", schema = "SEFAZ_ARR",
            sequenceName = "SQ_ARQUIVO_ERRO", allocationSize = 1)
    @Column(name = "ID_ERRO_DETALHE_ARQUIVO")
    private Long idErroDetalheArquivo;

    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "VALOR_LINHA", nullable = false, length = 2000)
    private String valorLinha;

    @Column(name = "ID_DETALHE_ARQUIVO")
    private Long idDetalheArquivo;

    @Column(name = "ID_CODIGO_REJEICAO")
    private Integer idCodigoRejeicao;

    @JoinColumn(name = "ID_DETALHE_ARQUIVO", referencedColumnName = "ID_DETALHE_ARQUIVO", insertable = false,
            updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ArquivoDetalhePagos arquivoDetalhePagos;

    @JoinColumn(name = "ID_CODIGO_REJEICAO", referencedColumnName = "ID_CODIGO_REJEICAO", insertable = false,
            updatable = false, nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRejeicaoArquivos tipoRejeicaoArquivos;

    public ArquivoErro() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoErro(String valorLinha, Integer idCodigoRejeicao, Long idDetalheArquivo) {
        this.valorLinha = valorLinha;
        this.idCodigoRejeicao = idCodigoRejeicao;
        this.idDetalheArquivo = idDetalheArquivo;
    }

    @Override
    public Long getId() {
        return idErroDetalheArquivo;
    }

    public Long getIdErroDetalheArquivo() {
        return idErroDetalheArquivo;
    }

    public String getValorLinha() {
        return valorLinha;
    }

    public Integer getIdCodigoRejeicao() {
        return idCodigoRejeicao;
    }

    public ArquivoDetalhePagos getArquivoDetalhePagos() {
        return arquivoDetalhePagos;
    }

    public TipoRejeicaoArquivos getTipoRejeicaoArquivos() {
        return tipoRejeicaoArquivos;
    }

    public Long getIdDetalheArquivo() {
        return idDetalheArquivo;
    }

    public void setIdDetalheArquivo(Long idDetalheArquivo) {
        this.idDetalheArquivo = idDetalheArquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArquivoErro that = (ArquivoErro) o;
        return Objects.equals(idErroDetalheArquivo, that.idErroDetalheArquivo)
                && Objects.equals(valorLinha, that.valorLinha)
                && Objects.equals(idDetalheArquivo, that.idDetalheArquivo)
                && Objects.equals(idCodigoRejeicao, that.idCodigoRejeicao)
                && Objects.equals(arquivoDetalhePagos, that.arquivoDetalhePagos)
                && Objects.equals(tipoRejeicaoArquivos, that.tipoRejeicaoArquivos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idErroDetalheArquivo, valorLinha, idDetalheArquivo, idCodigoRejeicao, arquivoDetalhePagos,
                tipoRejeicaoArquivos);
    }

    @Override
    public String toString() {
        return "ArquivoErro{"
                + "idErroDetalheArquivo=" + idErroDetalheArquivo
                + ", valorLinha='" + valorLinha + '\''
                + ", idDetalheArquivo=" + idDetalheArquivo
                + ", idCodigoRejeicao=" + idCodigoRejeicao
                + ", arquivoDetalhePagos=" + arquivoDetalhePagos
                + ", tipoRejeicaoArquivos=" + tipoRejeicaoArquivos
                + '}';
    }
}
