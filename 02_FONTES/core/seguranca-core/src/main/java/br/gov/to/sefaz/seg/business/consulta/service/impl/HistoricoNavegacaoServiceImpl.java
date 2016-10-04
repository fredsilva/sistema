package br.gov.to.sefaz.seg.business.consulta.service.impl;

import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.consulta.service.HistoricoNavegacaoService;
import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;
import br.gov.to.sefaz.seg.persistence.repository.HistoricoNavegacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 02/09/2016 10:08:00
 */
@Service
public class HistoricoNavegacaoServiceImpl implements HistoricoNavegacaoService {

    public static final String FILTER_VALIDATION_CONTEXT = "FILTER_VALIDATION_CONTEXT";

    @Autowired
    private HistoricoNavegacaoRepository repository;

    @Override
    public List<HistoricoNavegacao> findNavigationHistory(@ValidationSuite(context = FILTER_VALIDATION_CONTEXT)
                                                                      HistoricoNavegacaoFilter filter) {

        return repository.find("hn", sb -> sb.innerJoinFetch("hn.usuarioSistema", "us")
                .leftJoinFetch("us.usuarioPostoTrabalho", "upt")
                .leftJoinFetch("upt.postoTrabalho", "pt")
                .leftJoinFetch("pt.unidadeOrganizacional","uo")
                .where()
                .opt().equal("hn.cpfUsuario", filter.getCpfUsuario())
                .and().opt().like("hn.usuarioSistema.nomeCompletoUsuario", filter.getNomeUsuario())
                .and().opt().equal("hn.tipoOperacao", filter.getTipoOperacao())
                .and().opt().between("hn.dataOperacao", filter.getDataInicialAjustada(), filter.getDataFinalAjustada())
                .and().opt().like("hn.urlAcesso", filter.getUrl())
                .and().opt().equal("hn.cpfCnpjProcurado", filter.getCpfCnpjProcurado())
                .and().opt().like("hn.nomeProcurado", filter.getNomeProcurado())
                .orderBy("hn.dataOperacao", Order.DESC));
    }
}
