package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoPessoaEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
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
@SuppressWarnings("PMD.TooManyFields")
public class Dare extends AbstractEntity<Long> {

    private static final long serialVersionUID = -1627759411687864699L;

    @Id
    @Column(name = "ID_NOSSO_NUMERO_DARE")
    private Long idNossoNumeroDare;

    @NotNull(message = "#{arr_msg['dare.idInstituicao.obrigatorio']}")
    @Column(name = "ID_INSTITUICAO")
    private Long idInstituicao;

    @NotNull(message = "#{arr_msg['dare.ufEmissao.obrigatorio']}")
    @Size(min = 1, max = 2)
    @Column(name = "UF_EMISSAO")
    private String ufEmissao;

    @NotNull(message = "#{arr_msg['dare.idMunicipioEmissao.obrigatorio']}")
    @Column(name = "ID_MUNICIPIO_EMISSAO")
    private Integer idMunicipioEmissao;

    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @NotNull(message = "#{arr_msg['dare.tipoPessoa.obrigatorio']}")
    @Convert(converter = TipoPessoaEnumConverter.class)
    @Column(name = "TIPO_PESSOA")
    private TipoPessoaEnum tipoPessoa;

    @NotNull(message = "#{arr_msg['dare.idContribuinte.obrigatorio']}")
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @Column(name = "NOME_RAZAO_SOCIAL")
    private String nomeRazaoSocial;

    @Column(name = "DATA_EMISSAO")
    private LocalDateTime dataEmissao;

    @Column(name = "USUARIO_EMISSOR")
    private String usuarioEmissor;

    @NotNull(message = "#{arr_msg['dare.quantidadeDebitos.obrigatorio']}")
    @Column(name = "QUANTIDADE_DEBITOS")
    private Integer quantidadeDebitos;

    @NotNull(message = "#{arr_msg['dare.valorTotalDare.obrigatorio']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['dare.valorTotalDare.greaterThanZero']}")
    @Column(name = "VALOR_TOTAL_DARE")
    private BigDecimal valorTotalDare;

    @Column(name = "BARRA_DARE")
    private String barraDare;

    @Column(name = "DATA_PAGO")
    private LocalDateTime dataPago;

    @JoinColumn(name = "ID_INSTITUICAO", referencedColumnName = "IDENTIFICACAO_UNID_ORGANIZAC",
            insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadeOrganizacional unidadeOrganizacional;

    @JoinColumn(name = "ID_NOSSO_NUMERO_DARE", referencedColumnName = "ID_NOSSO_NUMERO_DARE",
            insertable = false, updatable = false)
    @OneToMany
    private Collection<DareDetalhe> dareDetalheCollection;

    @JoinColumn(name = "ID_MUNICIPIO_EMISSAO", referencedColumnName = "CODIGO_IBGE",
            insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;

    public Dare() {
        //necessário para inicialização por reflexão.
    }

    public Dare(Long idNossoNumeroDare, Long idInstituicao, String ufEmissao, Integer idMunicipioEmissao,
            LocalDate dataVencimento, TipoPessoaEnum tipoPessoa, Long idPessoa, String nomeRazaoSocial,
            LocalDateTime dataEmissao, String usuarioEmissor, Integer quantidadeDebitos, BigDecimal valorTotalDare,
            String barraDare, LocalDateTime dataPago, Collection<DareDetalhe> dareDetalheCollection) {
        this.idNossoNumeroDare = idNossoNumeroDare;
        this.idInstituicao = idInstituicao;
        this.ufEmissao = ufEmissao;
        this.idMunicipioEmissao = idMunicipioEmissao;
        this.dataVencimento = dataVencimento;
        this.tipoPessoa = tipoPessoa;
        this.idPessoa = idPessoa;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.dataEmissao = dataEmissao;
        this.usuarioEmissor = usuarioEmissor;
        this.quantidadeDebitos = quantidadeDebitos;
        this.valorTotalDare = valorTotalDare;
        this.barraDare = barraDare;
        this.dataPago = dataPago;
        this.dareDetalheCollection = dareDetalheCollection;
    }

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

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
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

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
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

    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return unidadeOrganizacional;
    }

    public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNomeInstituicao() {
        return unidadeOrganizacional.getNomeUnidOrganizac();
    }

    public String getNomeMunicipio() {
        return municipio.getNomeMunicipio();
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
