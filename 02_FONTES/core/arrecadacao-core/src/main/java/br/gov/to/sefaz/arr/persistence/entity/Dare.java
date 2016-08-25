package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 10:57:41
 */
@Entity
@Table(name = "TA_DARE", schema = "SEFAZ_ARR")
public class Dare extends AbstractEntity<Long> {

    private static final long serialVersionUID = -1627759411687864699L;

    @Id
    @NotNull
    @Column(name = "ID_NOSSO_NUMERO_DARE")
    private Long idNossoNumeroDare;

    @NotNull
    @Column(name = "ID_INSTITUICAO")
    private Integer idInstituicao;

    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UF_EMISSAO")
    private String ufEmissao;

    @NotNull
    @Column(name = "ID_MUNICIPIO_EMISSAO")
    private Integer idMunicipioEmissao;

    @NotNull
    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @NotNull
    @Column(name = "TIPO_PESSOA")
    private Integer tipoPessoa;

    @NotNull
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOME_RAZAO_SOCIAL")
    private String nomeRazaoSocial;

    @NotNull
    @Column(name = "DATA_EMISSAO")
    private LocalDateTime dataEmissao;

    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "USUARIO_EMISSOR")
    private String usuarioEmissor;

    @NotNull
    @Column(name = "QUANTIDADE_DEBITOS")
    private Integer quantidadeDebitos;

    @NotNull
    @Column(name = "VALOR_TOTAL_DARE")
    private BigDecimal valorTotalDare;

    @NotNull
    @Size(min = 1, max = 46)
    @Column(name = "BARRA_DARE")
    private String barraDare;

    @Column(name = "DATA_PAGO")
    private LocalDateTime dataPago;

    @Transient
    private Collection<DareDetalhe> dareDetalheCollection;

    @Override
    public Long getId() {
        return getIdNossoNumeroDare();
    }

    public Long getIdNossoNumeroDare() {
        return idNossoNumeroDare;
    }

    public void setIdNossoNumeroDare(Long idNossoNumeroDare) {
        this.idNossoNumeroDare = idNossoNumeroDare;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getUfEmissao() {
        return ufEmissao;
    }

    public void setUfEmissao(String ufEmissao) {
        this.ufEmissao = ufEmissao;
    }

    public Integer getIdMunicipioEmissao() {
        return idMunicipioEmissao;
    }

    public void setIdMunicipioEmissao(Integer idMunicipioEmissao) {
        this.idMunicipioEmissao = idMunicipioEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getUsuarioEmissor() {
        return usuarioEmissor;
    }

    public void setUsuarioEmissor(String usuarioEmissor) {
        this.usuarioEmissor = usuarioEmissor;
    }

    public Integer getQuantidadeDebitos() {
        return quantidadeDebitos;
    }

    public void setQuantidadeDebitos(Integer quantidadeDebitos) {
        this.quantidadeDebitos = quantidadeDebitos;
    }

    public BigDecimal getValorTotalDare() {
        return valorTotalDare;
    }

    public void setValorTotalDare(BigDecimal valorTotalDare) {
        this.valorTotalDare = valorTotalDare;
    }

    public String getBarraDare() {
        return barraDare;
    }

    public void setBarraDare(String barraDare) {
        this.barraDare = barraDare;
    }

    public LocalDateTime getDataPago() {
        return dataPago;
    }

    public void setDataPago(LocalDateTime dataPago) {
        this.dataPago = dataPago;
    }

    public Collection<DareDetalhe> getDareDetalheCollection() {
        return dareDetalheCollection;
    }

    public void setDareDetalheCollection(Collection<DareDetalhe> dareDetalheCollection) {
        this.dareDetalheCollection = dareDetalheCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dare dare = (Dare) o;
        return Objects.equals(idNossoNumeroDare, dare.idNossoNumeroDare)
                && Objects.equals(idInstituicao, dare.idInstituicao)
                && Objects.equals(ufEmissao, dare.ufEmissao)
                && Objects.equals(idMunicipioEmissao, dare.idMunicipioEmissao)
                && Objects.equals(dataVencimento, dare.dataVencimento)
                && Objects.equals(tipoPessoa, dare.tipoPessoa)
                && Objects.equals(idPessoa, dare.idPessoa)
                && Objects.equals(nomeRazaoSocial, dare.nomeRazaoSocial)
                && Objects.equals(dataEmissao, dare.dataEmissao)
                && Objects.equals(usuarioEmissor, dare.usuarioEmissor)
                && Objects.equals(quantidadeDebitos, dare.quantidadeDebitos)
                && Objects.equals(valorTotalDare, dare.valorTotalDare)
                && Objects.equals(barraDare, dare.barraDare)
                && Objects.equals(dataPago, dare.dataPago)
                && Objects.equals(dareDetalheCollection, dare.dareDetalheCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNossoNumeroDare, idInstituicao, ufEmissao, idMunicipioEmissao, dataVencimento,
                tipoPessoa, idPessoa, nomeRazaoSocial, dataEmissao, usuarioEmissor, quantidadeDebitos, valorTotalDare,
                barraDare, dataPago, dareDetalheCollection);
    }

    @Override
    public String toString() {
        return "Dare{"
                + "idNossoNumeroDare=" + idNossoNumeroDare
                + ", idInstituicao=" + idInstituicao
                + ", ufEmissao='" + ufEmissao + '\''
                + ", idMunicipioEmissao=" + idMunicipioEmissao
                + ", dataVencimento=" + dataVencimento
                + ", tipoPessoa=" + tipoPessoa
                + ", idPessoa=" + idPessoa
                + ", nomeRazaoSocial='" + nomeRazaoSocial + '\''
                + ", dataEmissao=" + dataEmissao
                + ", usuarioEmissor='" + usuarioEmissor + '\''
                + ", quantidadeDebitos=" + quantidadeDebitos
                + ", valorTotalDare=" + valorTotalDare
                + ", barraDare='" + barraDare + '\''
                + ", dataPago=" + dataPago
                + ", dareDetalheCollection=" + dareDetalheCollection
                + '}';
    }
}
