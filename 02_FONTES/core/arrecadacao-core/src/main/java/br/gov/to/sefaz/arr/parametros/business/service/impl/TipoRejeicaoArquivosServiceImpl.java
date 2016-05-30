package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoRejeicaoArquivosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idCodigoRejeicao")));
    }

    @Override
    protected TipoRejeicaoArquivosRepository getRepository() {
        return (TipoRejeicaoArquivosRepository) super.getRepository();
    }

    @Override
    public TipoRejeicaoArquivos save(@ValidationSuite(context = ValidationContext.SAVE) TipoRejeicaoArquivos entity) {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = super.save(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return tipoRejeicaoArquivos;
    }

    @Override
    public TipoRejeicaoArquivos update(
            @ValidationSuite(context = ValidationContext.UPDATE) TipoRejeicaoArquivos entity) {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = super.update(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return tipoRejeicaoArquivos;
    }

    @Override
    @Transactional
    public Optional<TipoRejeicaoArquivos> delete(Integer id) {
        Optional<TipoRejeicaoArquivos> tipoRejeicao;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            tipoRejeicao = Optional.of(getRepository().findOne(id));

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.logica");
        } else {
            super.delete(id);
            tipoRejeicao = Optional.empty();

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.fisica");
        }

        return tipoRejeicao;
    }
}
