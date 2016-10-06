package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoValorInformativoEnumConverter;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoValorInformativoEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_DETALHES_STR do Banco de Dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 17:18:00
 */
@Entity
@Table(name = "TA_DETALHE_STR", schema = "SEFAZ_ARR")
public class DetalheStr extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DETALHE_STR")
    @SequenceGenerator(name = "SQ_DETALHE_STR", schema = "SEFAZ_ARR", sequenceName = "SQ_DETALHE_STR",
            allocationSize = 1)
    @Column(name = "ID_ARQUIVO_DETALHE_STR")
    private Long idArquivoDetalheStr;

    @NotNull
    @Column(name = "ID_CONVENIO")
    private Long idConvenio;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "ID_ARQUIVO_STR", referencedColumnName = "ID_ARQUIVO_STR", nullable = false)
    private ArquivosStr arquivosStr;

    @NotNull
    @Convert(converter = TipoValorInformativoEnumConverter.class)
    @Column(name = "TIPO_VALOR")
    private TipoValorInformativoEnum tipoValor;

    @NotNull
    @Column(name = "VALOR_INFORMATIVO", precision = 14, scale = 2)
    private BigDecimal valorInformativo;

    public Long getIdArquivoDetalheStr() {
        return idArquivoDetalheStr;
    }

    public void setIdArquivoDetalheStr(Long idArquivoDetalheStr) {
        this.idArquivoDetalheStr = idArquivoDetalheStr;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public ArquivosStr getArquivosStr() {
        return arquivosStr;
    }

    public void setArquivosStr(ArquivosStr arquivosStr) {
        this.arquivosStr = arquivosStr;
    }

    public TipoValorInformativoEnum getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(TipoValorInformativoEnum tipoValor) {
        this.tipoValor = tipoValor;
    }

    public BigDecimal getValorInformativo() {
        return valorInformativo;
    }

    public void setValorInformativo(BigDecimal valorInformativo) {
        this.valorInformativo = valorInformativo;
    }

    @Override
    public Long getId() {
        return getIdArquivoDetalheStr();
    }
}
