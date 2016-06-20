package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Classe de filtro para a tela de Postos de trabalho.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/06/2016 11:08:00
 */
public class PostoTrabalhoFilter {

    private Long identificacaoPostoTrabalho;
    private String nomePostoTrabalho;
    private Long identificacaoUnidOrganizac;

    public Long getIdentificacaoPostoTrabalho() {
        return identificacaoPostoTrabalho;
    }

    public void setIdentificacaoPostoTrabalho(Long identificacaoPostoTrabalho) {
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
    }

    public String getNomePostoTrabalho() {
        return nomePostoTrabalho;
    }

    public void setNomePostoTrabalho(String nomePostoTrabalho) {
        this.nomePostoTrabalho = nomePostoTrabalho;
    }

    public Long getIdentificacaoUnidOrganizac() {
        return identificacaoUnidOrganizac;
    }

    public void setIdentificacaoUnidOrganizac(Long identificacaoUnidOrganizac) {
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
    }
}
