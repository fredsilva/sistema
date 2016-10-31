package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.ManterUsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:07:00
 */
@ManagedBean(name = "manterUsuarioSistemaMB")
@ViewScoped
public class ManterUsuarioSistemaMB extends DefaultCrudMB<UsuarioSistema, String> {

    private final ManterUsuarioSistemaFilter filter;
    private Collection<UnidadeOrganizacional> allUnidadeOrganizacionais;
    private Collection<PostoTrabalho> allPostoTrabalho;
    private Collection<Estado> estados;
    private Collection<Municipio> municipios;
    private Collection<TipoUsuario> tipoUsuarios;

    public ManterUsuarioSistemaMB() {

        super(UsuarioSistema::new);
        filter = new ManterUsuarioSistemaFilter();
        allPostoTrabalho = new ArrayList<>();
        allUnidadeOrganizacionais = new ArrayList<>();
        estados = new ArrayList<>();
        municipios = new ArrayList<>();
        tipoUsuarios = new ArrayList<>();
    }

    public ManterUsuarioSistemaFilter getFilter() {
        return filter;
    }


    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     */
    @Autowired
    public void setFacade(ManterUsuarioSistemaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected ManterUsuarioSistemaFacade getFacade() {
        return (ManterUsuarioSistemaFacade) super.getFacade();
    }

    /**
     * Filtra os UsuarioSistema de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
        setResultList(resultList);
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

    /**
     * Busca o Usuário selecionado em tela.
     */
    public void getUsuarioById() {
        UsuarioSistema usuarioSistema = getFacade().findOneUsuarioSistema(getDto().getCpfUsuario());
        setDto(usuarioSistema);
        loadMunicipios();
        loadPostosTrabalho();
    }

    /**
     * Reseta a senha do usuário.
     */
    public void resetUserPassword() {
        UsuarioSistema usuario = resultList.stream()
                .filter(us -> us.getCpfUsuario().equals(getDto().getCpfUsuario()))
                .findFirst().orElse(null);
        getFacade().resetPassword(usuario);
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.criaSenha.table.changePassword");
    }

    /**
     * Atualiza o status da solicitação do usuário, alterando-a para "Criado" e permitindo o mesmo a acessar o Sistema.
     */
    public void toggleStatusUsuario() {
        UsuarioSistema usuarioToggle = resultList
                .stream()
                .filter(us -> us.getCpfUsuario()
                        .equals(getDto()
                                .getCpfUsuario()))
                .findFirst()
                .orElse(null);
        getFacade().toggleUserStatus(usuarioToggle);
        usuarioToggle = getFacade().findOne(usuarioToggle.getId());
        updateElementResultList(usuarioToggle);
        showToggleMessage(usuarioToggle.getSituacaoUsuario());
        clearDto();
    }

    private void updateElementResultList(UsuarioSistema usuarioToggle) {
        resultList.removeIf(usuarioSistema -> usuarioSistema.getCpfUsuario().equals(usuarioToggle.getCpfUsuario()));
        resultList.add(usuarioToggle);
    }

    private void showToggleMessage(SituacaoUsuarioEnum situacaoUsuario) {
        if (SituacaoUsuarioEnum.ATIVO.equals(situacaoUsuario)) {
            MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterUsuarioSistema.table.activate");
        } else {
            MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterUsuarioSistema.table.inactivate");
        }
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
     * Retorna a coleção dos estados cadastrados na base de dados. Caso a coleção já tenha sido consultada apenas
     * retorna, caso contrário, consulta os estados na base de dados antes de retornar.
     *
     * @return Coleção dos estados cadastrados
     */
    public Collection<Estado> getEstados() {
        if (estados.isEmpty()) {
            estados = getFacade().findAllEstados();
        }
        return estados;
    }

    /**
     * Retorna a coleção de municípios da UF.
     *
     * @return Coleção de munípios da UF selecionada
     */
    public Collection<Municipio> getMunicipios() {
        if (municipios.isEmpty()) {
            loadMunicipios();
        }
        return municipios;
    }

    /**
     * Busca todos os {@link TipoUsuario}.
     * @return lista de TipoUsuario.
     */
    public Collection<TipoUsuario> getTipoUsuarios() {
        if (tipoUsuarios.isEmpty()) {
            loadTipoUsuarios();
        }
        return tipoUsuarios;
    }

    private void loadTipoUsuarios() {
        tipoUsuarios = getFacade().loadTipoUsuario();
    }

    /**
     * Carrega a lista de Municípios conforme UF (estado) selecionado.
     */
    public void loadMunicipios() {

        Optional<String> uf = Optional.ofNullable(getDto().getCodigoEstado());

        if (!uf.isPresent()) {
            uf = getEstados().stream().findFirst().map(Estado::getUnidadeFederacao);
        }

        if (uf.isPresent()) {
            municipios = getFacade().findMunicipiosByUF(uf.get());
        }

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

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manterUsuarioSistema.table.update");
    }

    public Collection<Logradouro> getLogradouros() {
        return getFacade().findAllLogradouros();
    }

}
