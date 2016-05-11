package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.AgenciasRepository;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ServiceValidation;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final BancosRepository bancosRepository;
    private final AgenciasRepository agenciasRepository;

    @Autowired
    public BancosServiceImpl(BancosRepository repository, AgenciasRepository agenciasRepository) {
        super(repository);
        this.bancosRepository = repository;
        this.agenciasRepository = agenciasRepository;
    }

    @Override
    public Bancos save(@ServiceValidation(context = ValidationContext.SAVE) Bancos entity) {
        Bancos bancos = super.save(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return bancos;
    }

    @Override
    public Bancos update(@ServiceValidation Bancos entity) {
        Bancos bancos = super.update(entity);
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return bancos;
    }

    @Override
    public Optional<Bancos> delete(@ServiceValidation Integer id) {
        Optional<Bancos> banco = Optional.empty();

        if (bancosRepository.existsInAnotherTable(id)) {
            banco = Optional.of(updateSituacao(id, SituacaoEnum.CANCELADO));
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.bancos.delecao.logica");
        } else {
            super.delete(id);
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.bancos.delecao.fisica");
        }

        return banco;
    }

    /**
     * Ataualiza a situação de um {@link Bancos} e de todas as suas {@link BancoAgencias}.
     *
     * @param id identificador de um {@link Bancos}.
     * @param situacao situação a ser atualizada.
     * @return o {@link Bancos} atualizado.
     */
    public Bancos updateSituacao(Integer id, SituacaoEnum situacao) {
        Bancos bancos = bancosRepository.findOne(id);
        bancos.setSituacao(situacao);
        List<BancoAgencias> bancoAgencias = bancosRepository.getAllBancoAgenciasFromIdBanco(id);

        if (!bancoAgencias.isEmpty()) {
            bancoAgencias.stream().forEach(bancoAgencia -> bancoAgencia.setSituacao(situacao));
            agenciasRepository.save(bancoAgencias);
        }

        return bancosRepository.save(bancos);
    }

}
