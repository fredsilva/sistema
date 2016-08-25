package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.AtivarInativarPerfilFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 14:27:00
 */
@ManagedBean(name = "ativarInativarPerfilMB")
@ViewScoped
public class AtivarInativarPerfilMB extends DefaultCrudMB<UsuarioPerfil, Long> {

    private final AtivarInativarPerfilFilter filter;
    private Collection<UsuarioPerfil> allPerfisUsuario;
    private Collection<PostoTrabalho> allPostoTrabalho;
    private Collection<UnidadeOrganizacional> allUnidadeOrganizacional;
    private Collection<UsuarioSistema> allUsuarioSistema;
    private Long idPerfilDto;

    public AtivarInativarPerfilMB() {
        super(UsuarioPerfil::new);
        this.filter = new AtivarInativarPerfilFilter();
        this.allPerfisUsuario = new ArrayList<>();
        this.allUsuarioSistema = new ArrayList<>();
    }

    public AtivarInativarPerfilFilter getFilter() {
        return filter;
    }


    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     */
    @Autowired
    public void setFacade(AtivarInativarPerfilFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected AtivarInativarPerfilFacade getFacade() {
        return (AtivarInativarPerfilFacade) super.getFacade();
    }

    /**
     * Filtra os UsuarioSistema de acordo com os dados informados em tela.
     */
    public void search() {
        allUsuarioSistema = getFacade().find(filter);
        if (allUsuarioSistema.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os UsuarioSistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     * Método alterado para não retornar dados ao abrir a tela.
     *
     * @return Lista dos usuários.
     */
    public Collection<UsuarioPerfil> getResultList() {
        return resultList;
    }

    /**
     * Carrega todas os Posto Trabalho existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos postos.
     */
    public Collection<PostoTrabalho> getAllPostoTrabalho() {
        if (Objects.isNull(allPostoTrabalho)) {
            loadAllPostoTrabalho();
        }
        return allPostoTrabalho;
    }

    /**
     * Carrega todos os PostoTrabalho.
     */
    private void loadAllPostoTrabalho() {
        allPostoTrabalho = getFacade().findAllPostoTrabalho();
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     * Método alterado para não retornar dados ao abrir a tela.
     */
    private void loadAllUnidadeOrganizacional() {
        allUnidadeOrganizacional = getFacade().findAllUnidadeOrganizacional();
    }

    /**
     * Carrega todas as as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos tipos.
     */
    public Collection<UnidadeOrganizacional> getAllUnidadeOrganizacional() {

        if (Objects.isNull(allUnidadeOrganizacional)) {

            loadAllUnidadeOrganizacional();
        }
        return allUnidadeOrganizacional;
    }

    /**
     * Método para buscar a lista de perfis que está configurado para o usuário.
     */
    public Collection<UsuarioPerfil> getAllPerfisUsuario() {
        return allPerfisUsuario;
    }

    /**
     * Seta os perfis dos respectivos Usuários.
     */
    public void setAllPerfisUsuariosView() {
        UsuarioSistema usuarioSistema = allUsuarioSistema.stream().filter( us -> us.getCpfUsuario().equals(getDto()
                .getCpfUsuario()))
                .findFirst().orElse(new UsuarioSistema());
        allPerfisUsuario = getFacade().loadAllUsuarioPerfil(usuarioSistema);
    }

    /**
     * Muda a situação do checkbox de Ativo/Inativo e vice-e-versa.
     */
    public void toggleSituacao() {
        UsuarioPerfil usuarioPerfil = allPerfisUsuario.stream()
                .filter(up -> up
                        .getIdentificacaoUsuarioPerfil().equals(getIdPerfilDto()))
                .findFirst().orElse(null);
        if (!Objects.isNull(usuarioPerfil) && usuarioPerfil.getSituacaoPerfil() == SituacaoUsuarioEnum.ATIVO) {
            usuarioPerfil.setSituacaoPerfil(SituacaoUsuarioEnum.INATIVO);
        } else if (!Objects.isNull(usuarioPerfil)) {
            usuarioPerfil.setSituacaoPerfil(SituacaoUsuarioEnum.ATIVO);
        }
    }

    /**
     * Atualiza os status alterados dos {@link UsuarioPerfil} de um {@link UsuarioSistema}.
     */
    public void updatePerfilUsuario() {
        getFacade().updatePerfilUsuarioList(allPerfisUsuario);
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.ativarInativarPerfil.form.sucesso");
    }

    public Long getIdPerfilDto() {
        return idPerfilDto;
    }

    public void setIdPerfilDto(Long idPerfilDto) {
        this.idPerfilDto = idPerfilDto;
    }

    public String getNomeUsuario() {
        return allUsuarioSistema.stream()
                .filter(us -> us.getCpfUsuario().equals(allPerfisUsuario.stream()
                        .findFirst()
                        .map(UsuarioPerfil::getCpfUsuario)
                        .orElse("")))
                .findFirst()
                .map(UsuarioSistema::getNomeCompletoUsuario)
                .orElse("");
    }

    public Collection<UsuarioSistema> getAllUsuarioSistema() {
        return allUsuarioSistema;
    }

    public void setAllUsuarioSistema(Collection<UsuarioSistema> allUsuarioSistema) {
        this.allUsuarioSistema = allUsuarioSistema;
    }
}
