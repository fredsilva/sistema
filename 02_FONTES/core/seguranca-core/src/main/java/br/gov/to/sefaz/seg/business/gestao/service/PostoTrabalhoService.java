package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link PostoTrabalho}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface PostoTrabalhoService extends CrudService<PostoTrabalho, Long> {

    /**
     * Busca todos os Posto Trabalho de acordo com os parâmetros passados em tela.
     * @param filter filtro passado em tela.
     * @return retorna lista de Posto Trabalho.
     */
    List<PostoTrabalho> findAll(PostoTrabalhoFilter filter);
}
