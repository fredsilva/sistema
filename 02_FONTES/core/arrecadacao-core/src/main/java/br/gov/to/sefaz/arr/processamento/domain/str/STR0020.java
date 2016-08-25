package br.gov.to.sefaz.arr.processamento.domain.str;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;
import java.util.Objects;

/**
 * Domínio que representa um arquivo STR0020.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 05/07/2016 17:17:00
 */
@SuppressWarnings("PMD")
@JacksonXmlRootElement(localName = "STR0020")
public class STR0020 {

    @JacksonXmlProperty(localName = "CodMsg")
    private String codMsg;
    @JacksonXmlProperty(localName = "NumCtrlIF")
    private String numCtrlIF;
    @JacksonXmlProperty(localName = "ISPBIFDebtd")
    private String ispbifDebtd;
    @JacksonXmlProperty(localName = "ISPBIFCredtd")
    private String ispbifCredtd;
    @JacksonXmlProperty(localName = "AgCredtd")
    private String agCredtd;
    @JacksonXmlProperty(localName = "CtCredtd")
    private String ctCredtd;
    @JacksonXmlProperty(localName = "CodSEFAZ")
    private String codSefaz;
    @JacksonXmlProperty(localName = "TpReceita")
    private String tpReceita;
    @JacksonXmlProperty(localName = "TpRecolht")
    private String tpRecolht;
    @JacksonXmlProperty(localName = "DtArrec")
    private String dtArrec;
    @JacksonXmlProperty(localName = "VlrLanc")
    private String vlrLanc;
    @JacksonXmlProperty(localName = "NivelPref")
    private String nivelPref;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Grupo_STR0020_VlrInf")
    private List<GrupoValorInformativo> grupoValorInformativo;
    @JacksonXmlProperty(localName = "Hist")
    private String hist;
    @JacksonXmlProperty(localName = "DtAgendt")
    private String dtAgendt;
    @JacksonXmlProperty(localName = "HrAgendt")
    private String hrAgendt;
    @JacksonXmlProperty(localName = "DtMovto")
    private String dtMovto;

    public String getCodMsg() {
        return codMsg;
    }

    public String getNumCtrlIF() {
        return numCtrlIF;
    }

    public String getIspbifDebtd() {
        return ispbifDebtd;
    }

    public String getIspbifCredtd() {
        return ispbifCredtd;
    }

    public String getAgCredtd() {
        return agCredtd;
    }

    public String getCtCredtd() {
        return ctCredtd;
    }

    public String getCodSefaz() {
        return codSefaz;
    }

    public String getTpReceita() {
        return tpReceita;
    }

    public String getTpRecolht() {
        return tpRecolht;
    }

    public String getDtArrec() {
        return dtArrec;
    }

    public String getVlrLanc() {
        return vlrLanc;
    }

    public String getNivelPref() {
        return nivelPref;
    }

    public List<GrupoValorInformativo> getGrupoValorInformativo() {
        return grupoValorInformativo;
    }

    public String getHist() {
        return hist;
    }

    public String getDtAgendt() {
        return dtAgendt;
    }

    public String getHrAgendt() {
        return hrAgendt;
    }

    public String getDtMovto() {
        return dtMovto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        STR0020 str0020 = (STR0020) o;
        return Objects.equals(codMsg, str0020.codMsg)
                && Objects.equals(numCtrlIF, str0020.numCtrlIF)
                && Objects.equals(ispbifDebtd, str0020.ispbifDebtd)
                && Objects.equals(ispbifCredtd, str0020.ispbifCredtd)
                && Objects.equals(agCredtd, str0020.agCredtd)
                && Objects.equals(ctCredtd, str0020.ctCredtd)
                && Objects.equals(codSefaz, str0020.codSefaz)
                && Objects.equals(tpReceita, str0020.tpReceita)
                && Objects.equals(tpRecolht, str0020.tpRecolht)
                && Objects.equals(dtArrec, str0020.dtArrec)
                && Objects.equals(vlrLanc, str0020.vlrLanc)
                && Objects.equals(nivelPref, str0020.nivelPref)
                && Objects.equals(grupoValorInformativo, str0020.grupoValorInformativo)
                && Objects.equals(hist, str0020.hist)
                && Objects.equals(dtAgendt, str0020.dtAgendt)
                && Objects.equals(hrAgendt, str0020.hrAgendt)
                && Objects.equals(dtMovto, str0020.dtMovto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMsg, numCtrlIF, ispbifDebtd, ispbifCredtd, agCredtd, ctCredtd, codSefaz, tpReceita,
                tpRecolht, dtArrec, vlrLanc, nivelPref, grupoValorInformativo, hist, dtAgendt, hrAgendt, dtMovto);
    }

    @Override
    public String toString() {
        return "STR0020{"
                + "codMsg='" + codMsg + '\''
                + ", numCtrlIF='" + numCtrlIF + '\''
                + ", ispbifDebtd='" + ispbifDebtd + '\''
                + ", ispbifCredtd='" + ispbifCredtd + '\''
                + ", agCredtd='" + agCredtd + '\''
                + ", ctCredtd='" + ctCredtd + '\''
                + ", codSefaz='" + codSefaz + '\''
                + ", tpReceita='" + tpReceita + '\''
                + ", tpRecolht='" + tpRecolht + '\''
                + ", dtArrec='" + dtArrec + '\''
                + ", vlrLanc='" + vlrLanc + '\''
                + ", nivelPref='" + nivelPref + '\''
                + ", grupoValorInformativo=" + grupoValorInformativo
                + ", hist='" + hist + '\''
                + ", dtAgendt='" + dtAgendt + '\''
                + ", hrAgendt='" + hrAgendt + '\''
                + ", dtMovto='" + dtMovto + '\''
                + '}';
    }
}
