package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.ProcuracaoOpcaoService;
import br.gov.to.sefaz.seg.business.gestao.service.ProcuracaoUsuarioService;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoOpcao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.repository.ProcuracaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de um {@link ProcuracaoUsuarioService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/08/2016 17:40:00
 */
@Service
public class ProcuracaoUsuarioServiceImpl extends DefaultCrudService<ProcuracaoUsuario, Long>
        implements ProcuracaoUsuarioService {

    private final ProcuracaoOpcaoService procuracaoOpcaoService;

    @Autowired
    public ProcuracaoUsuarioServiceImpl(ProcuracaoUsuarioRepository repository,
            ProcuracaoOpcaoService procuracaoOpcaoService) {
        super(repository);
        this.procuracaoOpcaoService = procuracaoOpcaoService;
    }

    @Override
    protected ProcuracaoUsuarioRepository getRepository() {
        return (ProcuracaoUsuarioRepository) super.getRepository();
    }

    @Override
    public ProcuracaoUsuario findProcuracaoByCpf(String cpfOrigem, String procuradorCpf) {
        return getRepository().findByCpf(cpfOrigem, procuradorCpf);
    }

    @Override
    public ProcuracaoUsuario findProcuracaoByCnpj(String cnpjOrigem, String procuradorCpf) {
        return getRepository().findByCnpj(cnpjOrigem, procuradorCpf);
    }

    @Override
    @Transactional
    public ProcuracaoUsuario save(@ValidationSuite(context = ValidationContext.SAVE) ProcuracaoUsuario entity) {
        List<ProcuracaoOpcao> procuracaoOpcoes = entity.getProcuracaoOpcoes();
        entity.setProcuracaoOpcoes(null);

        ProcuracaoUsuario save = super.save(entity);
        procuracaoOpcoes.forEach(po -> po.setIdentificacaoProcurUsuario(save.getIdentificacaoProcurUsuario()));

        entity.setProcuracaoOpcoes(procuracaoOpcaoService.save(procuracaoOpcoes)
                .stream().collect(Collectors.toList()));

        return save;
    }

    @Override
    @Transactional
    public ProcuracaoUsuario update(@ValidationSuite(context = ValidationContext.UPDATE) ProcuracaoUsuario entity) {
        List<ProcuracaoOpcao> procuracaoOpcoes = entity.getProcuracaoOpcoes();
        entity.setProcuracaoOpcoes(null);

        ProcuracaoUsuario save = super.update(entity);
        procuracaoOpcoes.forEach(po -> po.setIdentificacaoProcurUsuario(save.getIdentificacaoProcurUsuario()));

        procuracaoOpcaoService.deleteByProcuracao(save.getIdentificacaoProcurUsuario());
        entity.setProcuracaoOpcoes(procuracaoOpcaoService.save(procuracaoOpcoes)
                .stream().collect(Collectors.toList()));

        return save;
    }
}
