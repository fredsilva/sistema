package br.gov.to.sefaz.arr.persistence.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * View referente a SEFAZ_ARR.VW_NOTA_AVULSA da base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 14:49:41
 */
@Entity
@Table(name = "VW_NOTA_AVULSA", schema = "SEFAZ_ARR")
public class NotaAvulsa implements Serializable {

    private static final long serialVersionUID = -1804897801793531963L;

    @Id
    @Column(name = "ID_NFA")
    private Long idNfa;

    @Column(name = "ID_EMITENTE")
    private Long idEmitente;

    @Column(name = "ID_INSCRICAO_EMITENTE")
    private Integer idInscricaoEmitente;

    @Column(name = "DESCRICAO_EMITENTE")
    private String descricaoEmitente;

    @Column(name = "ID_DESTINATARIO")
    private Long idDestinatario;

    @Column(name = "ID_INSCRICAO_DESTINATARIO")
    private Integer idInscricaoDestinatario;

    @Column(name = "DESCRICAO_DESTINATARIO")
    private String descricaoDestinatario;

    @Column(name = "VALOR_DEVIDO")
    private BigDecimal valorDevido;

    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Column(name = "ID_RECEITA_TAXA")
    private Integer idReceitaTaxa;

    @Column(name = "ID_RECEITA_SUBCODIGO")
    private Integer idReceitaSubcodigo;

    @Column(name = "VALOR_TSE")
    private BigDecimal valorTse;

    public Long getIdNfa() {
        return idNfa;
    }

    public void setIdNfa(Long idNfa) {
        this.idNfa = idNfa;
    }

    public Long getIdEmitente() {
        return idEmitente;
    }

    public void setIdEmitente(Long idEmitente) {
        this.idEmitente = idEmitente;
    }

    public Integer getIdInscricaoEmitente() {
        return idInscricaoEmitente;
    }

    public void setIdInscricaoEmitente(Integer idInscricaoEmitente) {
        this.idInscricaoEmitente = idInscricaoEmitente;
    }

    public String getDescricaoEmitente() {
        return descricaoEmitente;
    }

    public void setDescricaoEmitente(String descricaoEmitente) {
        this.descricaoEmitente = descricaoEmitente;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Integer getIdInscricaoDestinatario() {
        return idInscricaoDestinatario;
    }

    public void setIdInscricaoDestinatario(Integer idInscricaoDestinatario) {
        this.idInscricaoDestinatario = idInscricaoDestinatario;
    }

    public String getDescricaoDestinatario() {
        return descricaoDestinatario;
    }

    public void setDescricaoDestinatario(String descricaoDestinatario) {
        this.descricaoDestinatario = descricaoDestinatario;
    }

    public BigDecimal getValorDevido() {
        return valorDevido;
    }

    public void setValorDevido(BigDecimal valorDevido) {
        this.valorDevido = valorDevido;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdReceitaTaxa() {
        return idReceitaTaxa;
    }

    public void setIdReceitaTaxa(Integer idReceitaTaxa) {
        this.idReceitaTaxa = idReceitaTaxa;
    }

    public Integer getIdReceitaSubcodigo() {
        return idReceitaSubcodigo;
    }

    public void setIdReceitaSubcodigo(Integer idReceitaSubcodigo) {
        this.idReceitaSubcodigo = idReceitaSubcodigo;
    }

    public BigDecimal getValorTse() {
        return valorTse;
    }

    public void setValorTse(BigDecimal valorTse) {
        this.valorTse = valorTse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotaAvulsa that = (NotaAvulsa) o;
        return Objects.equals(idNfa, that.idNfa)
                && Objects.equals(idEmitente, that.idEmitente)
                && Objects.equals(idInscricaoEmitente, that.idInscricaoEmitente)
                && Objects.equals(descricaoEmitente, that.descricaoEmitente)
                && Objects.equals(idDestinatario, that.idDestinatario)
                && Objects.equals(idInscricaoDestinatario, that.idInscricaoDestinatario)
                && Objects.equals(descricaoDestinatario, that.descricaoDestinatario)
                && Objects.equals(valorDevido, that.valorDevido)
                && Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idReceitaTaxa, that.idReceitaTaxa)
                && Objects.equals(idReceitaSubcodigo, that.idReceitaSubcodigo)
                && Objects.equals(valorTse, that.valorTse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNfa, idEmitente, idInscricaoEmitente, descricaoEmitente, idDestinatario,
                idInscricaoDestinatario, descricaoDestinatario, valorDevido, idReceita, idReceitaTaxa,
                idReceitaSubcodigo, valorTse);
    }

    @Override
    public String toString() {
        return "NotaAvulsa{"
                + "idNfa=" + idNfa
                + ", idEmitente=" + idEmitente
                + ", idInscricaoEmitente=" + idInscricaoEmitente
                + ", descricaoEmitente='" + descricaoEmitente + '\''
                + ", idDestinatario=" + idDestinatario
                + ", idInscricaoDestinatario=" + idInscricaoDestinatario
                + ", descricaoDestinatario='" + descricaoDestinatario + '\''
                + ", valorDevido=" + valorDevido
                + ", idReceita=" + idReceita
                + ", idReceitaTaxa=" + idReceitaTaxa
                + ", idReceitaSubcodigo=" + idReceitaSubcodigo
                + ", valorTse=" + valorTse
                + '}';
    }
}
