package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.service.validation.custom.CpfValidatorHandler;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.ProcuracaoUsuarioFacade;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedConverter;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedViewBean;
import br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoOpcao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Manage bean da tela de manutenção de procuração de usuários.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/08/2016 16:55:00
 */
@ManagedBean(name = "manterProcuracaoMB")
@ViewScoped
public class ManterProcuracaoMB extends DefaultCrudMB<ProcuracaoUsuario, Long> {

    @Autowired
    private OpcaoUsedConverter opcaoUsedConverter;
    private List<OpcaoUsedViewBean> opcoesFromUsuario;
    private List<ListagemCpfProcuracao> usuarioIdentifications;
    private String usuarioIdentification;
    private String procuradorNome;
    private String procuradorCpf;
    private Long identificacaoOpcaoApplicacao;

    private final CpfValidatorHandler cpfValidator;

    public ManterProcuracaoMB() {
        super(ProcuracaoUsuario::new);
        this.cpfValidator = new CpfValidatorHandler();
        opcoesFromUsuario = new ArrayList<>();
        procuradorNome = "";
    }

    @Autowired
    protected void setFacade(ProcuracaoUsuarioFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected ProcuracaoUsuarioFacade getFacade() {
        return (ProcuracaoUsuarioFacade) super.getFacade();
    }

    /**
     * Carreca os dados do usuario procurador bem como a procuração vinculada a ele em relação ao usuario da sessão.
     */
    public void loadUsuarioProcurador() {
        procuradorNome = getFacade().findUsuarioNomeById(procuradorCpf);

        ListagemCpfProcuracao usuarioCpfCnpj = usuarioIdentifications.stream()
                .filter(u -> u.getCpfCnpj().equals(usuarioIdentification))
                .findFirst().get();

        List<OpcaoAplicacao> opcaoAplicacaos;
        ProcuracaoUsuario procuracaoUsuario;

        if (cpfValidator.validateLength(usuarioCpfCnpj.getCpfCnpj())) {
            procuracaoUsuario = getFacade().findProcuracaoByCpf(usuarioCpfCnpj.getCpfCnpj(), procuradorCpf);
        } else {
            procuracaoUsuario = getFacade().findProcuracaoByCnpj(usuarioCpfCnpj.getCpfCnpj(), procuradorCpf);
        }

        opcaoAplicacaos = getFacade().getOpcoesFromUsuario();

        if (!Objects.isNull(procuracaoUsuario)) {
            setDto(procuracaoUsuario);
        } else {
            if (cpfValidator.validateLength(usuarioCpfCnpj.getCpfCnpj())) {
                getDto().setCpfOrigem(usuarioCpfCnpj.getCpfCnpj());
            } else {
                getDto().setCnpjOrigem(usuarioCpfCnpj.getCpfCnpj());
            }

            getDto().setCpfProcurado(procuradorCpf);
        }

        opcoesFromUsuario = opcaoUsedConverter.convert(opcaoAplicacaos, this::isUsedByProcuracao);
    }

    public List<OpcaoUsedViewBean> getOpcoesFromUsuario() {
        return opcoesFromUsuario;
    }

    private boolean isUsedByProcuracao(OpcaoAplicacao opcaoAplicacao) {
        return getDto().getProcuracaoOpcoes().stream()
                .anyMatch(po -> po.getIdentificacaoOpcaoAplicacao()
                        .equals(opcaoAplicacao.getIdentificacaoOpcaoAplicacao()));
    }

    /**
     * Retorna uma lista com o cpf e cnpj vinculados ao usuário na sessão.
     *
     * @return lista com o cpf e cnpj
     */
    public List<ListagemCpfProcuracao> getUsuarioIdentifications() {
        if (usuarioIdentifications == null) {
            usuarioIdentifications = getFacade().findAllCpfProcuracaoFromUsuario();
        }

        return usuarioIdentifications;
    }

    public String getProcuradorNome() {
        return procuradorNome;
    }

    public String getUsuarioIdentification() {
        return usuarioIdentification;
    }

    public void setUsuarioIdentification(String usuarioIdentification) {
        this.usuarioIdentification = usuarioIdentification;
    }

    public String getProcuradorCpf() {
        return procuradorCpf;
    }

    public void setProcuradorCpf(String procuradorCpf) {
        this.procuradorCpf = procuradorCpf;
    }

    public void setIdentificacaoOpcaoApplicacao(Long identificacaoOpcaoApplicacao) {
        this.identificacaoOpcaoApplicacao = identificacaoOpcaoApplicacao;
    }

    public Long getIdentificacaoOpcaoApplicacao() {
        return identificacaoOpcaoApplicacao;
    }

    /**
     * Altera o estado de usado de uma opção da aplicação em relação a uma procuração.
     */
    public void toggleOpcaoAplicacao() {
        opcoesFromUsuario.stream()
                .filter(ou -> ou.getIdentificacaoOpcaoAplicacao().equals(identificacaoOpcaoApplicacao))
                .forEach(ou -> ou.setUsed(!ou.getIsUsed()));
    }

    @Override
    public void save() {
        prepareDto();

        if (Objects.isNull(getDto().getIdentificacaoProcurUsuario())) {
            super.save();
        } else {
            super.update();
        }
    }

    private void prepareDto() {
        Long idProcurUsuario = getDto().getIdentificacaoProcurUsuario();

        List<ProcuracaoOpcao> opcoes = getOpcoesFromUsuario().stream()
                .filter(OpcaoUsedViewBean::getIsUsed)
                .map(o -> new ProcuracaoOpcao(idProcurUsuario, o.getIdentificacaoOpcaoAplicacao()))
                .collect(Collectors.toList());

        getDto().setProcuracaoOpcoes(opcoes);
    }
}
