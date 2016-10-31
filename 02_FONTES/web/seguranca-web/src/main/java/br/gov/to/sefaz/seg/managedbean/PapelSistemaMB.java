package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.PapelSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PapelSistemaFilter;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedConverter;
import br.gov.to.sefaz.seg.managedbean.viewbean.OpcaoUsedViewBean;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos PapelSistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 14:27:00
 */
@ManagedBean(name = "papelSistemaMB")
@ViewScoped
public class PapelSistemaMB extends DefaultCrudMB<PapelSistema, Long> {

    private final PapelSistemaFilter filter;
    private Collection<OpcaoAplicacao> opcaoAplicacaos;
    private Collection<PerfilSistema> perfisSistema;
    private Collection<PapelOpcao> papelOpcaos;
    private Long identificacaoOpcaoAplicacao;
    private Collection<OpcaoUsedViewBean> opcaoFromPapelDto;
    @Autowired
    private OpcaoUsedConverter opcaoUsedConverter;

    public PapelSistemaMB() {
        super(PapelSistema::new);
        filter = new PapelSistemaFilter();
        opcaoAplicacaos = new ArrayList<>();
    }

    public PapelSistemaFilter getFilter() {
        return filter;
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachada de PapelSistema
     */
    @Autowired
    public void setFacade(PapelSistemaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PapelSistemaFacade getFacade() {
        return (PapelSistemaFacade) super.getFacade();
    }

    /**
     * Filtra os Papeis Sistema de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().findByFilter(filter).stream().collect(Collectors.toList());
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os Papeis Sistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos papéis.
     */
    @Override
    public Collection<PapelSistema> getResultList() {
        if (Objects.isNull(resultList) || resultList.size() == 0) {
            resultList = loadAllPapelSistema();
        }
        return resultList;
    }

    /**
     * Carrega todos os PapelSistema existentes no Banco de Dados.
     */
    private Collection<PapelSistema> loadAllPapelSistema() {
        return getFacade().findAllPapelSistema();
    }

    /**
     * Carrega todos os perfis.
     */
    public void loadAllPerfisByPapel() {
        perfisSistema = getFacade().findAllPerfilByPapel(getDto().getId())
                .stream()
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());

    }

    /**
     * Carrega todas as opções pelo Papel.
     */
    public void loadAllOpcoesByPapel() {
        papelOpcaos = getFacade().findAllPapelOpcaoById(getDto().getIdentificacaoPapel())
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Busca um único {@link PapelSistema} pelo ID passado por parâmetro.
     */
    public void findOnePapelSistemaById() {
        PapelSistema papelSistema = resultList.stream().filter(ps -> ps.getIdentificacaoPapel().equals(getDto()
                .getIdentificacaoPapel())).findFirst().get();
        papelSistema.setPapelOpcao(getFacade().findAllPapelOpcaoById(papelSistema.getIdentificacaoPapel()));
        setDto(papelSistema);
        loadAllPapelOpcao();
    }

    /**
     * Busca todos as opções do sistema.
     * @return lista de opções.
     */
    public Collection<OpcaoAplicacao> getOpcaoAplicacaos() {
        if (Objects.isNull(opcaoAplicacaos) || opcaoAplicacaos.isEmpty()) {
            opcaoAplicacaos = getFacade().findAllOpcaoAplicacao();
        }

        return opcaoAplicacaos;
    }

    public Collection<OpcaoUsedViewBean> getOpcaoFromPapel() {
        return opcaoFromPapelDto;
    }

    /**
     * Carrega todas as opções.
     */
    public void loadAllPapelOpcao() {
        opcaoFromPapelDto = opcaoUsedConverter.convert(getOpcaoAplicacaos(), this::isOpcaoUsed);
    }

    private boolean isOpcaoUsed(OpcaoAplicacao oa) {
        return getDto().getPapelOpcao().stream()
                .anyMatch(po -> po.getIdentificacaoOpcaoAplicacao().equals(oa.getIdentificacaoOpcaoAplicacao()));
    }

    /**
     * Adiciona uma opção, da lista de opções, no DTO.
     */
    public void addOptionToList() {
        setActiveInactive(true);
    }

    /**
     * Remove uma opção, da lista de opções, do DTO.
     */
    public void removeOptionFromList() {
        setActiveInactive(false);
    }

    private void setActiveInactive(boolean status) {
        opcaoFromPapelDto.stream().filter(opcaoUsedViewBean -> opcaoUsedViewBean
                .getIdentificacaoOpcaoAplicacao()
                .equals(identificacaoOpcaoAplicacao)).findFirst().get().setUsed(status);
    }

    public int getPapelOpcaoListSize() {
        return Objects.isNull(opcaoFromPapelDto) ? 0 : opcaoFromPapelDto.stream().filter(OpcaoUsedViewBean::getIsUsed)
                .collect(Collectors.toSet())
                .size();
    }

    public void setOpcaoAplicacaos(Collection<OpcaoAplicacao> opcaoAplicacaos) {
        this.opcaoAplicacaos = opcaoAplicacaos;
    }

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public Collection<PerfilSistema> getAllPerfilSistema() {
        return perfisSistema;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public Collection<PapelOpcao> getPapelOpcaos() {
        return papelOpcaos;
    }

    /**
     * Salva ou atualiza um {@link PapelSistema}.
     */
    public void saveOrUpdatePapelSistema() {
        PapelSistema papelSistema = getDto();
        papelSistema.setPapelOpcao(createSetPapelOpcao());
        papelSistema = getFacade().saveOrUpdatePapelSistema(papelSistema);

        PapelSistema savedPapelSistema = papelSistema;

        if (resultList.removeIf(ps -> ps.getId().equals(savedPapelSistema.getId()))) {
            showUpdateMessage();
        } else {
            showSaveMessage();
        }

        resultList = loadAllPapelSistema();
    }

    private Set<PapelOpcao> createSetPapelOpcao() {
        return opcaoFromPapelDto
                .stream()
                .filter(OpcaoUsedViewBean::getIsUsed)
                .collect(Collectors.toSet())
                .stream().map(this::createPapelOpcao)
                .collect(Collectors.toSet());
    }

    private PapelOpcao createPapelOpcao(OpcaoUsedViewBean opvb) {
        return new PapelOpcao(opvb.getIdentificacaoOpcaoAplicacao(), getDto().getIdentificacaoPapel());
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterPapelSistema.form.insert.sucesso");
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterPapelSistema.form.update.sucesso");

    }

    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterPapelSistema.form.delete.sucesso");
    }

    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterPapelSistema.form.delete.sucesso");
    }
}
