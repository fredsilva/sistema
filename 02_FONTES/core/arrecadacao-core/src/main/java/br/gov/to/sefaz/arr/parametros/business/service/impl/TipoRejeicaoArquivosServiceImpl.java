package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.persistence.repository.TipoRejeicaoArquivosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Serviço para operações referentes a gerência de tipos de rejeição de arquivos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 03/05/2016 16:31:00
 */
@Service
public class TipoRejeicaoArquivosServiceImpl extends DefaultCrudService<TipoRejeicaoArquivos, Integer>
        implements TipoRejeicaoArquivosService {

    @Autowired
    public TipoRejeicaoArquivosServiceImpl(TipoRejeicaoArquivosRepository repository) {
        super(repository);
    }

    @Override
    protected TipoRejeicaoArquivosRepository getRepository() {
        return (TipoRejeicaoArquivosRepository) super.getRepository();
    }

    @Override
    public TipoRejeicaoArquivos save(@ValidationSuite(context = ValidationContext.SAVE) TipoRejeicaoArquivos entity) {
        return super.save(entity);
    }

    @Override
    public TipoRejeicaoArquivos update(
            @ValidationSuite(context = ValidationContext.UPDATE) TipoRejeicaoArquivos entity) {
        return super.update(entity);
    }

    @Override
    @Transactional
    public Optional<TipoRejeicaoArquivos> delete(Integer id) {
        Optional<TipoRejeicaoArquivos> tipoRejeicao;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            tipoRejeicao = Optional.of(getRepository().findOne(id));

        } else {
            super.delete(id);
            tipoRejeicao = Optional.empty();

        }

        return tipoRejeicao;
    }
}
