package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.AtribuirPerfilFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.managedbean.viewbean.UsuarioPerfilViewBean;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:07:00
 */
@ManagedBean(name = "atribuirPerfilMB")
@ViewScoped
public class AtribuirPerfilMB extends DefaultCrudMB<UsuarioSistema, String> {

    private final AtribuirPerfilFilter filter;
    private Collection<TipoUsuario> allTipoUsuario;
    private Collection<PerfilSistema> allPerfilSistema;
    private Collection<UnidadeOrganizacional> allUnidadeOrganizacionais;
    private Collection<PostoTrabalho> allPostoTrabalho;
    private Collection<UsuarioPerfilViewBean> usuarioPerfilViewBeanCollection;
    private Long identificacaoPerfilSistema;

    public AtribuirPerfilMB() {

        super(UsuarioSistema::new);
        filter = new AtribuirPerfilFilter();
        allPostoTrabalho = new ArrayList<>();
        allUnidadeOrganizacionais = new ArrayList<>();
        allTipoUsuario = new ArrayList<>();
        allPerfilSistema = new ArrayList<>();
    }

    public AtribuirPerfilFilter getFilter() {
        return filter;
    }


    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     */
    @Autowired
    public void setFacade(AtribuirPerfilFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected AtribuirPerfilFacade getFacade() {
        return (AtribuirPerfilFacade) super.getFacade();
    }

    /**
     * Filtra os UsuarioSistema de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter)
                .stream().collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os UsuarioSistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     * Método alterado para não retornar dados ao abrir a tela.
     *
     * @return Lista dos usuários.
     */
    public Collection<UsuarioSistema> getResultList() {
        return resultList;
    }

    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterUsuarioSistema.table.createUser");
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    public Collection<UnidadeOrganizacional> getAllUnidadeOrganizacionais() {
        if (allUnidadeOrganizacionais.isEmpty()) {
            loadAllUnidadeOrganizacionais();
        }
        return allUnidadeOrganizacionais.stream().collect(Collectors.toList());
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados.
     */
    public void loadAllUnidadeOrganizacionais() {
        allUnidadeOrganizacionais = getFacade().findAllUnidadeOrganizacional();
    }

    /**
     * Carrega todas os Posto Trabalho existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos postos.
     */
    public Collection<PostoTrabalho> getAllPostoTrabalho() {
        if (allPostoTrabalho.isEmpty()) {
            loadPostosTrabalho();
        }
        return allPostoTrabalho;
    }

    /**
     * Busca todos os {@link TipoUsuario}.
     *
     * @return lista de TipoUsuario.
     */
    public Collection<TipoUsuario> getTipoUsuarios() {
        if (allTipoUsuario.isEmpty()) {
            loadTipoUsuarios();
        }
        return allTipoUsuario;
    }

    private void loadTipoUsuarios() {
        allTipoUsuario = getFacade().loadTipoUsuario();
    }

    /**
     * Busca lista de {@link PostoTrabalho}.
     */
    public void loadPostosTrabalho() {
        Long identificUnidOrganizac = getDto().getUnidadeOrganizacionalPostoTrabalho();

        if (!Objects.isNull(identificUnidOrganizac)) {
            allPostoTrabalho = getFacade().findAllPostoTrabalhoByUnidadeOrganizacional(identificUnidOrganizac);
        } else {
            allPostoTrabalho = getFacade().findAllPostoTrabalho();
        }
    }

    /**
     * Busca todos os {@link PerfilSistema} cadastrados.
     */
    public Collection<PerfilSistema> getAllPerfilSistema() {
        if (Objects.isNull(allPerfilSistema) || allPerfilSistema.isEmpty()) {
            allPerfilSistema = getFacade().findAllPerfilSistema();
        }
        return allPerfilSistema;
    }

    public Collection<UsuarioPerfilViewBean> getUsuarioPerfilViewBeanCollection() {
        return usuarioPerfilViewBeanCollection;
    }

    /**
     * Busca um usuário pelo registro marcado em tela.
     */
    public void findOneUsuarioById() {
        setDto(resultList.stream()
                .filter(us -> us.getCpfUsuario().equals(getDto().getCpfUsuario()))
                .findFirst()
                .get());
        setUsuarioPerfilViewBeanCollection(
                getAllPerfilSistema()
                        .stream()
                        .map(this::createUsuarioPerfilViewBeanCollection)
                        .collect(Collectors.toSet())
                        .stream()
                        .collect(Collectors.toList()));
    }

    private UsuarioPerfilViewBean createUsuarioPerfilViewBeanCollection(PerfilSistema perfilSistema) {
        Boolean isUsing = getDto().getUsuarioPerfil().stream().anyMatch(usuarioPerfil -> usuarioPerfil
                .getIdentificacaoPerfil().equals(perfilSistema.getIdentificacaoPerfil()));

        return new UsuarioPerfilViewBean(perfilSistema.getIdentificacaoPerfil(),perfilSistema.getDescricaoPerfil(),
                isUsing);
    }

    private Set<UsuarioPerfil> createUsuarioPerfilCollection(Collection<UsuarioPerfilViewBean>
            usuarioPerfilViewBeanCollection) {

        Set<UsuarioPerfil> usuarioPerfils = new HashSet<>();

        usuarioPerfilViewBeanCollection
                .stream()
                .filter(UsuarioPerfilViewBean::getUsing)
                .collect(Collectors.toList())
                .stream()
                .map(usuarioPerfilViewBean -> usuarioPerfils.add(new UsuarioPerfil(getDto().getCpfUsuario(),
                        usuarioPerfilViewBean.getIdentificacaoPerfil())))
                .collect(Collectors.toSet());


        return usuarioPerfils;
    }

    /**
     * Remove ou adiciona perfil à lista de perfis do usuário para salvar.
     */
    public void toggleConcederPerfil() {
        UsuarioPerfilViewBean perfilViewBean = usuarioPerfilViewBeanCollection
                .stream()
                .filter(upvb -> upvb.getIdentificacaoPerfil().equals(identificacaoPerfilSistema))
                .findFirst()
                .get();
        perfilViewBean.setUsing(!perfilViewBean.getUsing());
    }

    /**
     * Atribui um perfil ao usuário.
     */
    public void atribuirUsuarioPerfil() {
        UsuarioSistema usuarioSistema = getDto();
        Set<UsuarioPerfil> usuarioPerfilCollection = createUsuarioPerfilCollection(usuarioPerfilViewBeanCollection);
        usuarioSistema.setUsuarioPerfil(usuarioPerfilCollection);

        UsuarioSistema update = getFacade().updateUsuarioPerfil(usuarioSistema);

        resultList.removeIf(us -> us.getCpfUsuario().equals(usuarioSistema.getCpfUsuario()));
        resultList.add(update);

        showUpdateMessage();
    }

    public Long getIdentificacaoPerfilSistema() {
        return identificacaoPerfilSistema;
    }

    public void setIdentificacaoPerfilSistema(Long identificacaoPerfilSistema) {
        this.identificacaoPerfilSistema = identificacaoPerfilSistema;
    }

    public void setUsuarioPerfilViewBeanCollection(Collection<UsuarioPerfilViewBean> usuarioPerfilViewBeanCollection) {
        this.usuarioPerfilViewBeanCollection = usuarioPerfilViewBeanCollection;
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.atribuirPerfil.table.update");
    }
}
