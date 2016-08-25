package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Classe de filtro para a tela de Postos de trabalho.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/06/2016 11:08:00
 */
public class PerfilSistemaFilter {

    private String nomePerfil;

    public PerfilSistemaFilter() {
        //Construtor vazio para utilização no Managed Bean.
    }

    public PerfilSistemaFilter(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }
}
