package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.BancoAgenciasService;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.arr.persistence.repository.BancoAgenciasRepository;
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
        super(repository);
    }

    @Override
    protected BancoAgenciasRepository getRepository() {
        return (BancoAgenciasRepository) super.getRepository();
    }

    @Override
    public Collection<BancoAgencias> findByIdBanco(Integer idBanco) {
        return getRepository().find("ba", sb -> sb.innerJoinFetch("ba.bancos").where().equal("ba.idBanco", idBanco));
    }

    @Override
    public List<BancoAgencias> findByCtaBanco(Integer cnpjAgenteBancarioCreditado, Integer agencia) {
        return getRepository().find("ba", sb -> sb
                .innerJoinFetch("ba.bancos", "b")
                .where()
                .equal("b.cnpjRaiz", cnpjAgenteBancarioCreditado)
                .and().equal("ba.idAgencia", agencia)
                .and().equal("b.situacao", SituacaoEnum.ATIVO)
                .and().equal("ba.situacao", SituacaoEnum.ATIVO));
    }

    @Override
    public void validateSave(@ValidationSuite(context = ValidationContext.SAVE) BancoAgencias agencia) {
        // Método que valida as ações de SAVE de uma agência através da anotação de atributo.
    }

    @Override
    public void validateSave(@ValidationSuite(context = ValidationContext.SAVE) Collection<BancoAgencias> list) {
        // Método que valida as ações de SAVE de uma coleção de agências através da anotação de atributo.
    }

    @Override
    public void validateUpdate(@ValidationSuite(context = ValidationContext.UPDATE) BancoAgencias agencia) {
        // Método que valida as ações de UPDATE de uma agência através da anotação de atributo.
    }

    @Override
    public void validateUpdate(@ValidationSuite(context = ValidationContext.UPDATE) Collection<BancoAgencias> list) {
        // Método que valida as ações de UPDATE de uma coleção de agências através da anotação de atributo.
    }

    @Override
    @Transactional
    public Optional<BancoAgencias> delete(BancoAgenciasPK id) {
        Optional<BancoAgencias> bancoAgencias;

        if (getRepository().existsLockReference(id.getIdBanco(), id.getIdAgencia())) {
            getRepository().updateSituacao(id.getIdBanco(), id.getIdAgencia(), SituacaoEnum.CANCELADO);
            bancoAgencias = Optional.of(getRepository().findOne(id));
        } else {
            super.delete(id);
            bancoAgencias = Optional.empty();
        }

        return bancoAgencias;
    }

    @Override
    public Collection<BancoAgencias> getAllActiveBancoAgenciasFromIdBanco(Integer idBanco) {
        return getRepository().find(sb -> sb.where()
                .equal("situacao", SituacaoEnum.ATIVO)
                .and().equal("idBanco", idBanco));
    }

}
