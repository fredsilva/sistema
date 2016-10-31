package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 08/06/2016 17:40:00
 */
public class UnidadeOrganizacionalFilter {

    private Long identificacaoUnidOrganizac;
    private String nomeUnidOrganizac;
    private Long unidOrganizacPai;
    private Character tipoUnidade;

    public Long getIdentificacaoUnidOrganizac() {
        return identificacaoUnidOrganizac;
    }

    public void setIdentificacaoUnidOrganizac(Long identificacaoUnidOrganizac) {
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
    }

    public String getNomeUnidOrganizac() {
        return nomeUnidOrganizac;
    }

    public void setNomeUnidOrganizac(String nomeUnidOrganizac) {
        this.nomeUnidOrganizac = nomeUnidOrganizac;
    }

    public Long getUnidOrganizacPai() {
        return unidOrganizacPai;
    }

    public void setUnidOrganizacPai(Long unidOrganizacPai) {
        this.unidOrganizacPai = unidOrganizacPai;
    }

    public Character getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(Character tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }
}
