package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.seg.persistence.converter.TipoComunicacaoEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade referente a view SEFAZ_SEG.VW_COMUNICACAO_CONTRIBUINTE do Banco de Dados.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 10:30:37
 */
@Entity
@Table(name = "VW_COMUNICACAO_CONTRIBUINTE", schema = "SEFAZ_SEG")
public class ComunicacaoContribuinte implements Serializable {

    private static final long serialVersionUID = -2415482443943915238L;

    @Id
    @Column(name = "ID_COMUNICACAO")
    private String identificacaoComunicacao;

    @Column(name = "CPF_DESTINATARIO",
            insertable = false, updatable = false)
    private String cpfDestinatario;


    @Column(name = "NOME_DESTINATARIO",
            insertable = false, updatable = false)
    private String nomeDestinatario;

    @Column(name = "DATA_COMUNICACAO", insertable = false, updatable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataComunicacao;

    @Column(name = "DT_HR_COMUNICACAO", insertable = false, updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataHoraComunicacao;

    @Column(name = "CONTEUDO", insertable = false, updatable = false)
    private String conteudo;

    @Column(name = "ASSUNTO", insertable = false, updatable = false)
    private String assunto;

    @Convert(converter = TipoComunicacaoEnumConverter.class)
    @Column(name = "TIPO_COMUNICACAO", insertable = false, updatable = false)
    private TipoComunicacaoEnum tipoComunicacao;

    @Column(name = "TEM_ERRO", insertable = false, updatable = false)
    private Boolean possuiErro;

    @Column(name = "TEM_ANEXO", insertable = false, updatable = false)
    private Boolean possuiAnexo;

    public String getIdentificacaoComunicacao() {
        return identificacaoComunicacao;
    }

    public void setIdentificacaoComunicacao(String identificacaoComunicacao) {
        this.identificacaoComunicacao = identificacaoComunicacao;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }


    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public TipoComunicacaoEnum getTipoComunicacao() {
        return tipoComunicacao;
    }

    public void setTipoComunicacao(TipoComunicacaoEnum tipoComunicacao) {
        this.tipoComunicacao = tipoComunicacao;
    }

    public String getCpfDestinatario() {
        return cpfDestinatario;
    }

    public void setCpfDestinatario(String cpfDestinatario) {
        this.cpfDestinatario = cpfDestinatario;
    }

    public Boolean getPossuiErro() {
        return possuiErro;
    }

    public void setPossuiErro(Boolean possuiErro) {
        this.possuiErro = possuiErro;
    }

    public Boolean getPossuiAnexo() {
        return possuiAnexo;
    }

    public void setPossuiAnexo(Boolean possuiAnexo) {
        this.possuiAnexo = possuiAnexo;
    }

    public LocalDate getDataComunicacao() {
        return dataComunicacao;
    }

    public void setDataComunicacao(LocalDate dataComunicacao) {
        this.dataComunicacao = dataComunicacao;
    }

    public LocalDateTime getDataHoraComunicacao() {
        return dataHoraComunicacao;
    }

    public void setDataHoraComunicacao(LocalDateTime dataHoraComunicacao) {
        this.dataHoraComunicacao = dataHoraComunicacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComunicacaoContribuinte that = (ComunicacaoContribuinte) o;
        return Objects.equals(getIdentificacaoComunicacao(), that.getIdentificacaoComunicacao())
                && Objects.equals(getCpfDestinatario(), that.getCpfDestinatario())
                && Objects.equals(getNomeDestinatario(), that.getNomeDestinatario())
                && Objects.equals(getDataComunicacao(), that.getDataComunicacao())
                && Objects.equals(getDataHoraComunicacao(), that.getDataHoraComunicacao())
                && Objects.equals(getAssunto(), that.getAssunto())
                && getTipoComunicacao() == that.getTipoComunicacao()
                && Objects.equals(getPossuiErro(), that.getPossuiErro())
                && Objects.equals(getPossuiAnexo(), that.getPossuiAnexo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificacaoComunicacao(), getCpfDestinatario(), getNomeDestinatario(),
                getDataComunicacao(), getDataHoraComunicacao(), getAssunto(), getTipoComunicacao(),
                getPossuiErro(), getPossuiAnexo());
    }
}
