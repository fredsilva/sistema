package br.gov.to.sefaz.par.gestao.business.service.filter;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 08/06/2016 17:40:00
 */
public class ParametroGeralFilter {

    private String nomeParametroGeral;

    public String getNomeParametroGeral() {
        return nomeParametroGeral;
    }

    public void setNomeParametroGeral(String nomeParametroGeral) {
        this.nomeParametroGeral = nomeParametroGeral;
    }
}
