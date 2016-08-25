package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.PerfilSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PerfilSistemaFilter;
import br.gov.to.sefaz.seg.managedbean.viewbean.PerfilPapelViewBean;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos PerfilSistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 29/07/2016 14:28:00
 */
@ManagedBean(name = "perfilSistemaMB")
@ViewScoped
public class PerfilSistemaMB extends DefaultCrudMB<PerfilSistema, Long> {

    private final PerfilSistemaFilter filter;
    private Collection<PapelSistema> allPapelSistemas;
    private Collection<PapelSistema> papelSistemasById;
    private Collection<PerfilPapelViewBean> perfilPapelViewBeanCollection;
    private Collection<UsuarioPerfil> usuarioPerfils;
    private Long identificacaoPapelSistema;

    public PerfilSistemaMB() {
        super(PerfilSistema::new);
        filter = new PerfilSistemaFilter();
        allPapelSistemas = new ArrayList<>();
        usuarioPerfils = new ArrayList<>();
    }

    public PerfilSistemaFilter getFilter() {
        return filter;
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachada de PerfilSistema
     */
    @Autowired
    public void setFacade(PerfilSistemaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PerfilSistemaFacade getFacade() {
        return (PerfilSistemaFacade) super.getFacade();
    }

    /**
     * Filtra os PerfilSistema de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().findByFilter(filter).stream().collect(Collectors.toList());
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os PerfilSistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos PerfilSistema.
     */
    @Override
    public Collection<PerfilSistema> getResultList() {
        if (Objects.isNull(resultList) || resultList.size() == 0) {
            resultList = loadAllPerfilSistema();
        }
        return resultList;
    }

    /**
     * Carrega todos os PerfilSistema existentes no Banco de Dados.
     */
    private Collection<PerfilSistema> loadAllPerfilSistema() {
        return getFacade()
                .findAllPerfilSistema(new PerfilSistemaFilter(StringUtils.EMPTY));
    }

    /**
     * Carrega todos os Papéis para mostrar na tabela.
     */
    public void loadAllPapelSistema() {
        perfilPapelViewBeanCollection = getAllPapelSistemas().stream()
                .map(this::createPapelViewBean)
                .collect(Collectors.toList());
    }

    private PerfilPapelViewBean createPapelViewBean(PapelSistema papelSistema) {
        boolean isUsing = getDto().getPerfilPapel()
                .stream()
                .anyMatch(pp -> pp.getIdentificacaoPapel().equals(papelSistema.getIdentificacaoPapel()));

        return new PerfilPapelViewBean(papelSistema.getNomePapel(), papelSistema.getDescricaoPapel(), papelSistema
                .getIdentificacaoPapel(), isUsing);
    }

    /**
     * Carrega todos os papéis do Perfil.
     */
    public void loadAllPapelByPerfil() {
        papelSistemasById = getFacade().findAllPapelByPerfil(getDto().getId());

    }

    /**
     * Carrega todos os Usuários pelo Perfil.
     */
    public void loadAllUsusariosByPerfil() {
        usuarioPerfils = getFacade().findAllUsuariosByPerfil(getDto().getIdentificacaoPerfil());
    }

    /**
     * Busca um único {@link PerfilSistema} pelo ID passado por parâmetro.
     */
    public void findOnePerfilSistemaById() {
        PerfilSistema perfilSistema = resultList.stream()
                .filter(ps -> ps.getIdentificacaoPerfil().equals(getDto().getIdentificacaoPerfil()))
                .findFirst()
                .get();
        perfilSistema
                .setPerfilPapel(getFacade()
                        .findAllPerfilPapelByPerfil(perfilSistema
                                .getIdentificacaoPerfil())
                        .stream()
                        .collect(Collectors.toSet()));
        setDto(perfilSistema);
        loadAllPapelSistema();
    }

    /**
     * Busca todos os {@link PapelSistema}.
     * @return lista de PapelSistema.
     */
    public Collection<PapelSistema> getAllPapelSistemas() {

        if (Objects.isNull(allPapelSistemas) || allPapelSistemas.isEmpty()) {
            allPapelSistemas = getFacade().findAllPapelSistema();
        }

        return allPapelSistemas;
    }

    /**
     * Adiciona uma opção, da lista de papéis, no DTO.
     */
    public void addPapelToList() {
        setUsingPapelStatus(true);
    }

    /**
     * Remove uma opção, da lista de papéis, do DTO.
     */
    public void removePapelFromList() {
        setUsingPapelStatus(false);
    }

    private void setUsingPapelStatus(Boolean status) {
        perfilPapelViewBeanCollection.stream()
                .filter(perfilPapelViewBean -> perfilPapelViewBean.getIdentificacaoPapel()
                        .equals(identificacaoPapelSistema)).findFirst().get().setUsing(status);
    }

    public void setAllPapelSistemas(Collection<PapelSistema> allPapelSistemas) {
        this.allPapelSistemas = allPapelSistemas;
    }

    public Long getIdentificacaoPapelSistema() {
        return identificacaoPapelSistema;
    }

    public void setIdentificacaoPapelSistema(Long identificacaoPapelSistema) {
        this.identificacaoPapelSistema = identificacaoPapelSistema;
    }

    public Collection<UsuarioPerfil> getUsuarioPerfils() {
        return usuarioPerfils.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
    }

    public Collection<PerfilPapelViewBean> getPerfilPapelViewBeanCollection() {
        return perfilPapelViewBeanCollection;
    }

    public Integer getPerfilPapelViewBeanCollectionSize() {
        return Objects.isNull(perfilPapelViewBeanCollection) ? 0 : perfilPapelViewBeanCollection.stream()
                .filter(PerfilPapelViewBean::getUsing)
                .collect(Collectors.toSet())
                .size();
    }

    public void setPerfilPapelViewBeanCollection(Collection<PerfilPapelViewBean> perfilPapelViewBeanCollection) {
        this.perfilPapelViewBeanCollection = perfilPapelViewBeanCollection;
    }

    /**
     * Salva ou atualiza um {@link PapelSistema}.
     */
    public void saveOrUpdatePerfilSistema() {
        getDto().setPerfilPapel(perfilPapelViewBeanCollection.stream().filter(PerfilPapelViewBean::getUsing)
                .map(this::createPerfilPapel)
                .collect(Collectors.toSet()));

        PerfilSistema savePerfilSistema = getFacade().saveOrUpdatePerfilSistema(getDto());

        if (resultList.removeIf(ps -> ps.getId().equals(savePerfilSistema.getId()))) {
            showUpdateMessage();
        } else {
            showSaveMessage();
        }

        resultList = loadAllPerfilSistema();
    }

    private PerfilPapel createPerfilPapel(PerfilPapelViewBean perfilPapelViewBean) {
        return new PerfilPapel(getDto().getIdentificacaoPerfil(),perfilPapelViewBean.getIdentificacaoPapel());
    }

    public Collection<PapelSistema> getPapelSistemasById() {
        return papelSistemasById;
    }

    @Override
    public void delete() {
        super.delete();
        resultList.removeIf(perfilSistema ->
                perfilSistema.getIdentificacaoPerfil().equals(getDto().getIdentificacaoPerfil()));
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.perfilSistema.form.insert.sucesso");
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.perfilSistema.form.update.sucesso");
    }

    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.perfilSistema.form.delete.sucesso");
    }

    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.perfilSistema.form.delete.sucesso");
    }
}
