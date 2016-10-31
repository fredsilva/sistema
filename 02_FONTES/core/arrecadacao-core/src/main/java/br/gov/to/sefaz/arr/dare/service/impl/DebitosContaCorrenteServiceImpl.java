package br.gov.to.sefaz.arr.dare.service.impl;

import br.gov.to.sefaz.arr.dare.enums.SituacaoContaCorrenteEnum;
import br.gov.to.sefaz.arr.dare.service.DebitosContaCorrenteService;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.repository.DebitosContaCorrenteRepository;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço que implementa {@link br.gov.to.sefaz.arr.dare.service.DebitosContaCorrenteService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:42:00
 */
@Service
public class DebitosContaCorrenteServiceImpl implements DebitosContaCorrenteService {

    public static final String DEBITO_IPVA_FILTER_CONTEXT = "DEBITO_IPVA";
    private static final String MES_JANEIRO = "01";
    private static final String MES_DEZEMBRO = "12";
    private static final SituacaoContaCorrenteEnum SITUACAO_DEBITO = SituacaoContaCorrenteEnum.DEBITO;

    private final DebitosContaCorrenteRepository repository;

    @Autowired
    public DebitosContaCorrenteServiceImpl(DebitosContaCorrenteRepository repository) {
        this.repository = repository;
    }

    public DebitosContaCorrenteRepository getRepository() {
        return repository;
    }


    @Override
    public List<DebitosContaCorrente> findAllByDebitoParcialFilter(@ValidationSuite DebitoParcialFilter
            debitoParcialFilter) {
        return getRepository().find(select -> select
                .where()
                .equal("documento", debitoParcialFilter.getIdDocumento())
                .and()
                .equal("idPessoa", debitoParcialFilter.getIdContribuinte())
                .and()
                .equal("situacao", SITUACAO_DEBITO));
    }

    @Override
    public List<DebitosContaCorrente> findAllByIpvaFilter(@ValidationSuite(context = DEBITO_IPVA_FILTER_CONTEXT)
            DebitoIpvaFilter debitoIpvaFilter) {
        int periodoInicial = Integer.valueOf(debitoIpvaFilter.getAnoInicial() + MES_JANEIRO);
        int periodoFinal = Integer.valueOf(debitoIpvaFilter.getAnoFinal() + MES_DEZEMBRO);

        return getRepository().find(select -> select
                .where()
                .equal("tipoConta", OrigemDebitoEnum.IPVA)
                .and()
                .equal("situacao", SITUACAO_DEBITO)
                .and()
                .equal("idPessoa", debitoIpvaFilter.getIdentificacaoPessoa())
                .and()
                .between("periodoReferencia", periodoInicial, periodoFinal));
    }
}
