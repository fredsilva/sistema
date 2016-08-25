package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.ProcuracaoUsuarioFacade;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedConverter;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedViewBean;
import br.gov.to.sefaz.seg.managedbean.viewbean.UsuarioCpfCnpjViewBean;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoOpcao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
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
    private List<UsuarioCpfCnpjViewBean> usuarioIdentifications;
    private String usuarioIdentification;
    private String procuradorNome;
    private String procuradorCpf;
    private Long identificacaoOpcaoApplicacao;

    public ManterProcuracaoMB() {
        super(ProcuracaoUsuario::new);
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

        UsuarioCpfCnpjViewBean usuarioCpfCnpj = usuarioIdentifications.stream()
                .filter(u -> u.getId().equals(usuarioIdentification))
                .findFirst().get();

        List<OpcaoAplicacao> opcaoAplicacaos;
        ProcuracaoUsuario procuracaoUsuario;

        if (usuarioCpfCnpj.isCpf()) {
            procuracaoUsuario = getFacade().findProcuracaoByCpf(usuarioCpfCnpj.getId(), procuradorCpf);
            opcaoAplicacaos = getFacade().getOpcoesFromUsuario();
        } else {
            procuracaoUsuario = getFacade().findProcuracaoByCnpj(usuarioCpfCnpj.getId(), procuradorCpf);
            opcaoAplicacaos = new ArrayList<>();
        }

        if (!Objects.isNull(procuracaoUsuario)) {
            setDto(procuracaoUsuario);
        } else {
            if (usuarioCpfCnpj.isCpf()) {
                getDto().setCpfOrigem(usuarioCpfCnpj.getId());
            } else {
                getDto().setCnpjOrigem(usuarioCpfCnpj.getId());
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
    public List<UsuarioCpfCnpjViewBean> getUsuarioIdentifications() {
        if (usuarioIdentifications == null) {
            usuarioIdentifications = new ArrayList<>();
            UsuarioSistema usuarioSistema = getFacade().findUsuarioSistema();
            String cpfUsuario = usuarioSistema.getCpfUsuario();
            String cpfComboPattern = SourceBundle
                    .getMessage(MessageUtil.SEG, "seg.gestao.manterProcuracao.usuarioCpfPattern");
            String cpfComboLabel = String
                    .format(cpfComboPattern, FormatterUtil.formatCpf(cpfUsuario));

            usuarioIdentifications.add(new UsuarioCpfCnpjViewBean(cpfUsuario, cpfComboLabel, true));
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
