package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxasPK;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasTaxasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:23:00
 */
@Service
public class ReceitasTaxasServiceImpl extends DefaultCrudService<ReceitasTaxas, ReceitasTaxasPK>
        implements ReceitasTaxasService {

    @Autowired
    public ReceitasTaxasServiceImpl(ReceitasTaxasRepository repository) {
        super(repository);
    }

    @Override
    protected ReceitasTaxasRepository getRepository() {
        return (ReceitasTaxasRepository) super.getRepository();
    }

    @Override
    public void deleteAllTaxasByIdReceita(Integer idReceita) {
        getRepository().deleteAllTaxasByIdReceita(idReceita);
    }

    @Override
    public Collection<ReceitasTaxas> getReceitasTaxasByIdReceita(Integer idReceita) {
        return getRepository()
                .find("rt", sb -> sb
                        .innerJoinFetch("rt.receitas")
                        .where().equal("rt.idReceita", idReceita).orderById());
    }

    @Override
    public Optional<ReceitasTaxas> delete(ReceitasTaxasPK id) {
        Optional<ReceitasTaxas> taxas;

        if (getRepository().existsLockReference(id.getIdSubcodigo(), id.getIdReceita())) {
            getRepository().updateSituacao(id.getIdSubcodigo(), id.getIdReceita(), SituacaoEnum.CANCELADO);
            taxas = Optional.of(getRepository().findOne(id));

        } else {
            super.delete(id);
            taxas = Optional.empty();

        }

        return taxas;
    }
}
