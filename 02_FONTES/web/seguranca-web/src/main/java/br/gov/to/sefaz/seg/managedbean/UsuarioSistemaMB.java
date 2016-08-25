package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
<<<<<<< Updated upstream
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.general.facade.UsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.general.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
=======
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.UsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
>>>>>>> Stashed changes
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 14:27:00
 */
@ManagedBean(name = "usuarioSistemaMB")
@ViewScoped
public class UsuarioSistemaMB extends DefaultCrudMB<UsuarioSistema, String> {

    private final UsuarioSistemaFilter filter;
    private Collection<TipoUsuario> allTipoUsuario;
    private Collection<Estado> estados;
    private Collection<Municipio> municipios;

    public UsuarioSistemaMB() {

        super(UsuarioSistema::new);
        filter = new UsuarioSistemaFilter();
    }

    public UsuarioSistemaFilter getFilter() {
        return filter;
    }


    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     */
    @Autowired
    public void setFacade(UsuarioSistemaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected UsuarioSistemaFacade getFacade() {
        return (UsuarioSistemaFacade) super.getFacade();
    }

    /**
     * Filtra os UsuarioSistema de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
<<<<<<< Updated upstream
            MessageUtil.addMesage(MessageUtil.SEG, "geral.pesquisa.vazia");
=======
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
>>>>>>> Stashed changes
        }
    }

    /**
     * Carrega todos os UsuarioSistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     * Método alterado para não retornar dados ao abrir a tela.
     *
     * @return Lista dos usuários.
     */
    public Collection<UsuarioSistema> getResultList() {
<<<<<<< Updated upstream
        return getFacade().find(filter);
=======
        return resultList;
>>>>>>> Stashed changes
    }

    /**
     * Carrega todas os Tipos de Usuário existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista dos tipos.
     */
    public Collection<TipoUsuario> getAllTipoUsuario() {
        if (allTipoUsuario == null) {
            loadAllTipoUsuario();
        }
        return allTipoUsuario;
    }

    private void loadAllTipoUsuario() {
        allTipoUsuario = getFacade().findAllTipoUsuario();
    }

    /**
     * Retorna a coleção dos estados cadastrados na base de dados. Caso a coleção já tenha sido consultada apenas
     * retorna, caso contrário, consulta os estados na base de dados antes de retornar.
     *
     * @return Coleção dos estados cadastrados
     */
    public Collection<Estado> getEstados() {
        if (estados == null) {
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
        if (municipios == null) {
            loadMunicipios();
        }
        return municipios;
    }

    /**
     * Carrega a lista de Municípios conforme UF (estado) selecionado.
     */
    public void loadMunicipios() {

<<<<<<< Updated upstream
        Optional<String> uf = Optional.ofNullable(filter.getCodigoEstado());

        if (!uf.isPresent()) {
            uf = getEstados().stream().findFirst().map(e -> e.getUnidadeFederacao());
=======
        Optional<String> uf = Optional.ofNullable(getDto().getCodigoEstado());

        if (!uf.isPresent()) {
            uf = getEstados().stream().findFirst().map(Estado::getUnidadeFederacao);
        }

        if (uf.isPresent()) {
            municipios = getFacade().findMunicipiosByUF(uf.get());
        }
    }

    /**
     * Carrega a lista de Municípios conforme UF (estado) selecionado.
     */
    public void loadMunicipiosFilter() {

        Optional<String> uf = Optional.ofNullable(filter.getCodigoEstado());

        if (!uf.isPresent()) {
            uf = getEstados().stream().findFirst().map(Estado::getUnidadeFederacao);
>>>>>>> Stashed changes
        }

        if (uf.isPresent()) {
            municipios = getFacade().findMunicipiosByUF(uf.get());
        }
    }

<<<<<<< Updated upstream
=======
    public Collection<Logradouro> getLogradouros() {
        return getFacade().findAllLogradouros();
    }

>>>>>>> Stashed changes
    /**
     * Busca o Usuário selecionado em tela.
     */
    public void getUsuarioById() {
<<<<<<< Updated upstream
        UsuarioSistema usuarioSistema = getFacade().findOne(getDto().getCpfUsuario());
        setDto(usuarioSistema);
    }
=======
        UsuarioSistema usuarioSistema = getFacade().findOneUsuarioSistema(getDto().getCpfUsuario());
        setDto(usuarioSistema);
    }

    /**
     * Salva novo usuário no Sistema.
     */
    public void saveNewUsuarioSistema() {
        getFacade().saveNewUsuarioSistema(getDto());
        showSaveMessage();
        clearDto();
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "geral.criaSenha.solicitacao");
    }
>>>>>>> Stashed changes
}
