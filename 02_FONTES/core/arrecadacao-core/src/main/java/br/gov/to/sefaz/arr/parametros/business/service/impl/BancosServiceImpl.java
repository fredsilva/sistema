package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.repository.BancoAgenciasRepository;
import br.gov.to.sefaz.arr.persistence.repository.BancosRepository;
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
 * Implementação do serviço da entidade Bancos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Service
public class BancosServiceImpl extends DefaultCrudService<Bancos, Integer> implements BancosService {

    private final BancoAgenciasRepository agenciasRepository;

    @Autowired
    public BancosServiceImpl(BancosRepository repository, BancoAgenciasRepository agenciasRepository) {
        super(repository);
        this.agenciasRepository = agenciasRepository;
    }

    @Override
    protected BancosRepository getRepository() {
        return (BancosRepository) super.getRepository();
    }

    @Override
    public Bancos save(@ValidationSuite(context = ValidationContext.SAVE) Bancos entity) {
        return super.save(entity);
    }

    @Override
    public Bancos update(@ValidationSuite Bancos entity) {
        return super.update(entity);
    }

    @Override
    @Transactional
    public Optional<Bancos> delete(Integer id) {
        Optional<Bancos> banco;

        if (getRepository().existsLockReference(id)) {
            agenciasRepository.updateSituacaoByBanco(id, SituacaoEnum.CANCELADO);
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            banco = Optional.of(getRepository().findOne(id));
        } else {
            super.delete(id);
            banco = Optional.empty();
        }

        return banco;
    }

    @Override
    public Collection<Bancos> findAllActiveBancos() {
        return getRepository().find(sb -> sb.where().equal("situacao", SituacaoEnum.ATIVO).orderById());
    }

    @Override
    public List<Bancos> findByCpjRaiz(Integer cnpjAgenteBancarioDebidato) {
        return getRepository().find(sb -> sb
                .where().equal("situacao", SituacaoEnum.ATIVO)
                .and().equal("cnpjRaiz", cnpjAgenteBancarioDebidato));
    }
}
