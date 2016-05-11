package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoRejeicaoArquivosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ServiceValidation;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final TipoRejeicaoArquivosRepository tipoRejeicaoArquivosRepository;

    @Autowired
    public TipoRejeicaoArquivosServiceImpl(TipoRejeicaoArquivosRepository repository) {
        super(repository);
        tipoRejeicaoArquivosRepository = repository;
    }

    @Override
    public TipoRejeicaoArquivos save(@ServiceValidation(context = ValidationContext.SAVE) TipoRejeicaoArquivos entity) {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = super.save(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return tipoRejeicaoArquivos;
    }

    @Override
    public TipoRejeicaoArquivos update(TipoRejeicaoArquivos entity) {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = super.update(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return tipoRejeicaoArquivos;
    }

    @Override
    public Optional<TipoRejeicaoArquivos> delete(@ServiceValidation Integer id) {
        Optional<TipoRejeicaoArquivos> tipoRejeicao = Optional.empty();
        if (tipoRejeicaoArquivosRepository.existsInAnotherTable(id)) {
            tipoRejeicao = Optional.of(updateSituacao(id, SituacaoEnum.CANCELADO));
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.tipo.rejeicao.delecao.logica");
        } else {
            super.delete(id);
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.tipo.rejeicao.delecao.fisica");
        }

        return tipoRejeicao;
    }

    /**
     * Ataualiza a situação de um {@link TipoRejeicaoArquivos}.
     *
     * @param id identificador de um {@link TipoRejeicaoArquivos}.
     * @param situacao situação a ser atualizada.
     */
    private TipoRejeicaoArquivos updateSituacao(Integer id, SituacaoEnum situacao) {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = tipoRejeicaoArquivosRepository.findOne(id);
        tipoRejeicaoArquivos.setSituacao(situacao);

        return tipoRejeicaoArquivosRepository.save(tipoRejeicaoArquivos);
    }
}
