package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.repository.ConveniosArrecRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:17:29
 */
@Service
@Transactional
public class ConveniosArrecServiceImpl extends DefaultCrudService<ConveniosArrec, Long>
        implements ConveniosArrecService {

    private final ConveniosTarifasService conveniosTarifasService;
    private final ConveniosReceitasService receitasService;

    @Autowired
    public ConveniosArrecServiceImpl(
            ConveniosArrecRepository repository,
            ConveniosTarifasService conveniosTarifasService, ConveniosReceitasService receitasService) {
        super(repository);
        this.conveniosTarifasService = conveniosTarifasService;
        this.receitasService = receitasService;
    }

    @Override
    public ConveniosArrecRepository getRepository() {
        return (ConveniosArrecRepository) super.getRepository();
    }

    @Override
    @Transactional
    public ConveniosArrec save(@ValidationSuite(context = ValidationContext.SAVE) ConveniosArrec entity) {
        final ConveniosArrec save = super.save(entity);

        saveAllConveniosTarifas(entity);
        saveAllConveniosReceitas(entity);

        return save;
    }

    @Override
    @Transactional
    public ConveniosArrec update(@ValidationSuite(context = ValidationContext.UPDATE) ConveniosArrec entity) {
        final ConveniosArrec update = super.update(entity);

        saveAllConveniosTarifas(entity);
        saveAllConveniosReceitas(entity);

        return update;
    }

    @Override
    public Optional<ConveniosArrec> delete(Long id) {
        Optional<ConveniosArrec> conveniosArrec;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            conveniosArrec = Optional.of(getRepository().findOne(id));

        } else {
            conveniosTarifasService.deleteAllByIdConvenio(id);
            receitasService.deleteAllByIdConvenio(id);
            super.delete(id);
            conveniosArrec = Optional.empty();

        }

        return conveniosArrec;
    }

    @Override
    public List<ConveniosArrec> find(ConveniosArrecFilter filter) {
        return getRepository().find(sb -> sb.where()
                .opt().equal("idConvenio", filter.getIdConvenio())
                .and().opt().like("descricaoConvenio", filter.getDescricaoConvenio())
                .and().opt().equal("idBanco", filter.getIdBanco())
                .and().opt().equal("tipoConvenio", filter.getTipoConvenio())
                .and().opt().equal("tipoBarra", filter.getTipoBarra()));
    }

    @Override
    public ConveniosArrec findByBancoAgencias(BancoAgencias bancoAgencias) {
        return getRepository().findOne(select -> select
                .where()
                .equal("idAgencia", bancoAgencias.getIdAgencia()));
    }

    @Override
    public List<ConveniosArrec> findByBancoCnpjAndTipoConvenio(Integer cnpjRaiz, TipoConvenioEnum tipoConvenio) {
        return getRepository().find("ca", sb -> sb
                .innerJoinFetch("ca.bancoAgencias", "ba")
                .innerJoinFetch("ba.bancos", "b")
                .where()
                .equal("b.cnpjRaiz", cnpjRaiz)
                .and().equal("ca.tipoConvenio", tipoConvenio));
    }

    @Override
    public ConveniosArrec findByBancoAndTipoConvenio(Integer bancoId, TipoConvenioEnum tipoConvenioEnum) {
        return getRepository().findOne(sb -> sb
                .where()
                .equal("idBanco", bancoId)
                .and().equal("tipoConvenio", tipoConvenioEnum));
    }

    private void saveAllConveniosTarifas(ConveniosArrec entity) {
        entity.getConveniosTarifas()
                .forEach(conveniosTarifas -> conveniosTarifas.setIdConveniosArrec(entity.getIdConvenio()));

        conveniosTarifasService.deleteAllByIdConvenio(entity.getIdConvenio());
        conveniosTarifasService.save(entity.getConveniosTarifas());
    }

    private void saveAllConveniosReceitas(ConveniosArrec entity) {
        Collection<ConveniosReceitas> conveniosReceitas = new ArrayList<>();
        entity.getReceitas().forEach(receitas -> conveniosReceitas
                .add(new ConveniosReceitas(receitas.getIdReceita(), entity.getIdConvenio())));

        receitasService.deleteAllByIdConvenio(entity.getIdConvenio());
        receitasService.save(conveniosReceitas);
    }
}
