package br.gov.to.sefaz.arr.dare.facade.impl;

import br.gov.to.sefaz.arr.dare.facade.DareFacade;
import br.gov.to.sefaz.arr.dare.service.ContribuitesService;
import br.gov.to.sefaz.arr.dare.service.DareService;
import br.gov.to.sefaz.arr.dare.service.DebitosContaCorrenteService;
import br.gov.to.sefaz.arr.dare.service.NotaAvulsaService;
import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Implementação da fachada {@link br.gov.to.sefaz.arr.dare.facade.DareFacade}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/08/2016 14:26:00
 */
@Component
public class DareFacadeImpl extends DefaultCrudFacade<Dare, Long> implements DareFacade {

    private final ContribuitesService contribuitesService;
    private final ReceitasService receitasService;
    private final ReceitasTaxasService receitasTaxasService;
    private final DebitosContaCorrenteService debitosContaCorrenteService;
    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final NotaAvulsaService notaAvulsaService;

    @Autowired
    public DareFacadeImpl(DareService service, ContribuitesService contribuitesService, ReceitasService receitasService,
            ReceitasTaxasService receitasTaxasService, DebitosContaCorrenteService debitosContaCorrenteService,
            UnidadeOrganizacionalService unidadeOrganizacionalService, NotaAvulsaService notaAvulsaService) {
        super(service);
        this.contribuitesService = contribuitesService;
        this.receitasService = receitasService;
        this.receitasTaxasService = receitasTaxasService;
        this.debitosContaCorrenteService = debitosContaCorrenteService;
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.notaAvulsaService = notaAvulsaService;
    }

    @Override
    public List<UnidadeOrganizacional> getAllInstituicoesWithTipoUnidadeSefazAndOrgaosExternos() {
        return unidadeOrganizacionalService.findAllByTipoUnidade('S', 'E');
    }

    @Override
    protected DareService getService() {
        return (DareService) super.getService();
    }

    @Override
    public Contribuintes searchContribuinteBy(DareContribuinteFilter contribuinteFilter) {
        return contribuitesService.findByFilter(contribuinteFilter);
    }

    @Override
    public Collection<Receitas> searchReceitasWithId(Set<Integer> idReceitas) {
        return receitasService.findWithIds(idReceitas);
    }

    @Override
    public Collection<Receitas> searchReceitasWithOrigemDebitoId(Integer origemDebitoId) {
        return receitasService.findWithOrigemDebitoId(origemDebitoId);
    }

    @Override
    public Collection<ReceitasTaxas> searchReceitasTaxasWithReceitaId(Integer idReceita) {
        return receitasTaxasService.getReceitasTaxasByIdReceita(idReceita);
    }

    @Override
    public List<DebitosContaCorrente> searchDebitosWithFilter(DebitoParcialFilter debitoParcialFilter) {
        return debitosContaCorrenteService.findAllByDebitoParcialFilter(debitoParcialFilter);
    }

    @Override
    public List<DebitosContaCorrente> searchDebitoIpvaByAnoReferencia(DebitoIpvaFilter debitoIpvaFilter) {
        return debitosContaCorrenteService.findAllByIpvaFilter(debitoIpvaFilter);
    }

    @Override
    public Character getTipoUnidadeByUnidadeOrganizacional(Long idUnidade) {
        UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService.findOne(idUnidade);
        return Objects.isNull(unidadeOrganizacional) ? null : unidadeOrganizacional.getCodigoTipoUnidade();
    }

    @Override
    public void sendEmail(DareEmail dareEmail) {
        getService().sendDareEmail(dareEmail);
    }

    @Override
    public byte[] createDareEPdf(Long nossoNumero) {
        return getService().createDareEPdf(nossoNumero);
    }

    @Override
    public List<NotaAvulsa> searchNotasAvulsasByDebitoParcialFilter(DebitoParcialFilter debitoParcialFilter) {
        return notaAvulsaService.searchNotasAvulsasByDebitoParcialFilter(debitoParcialFilter);
    }

    @Override
    public String getDarePath(Long nossoNumero) {
        return getService().getDarePath(nossoNumero);
    }

}
