package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.CadastroSenhaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.CadastroSenhaFilter;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:07:00
 *
 */
@ManagedBean(name = "cadastroSenhaMB")
@ViewScoped
public class CadastroSenhaMB extends DefaultCrudMB<UsuarioSistema, String> {

    private final CadastroSenhaFilter filter;

    public CadastroSenhaMB() {

        super(UsuarioSistema::new);
        filter = new CadastroSenhaFilter();
    }

    public CadastroSenhaFilter getFilter() {
        return filter;
    }


    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     */
    @Autowired
    public void setFacade(CadastroSenhaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected CadastroSenhaFacade getFacade() {
        return (CadastroSenhaFacade) super.getFacade();
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
    }

    /**
     * Reseta a senha do usuário.
     */
    public void resetUserPassword() {
        UsuarioSistema usuarioSistema = getFacade().findOne(getDto().getCpfUsuario());
        getFacade().resetPassword(usuarioSistema);
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.criaSenha.table.changePassword");
    }

    /**
     * Atualiza o status da solicitação do usuário, alterando-a para "Criado" e permitindo o mesmo a acessar o Sistema.
     */
    public void authorizeUser() {
        getFacade().authorizeUser(getDto());
        resultList.removeIf(usuarioSistema -> usuarioSistema.getCpfUsuario().equals(getDto().getCpfUsuario()));
        resultList.add(getDto());
        clearDto();
        showSaveMessage();
    }

    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.criaSenha.table.createUser");
    }

    public Collection<Logradouro> getLogradouros() {
        return getFacade().findAllLogradouros();
    }
}
