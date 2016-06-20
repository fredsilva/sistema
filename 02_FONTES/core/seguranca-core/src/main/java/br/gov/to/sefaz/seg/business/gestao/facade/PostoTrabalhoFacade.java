package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Bancos.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface PostoTrabalhoFacade extends CrudFacade<PostoTrabalho, Long> {

    /**
     * Filtro da tela de PostoTrabalho.
     * @param filter filtro digitado na tela.
     * @return retorna lista que foi encontrada de acordo com os parâmetros passados.
     */
    List<PostoTrabalho> find(PostoTrabalhoFilter filter);

    /**
     * Busca todas as Unidades Organizacionais para uso em combo box.
     * @return lista de Unidades Organizacionais.
     */
    Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional();
}

