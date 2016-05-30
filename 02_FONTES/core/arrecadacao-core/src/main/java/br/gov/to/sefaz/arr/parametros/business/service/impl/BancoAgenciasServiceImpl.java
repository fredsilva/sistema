package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.BancoAgenciasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancoAgenciasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementação do serviço de Agencias Bancárias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
@Service
public class BancoAgenciasServiceImpl extends DefaultCrudService<BancoAgencias, BancoAgenciasPK>
        implements BancoAgenciasService {

    @Autowired
    public BancoAgenciasServiceImpl(
            BancoAgenciasRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idBanco"),
                new Sort.Order(Sort.Direction.ASC, "idAgencia")));
    }

    @Override
    protected BancoAgenciasRepository getRepository() {
        return (BancoAgenciasRepository) super.getRepository();
    }

    @Override
    public Collection<BancoAgencias> findByIdBanco(Integer idBanco) {
        return getRepository().findByIdBanco(idBanco);
    }

    @Override
    public void validateSave(@ValidationSuite(context = ValidationContext.SAVE) BancoAgencias agencia) {
        // Método que valida as ações de SAVE de uma agência através da anotação de atributo.
    }

    @Override
    public void validateSave(@ValidationSuite(context = ValidationContext.SAVE, isCollection = true,
            clazz = BancoAgencias.class) Collection<BancoAgencias> list) {
        // Método que valida as ações de SAVE de uma coleção de agências através da anotação de atributo.
    }

    @Override
    public void validateUpdate(@ValidationSuite(context = ValidationContext.UPDATE) BancoAgencias agencia) {
        // Método que valida as ações de UPDATE de uma agência através da anotação de atributo.
    }

    @Override
    public void validateUpdate(@ValidationSuite(context = ValidationContext.UPDATE, isCollection = true,
            clazz = BancoAgencias.class) Collection<BancoAgencias> list) {
        // Método que valida as ações de UPDATE de uma coleção de agências através da anotação de atributo.
    }

    @Override
    @Transactional
    public Optional<BancoAgencias> delete(@ValidationSuite BancoAgenciasPK id) {
        Optional<BancoAgencias> bancoAgencias;

        if (getRepository().existsLockReference(id.getIdBanco(), id.getIdAgencia())) {
            getRepository().updateSituacao(id.getIdBanco(), id.getIdAgencia(), SituacaoEnum.CANCELADO);
            bancoAgencias = Optional.of(getRepository().findOne(id));
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.logica");
        } else {
            super.delete(id);
            bancoAgencias = Optional.empty();
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.fisica");
        }

        return bancoAgencias;
    }

    @Override
    public Collection<BancoAgencias> getAllActiveBancoAgenciasFromIdBanco(Integer idBanco) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("situacao", SituacaoEnum.ATIVO)
                .equalsTo("idBanco", idBanco)
                .build(), getDefaultSort());
    }

}
