package br.gov.to.sefaz.seg.business.consulta.service.impl;

import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.consulta.service.ComunicacaoContribuinteService;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ComunicacaoContribuinteFilter;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.TipoPeriodoConsultaComunicacaoEnum;
import br.gov.to.sefaz.seg.persistence.repository.ComunicacaoContribuinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe de serviço que implementa os métodos de negócio para a entidade {@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 15:09:00
 */
@Service
public class ComunicacaoContribuinteServiceImpl implements ComunicacaoContribuinteService {

    /**
     * Contexto de validação a ser utilizado na validação de métodos de consulta.
     */
    public static final String FILTER_VALIDATION_CONTEXT = "FILTER_VALIDATION_CONTEXT";

    private final ComunicacaoContribuinteRepository repository;

    @Autowired
    public ComunicacaoContribuinteServiceImpl(ComunicacaoContribuinteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ComunicacaoContribuinte> findByFilter(ComunicacaoContribuinteFilter filter) {
        return repository.find(sb -> sb.where()
                .opt().like("cpfDestinatario", filter.getCpf())
                .and().opt().like("nomeDestinatario", filter.getNomeDestinatario())
                .and().opt().equal("dataComunicacao", filter.getDataComunicacao())
                .and().opt().equal("tipoComunicacao", filter.getTipoComunicacao())
                .orderBy("nomeDestinatario", "tipoComunicacao", "dataHoraComunicacao"));

    }

    @Override
    public List<ComunicacaoContribuinte> findSystemCommunicationsForUser(@ValidationSuite(onlyCustom = true,
            context = FILTER_VALIDATION_CONTEXT) ConsultaComunicacaoSistemaFilter filter, UsuarioSistema
            usuarioSistema) {

        LocalDate dataInicial = calculateStartDate(filter);
        LocalDate dataFinal = calculateEndDate(filter);

        return repository.find(sb -> sb.where()
                .equal("cpfDestinatario", usuarioSistema.getCpfUsuario())
                .and().opt().between("dataComunicacao", dataInicial, dataFinal)
                .and().opt().equal("tipoComunicacao", filter.getTipoComunicacao())
                .orderBy("dataHoraComunicacao"));

    }

    private LocalDate calculateEndDate(ConsultaComunicacaoSistemaFilter filter) {
        if (TipoPeriodoConsultaComunicacaoEnum.PERIODO == filter.getTipoPeriodo()) {
            return filter.getDataFinal();
        } else {
            return LocalDate.now();
        }
    }

    private LocalDate calculateStartDate(ConsultaComunicacaoSistemaFilter filter) {
        if (TipoPeriodoConsultaComunicacaoEnum.PERIODO == filter.getTipoPeriodo()) {
            return filter.getDataInicial();
        } else {
            return LocalDate.now().minusDays(filter.getTipoPeriodo().getDays());
        }
    }
}
