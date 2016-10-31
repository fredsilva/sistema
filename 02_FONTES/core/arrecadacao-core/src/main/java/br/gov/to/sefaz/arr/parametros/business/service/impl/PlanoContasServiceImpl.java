package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.repository.PlanoContasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Service
public class PlanoContasServiceImpl extends DefaultCrudService<PlanoContas, Long> implements PlanoContasService {

    @Autowired
    public PlanoContasServiceImpl(
            PlanoContasRepository repository) {
        super(repository);
    }

    @Override
    protected PlanoContasRepository getRepository() {
        return (PlanoContasRepository) super.getRepository();
    }

    @Override
    public PlanoContas save(@ValidationSuite(context = ValidationContext.SAVE) PlanoContas entity) {
        PlanoContas planoContas = super.save(entity);

        return planoContas;
    }

    @Override
    public PlanoContas update(@ValidationSuite(context = ValidationContext.UPDATE) PlanoContas entity) {
        PlanoContas planoContas = super.update(entity);

        return planoContas;
    }

    @Override
    @Transactional
    public Optional<PlanoContas> delete(Long id) {
        Optional<PlanoContas> planoContas;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            planoContas = Optional.of(getRepository().findOne(id));

        } else {
            super.delete(id);
            planoContas = Optional.empty();

        }

        return planoContas;
    }

    @Override
    public List<PlanoContas> find(PlanoContasFilter filter) {
        return getRepository().find("pc", sb -> sb
                .innerJoinFetch("pc.gruposCnaes")
                .where().opt().like("pc.codigoPlanoContas", filter.getCodigoPlanoContas())
                .and().opt().like("pc.nomeConta", filter.getNomeConta())
                .and().opt().like("pc.codigoContabil", filter.getCodigoContabil())
                .and().opt().equal("pc.tipoConta", filter.getTipoConta())
                .orderById());
    }

    @Override
    public Collection<PlanoContas> findAll() {
        return getRepository().find("pc", sb -> sb.innerJoinFetch("pc.gruposCnaes").orderById());
    }
}
