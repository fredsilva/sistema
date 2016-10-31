package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Classe de filtro para a tela de Postos de trabalho.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/06/2016 11:08:00
 */
public class PapelSistemaFilter {

    private String nomePapel;

    public PapelSistemaFilter(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    public PapelSistemaFilter() {
        //Construtor para utilização no Managed Bean.
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }
}
