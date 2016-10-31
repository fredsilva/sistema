package br.gov.to.sefaz.arr.processamento.domain.str;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

/**
 * Domínio que retrata TAG Grupo_STR0020_VlrInf de lista de Grupo_STR0020_VlrInf do arquivo STR0020.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 06/07/2016 09:48:00
 */
public class GrupoValorInformativo {

    @JacksonXmlProperty(localName = "TpVlrInf")
    private String tpVlrInf;
    @JacksonXmlProperty(localName = "VlrInf")
    private String vlrInf;
    private ConveniosArrec conveniosArrec;

    public String getTpVlrInf() {
        return tpVlrInf;
    }

    public String getVlrInf() {
        return vlrInf;
    }

    public ConveniosArrec getConveniosArrec() {
        return conveniosArrec;
    }

    public void setConveniosArrec(ConveniosArrec conveniosArrec) {
        this.conveniosArrec = conveniosArrec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GrupoValorInformativo that = (GrupoValorInformativo) o;
        return Objects.equals(tpVlrInf, that.tpVlrInf)
                && Objects.equals(vlrInf, that.vlrInf)
                && Objects.equals(conveniosArrec, that.conveniosArrec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tpVlrInf, vlrInf, conveniosArrec);
    }

    @Override
    public String toString() {
        return "GrupoValorInformativo{"
                + "tpVlrInf='" + tpVlrInf + '\''
                + ", vlrInf='" + vlrInf + '\''
                + ", conveniosArrec=" + conveniosArrec
                + '}';
    }

}
