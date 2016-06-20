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
    private String telefone;
    private String endereco;
    private String chefeGeral;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getChefeGeral() {
        return chefeGeral;
    }

    public void setChefeGeral(String chefeGeral) {
        this.chefeGeral = chefeGeral;
    }
}
