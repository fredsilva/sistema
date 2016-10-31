package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoCodigoBarraEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoConvenioEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_CONVENIOS_ARREC do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_CONVENIOS_ARREC", schema = "SEFAZ_ARR")
public class ConveniosArrec extends AbstractEntity<Long> {

    private static final long serialVersionUID = -870771392842370740L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.idConvenio.obrigatorio']}")
    @Max(value = 9999999999L, message = "#{arr_msg['parametros.conveniosArrec.idConvenio.maximo']}")
    @Column(name = "ID_CONVENIO", nullable = false)
    private Long idConvenio;

    @NotEmpty(message = "#{arr_msg['parametros.conveniosArrec.descricaoConvenio.obrigatorio']}")
    @Size(max = 100, message = "#{arr_msg['parametros.conveniosArrec.descricaoConvenio.maximo']}")
    @Column(name = "DESCRICAO_CONVENIO", nullable = false, length = 100)
    private String descricaoConvenio;

    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.tipoBarra.obrigatorio']}")
    @Convert(converter = TipoCodigoBarraEnumConverter.class)
    @Column(name = "TIPO_BARRA", nullable = false)
    private TipoCodigoBarraEnum tipoBarra;

    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.versaoArquivo.obrigatorio']}")
    @Max(value = 99, message = "#{arr_msg['parametros.conveniosArrec.idConvenio.maximo']}")
    @Column(name = "VERSAO_ARQUIVO", nullable = false)
    private Integer versaoArquivo;

    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.tipoConvenio.obrigatorio']}")
    @Convert(converter = TipoConvenioEnumConverter.class)
    @Column(name = "TIPO_CONVENIO", nullable = false)
    private TipoConvenioEnum tipoConvenio;

    @JoinColumns({@JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO",
            insertable = false, updatable = false),
            @JoinColumn(name = "ID_AGENCIA", referencedColumnName = "ID_AGENCIA",
                    insertable = false, updatable = false)})
    @ManyToOne()
    private BancoAgencias bancoAgencias;

    @NotNull(message = "O campo Código do Banco é obrigatório e deve ser informado!")
    @Column(name = "ID_BANCO", nullable = false)
    private Integer idBanco;

    @NotNull(message = "O campo Código da Agência é obrigatório e deve ser informado!")
    @Column(name = "ID_AGENCIA", nullable = false)
    private Integer idAgencia;

    @Transient
    private List<ConveniosTarifas> conveniosTarifas;
    @Transient
    private List<Receitas> receitas;

    public ConveniosArrec() {
        bancoAgencias = new BancoAgencias();
        conveniosTarifas = new ArrayList<>();
        receitas = new ArrayList<>();
    }

    public ConveniosArrec(Long idConvenio, String descricaoConvenio, TipoCodigoBarraEnum tipoBarra,
            SituacaoEnum situacao, Integer versaoArquivo, TipoConvenioEnum tipoConvenio, BancoAgencias bancoAgencias) {
        this.idConvenio = idConvenio;
        this.descricaoConvenio = descricaoConvenio;
        this.tipoBarra = tipoBarra;
        this.situacao = situacao;
        this.versaoArquivo = versaoArquivo;
        this.tipoConvenio = tipoConvenio;
        this.bancoAgencias = bancoAgencias;
    }

    @Override
    public Long getId() {
        return idConvenio;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getDescricaoConvenio() {
        return descricaoConvenio;
    }

    public void setDescricaoConvenio(String descricaoConvenio) {
        this.descricaoConvenio = descricaoConvenio;
    }

    public TipoCodigoBarraEnum getTipoBarra() {
        return tipoBarra;
    }

    public void setTipoBarra(TipoCodigoBarraEnum tipoBarra) {
        this.tipoBarra = tipoBarra;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Integer getVersaoArquivo() {
        return versaoArquivo;
    }

    public void setVersaoArquivo(Integer versaoArquivo) {
        this.versaoArquivo = versaoArquivo;
    }

    public TipoConvenioEnum getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenioEnum tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    public BancoAgencias getBancoAgencias() {
        return bancoAgencias;
    }

    public void setBancoAgencias(BancoAgencias bancoAgencias) {
        this.bancoAgencias = bancoAgencias;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    /**
     * Atribui o id do banco ao {@link #idBanco} e {@link #bancoAgencias}.
     * @param idBanco id do banco
     */
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
        bancoAgencias.setIdBanco(idBanco);
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    /**
     * Atribui o id do agencia ao {@link #idAgencia} e {@link #bancoAgencias}.
     * @param idAgencia id do agencia
     */
    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
        bancoAgencias.setIdAgencia(idAgencia);
    }

    public String getNomeBanco() {
        return bancoAgencias.getBancos().getNomeBanco();
    }

    public String getNomeAgencia() {
        return bancoAgencias.getNomeAgencia();
    }

    public List<ConveniosTarifas> getConveniosTarifas() {
        return conveniosTarifas;
    }

    public void setConveniosTarifas(List<ConveniosTarifas> conveniosTarifas) {
        this.conveniosTarifas = conveniosTarifas;
    }

    public List<Receitas> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receitas> receitas) {
        this.receitas = receitas;
    }

    /**
     * Adiciona uma tarifa ao {@link #conveniosTarifas}.
     * @param tarifa tarifa
     */
    public void addTarifa(ConveniosTarifas tarifa) {
        this.conveniosTarifas.add(tarifa);
    }

    /**
     * Remove uma tarifa do {@link #conveniosTarifas}.
     * @param conveniosTarifas tarifa
     */
    public void removeTarifa(ConveniosTarifas conveniosTarifas) {
        this.conveniosTarifas.remove(conveniosTarifas);
    }

    /**
     * Adiciona uma receita ao {@link #receitas}.
     * @param receitas receitas
     */
    public void addReceita(Receitas receitas) {
        this.receitas.add(receitas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConveniosArrec that = (ConveniosArrec) o;
        return Objects.equals(idConvenio, that.idConvenio)
                && Objects.equals(descricaoConvenio, that.descricaoConvenio)
                && tipoBarra == that.tipoBarra
                && situacao == that.situacao
                && Objects.equals(versaoArquivo, that.versaoArquivo)
                && tipoConvenio == that.tipoConvenio
                && Objects.equals(bancoAgencias, that.bancoAgencias)
                && Objects.equals(idBanco, that.idBanco)
                && Objects.equals(idAgencia, that.idAgencia)
                && Objects.equals(conveniosTarifas, that.conveniosTarifas)
                && Objects.equals(receitas, that.receitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConvenio, descricaoConvenio, tipoBarra, situacao, versaoArquivo, tipoConvenio,
                bancoAgencias, idBanco, idAgencia, conveniosTarifas, receitas);
    }

    @Override
    public String toString() {
        return "ConveniosArrec{"
                + "idConvenio=" + idConvenio
                + ", descricaoConvenio='" + descricaoConvenio + '\''
                + ", tipoBarra=" + tipoBarra
                + ", situacao=" + situacao
                + ", versaoArquivo=" + versaoArquivo
                + ", tipoConvenio=" + tipoConvenio
                + ", bancoAgencias=" + bancoAgencias
                + ", idBanco=" + idBanco
                + ", idAgencia=" + idAgencia
                + ", conveniosTarifas=" + conveniosTarifas
                + ", receitas=" + receitas
                + '}';
    }
}
