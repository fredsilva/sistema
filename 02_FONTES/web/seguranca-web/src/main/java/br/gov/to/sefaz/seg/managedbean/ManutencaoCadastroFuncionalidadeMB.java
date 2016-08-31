package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.ManutencaoCadastroFuncionalidadeFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da Manutenção do Cadastro de Funcionalidade.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 09:09:00
 */
@ManagedBean(name = "manutencaoCadastroFuncionalidadeMB")
@ViewScoped
public class ManutencaoCadastroFuncionalidadeMB extends DefaultCrudMB<OpcaoAplicacao, Long> {

    private final OpcaoAplicacaoFilter filter;

    private Collection<ModuloSistema> allModuloSistema;
    private Collection<AplicacaoModulo> allAplicacaoModulo;
    private Collection<AplicacaoModulo> aplicacoesPorModulo;


    public ManutencaoCadastroFuncionalidadeMB() {
        super(() -> {
            AplicacaoModulo aplicacaoModulo = new AplicacaoModulo();
            aplicacaoModulo.setModuloSistema(new ModuloSistema());

            OpcaoAplicacao opt = new OpcaoAplicacao();
            opt.setAjudaOpcao(StringUtils.EMPTY);
            opt.setAplicacaoModulo(aplicacaoModulo);
            return opt;
        });

        filter = new OpcaoAplicacaoFilter();
        aplicacoesPorModulo = new ArrayList<>();
        resultList = new ArrayList<>();
    }

    /**
     * Busca todos os módulos.
     *
     * @return Lista de ModuloSistema.
     */
    public Collection<ModuloSistema> getAllModuloSistema() {
        if (allModuloSistema == null) {
            loadAllModuloSistema();
        }
        return allModuloSistema;
    }

    /**
     * Busca todos os múdulos da aplicação.
     *
     * @return Lista de Aplicações.
     */
    public Collection<AplicacaoModulo> getAllAplicacaoModulo() {
        if (allAplicacaoModulo == null) {
            loadAllAplicacaoModulo();
        }
        return allAplicacaoModulo;
    }

    /**
     * Busca todos os módulos do Sistema.
     */
    public void loadAllModuloSistema() {
        allModuloSistema = getFacade().findAllModuloSistema();
        allModuloSistema.stream().findFirst()
                .ifPresent(moduloSistema -> loadAplicacoesPorModuloOpen(moduloSistema.getIdentificacaoModuloSistema()));
    }

    /**
     * Busca todos os módulos da aplicação.
     */
    public void loadAllAplicacaoModulo() {
        allAplicacaoModulo = getFacade().findAllAplicacaoModulo();
    }

    public OpcaoAplicacaoFilter getFilter() {
        return filter;
    }

    @Override
    protected ManutencaoCadastroFuncionalidadeFacade getFacade() {
        return (ManutencaoCadastroFuncionalidadeFacade) super.getFacade();
    }

    /**
     * {@link DefaultCrudMB#setFacade(br.gov.to.sefaz.business.facade.CrudFacade)}.
     */
    @Autowired
    public void setFacade(ManutencaoCadastroFuncionalidadeFacade facade) {
        super.setFacade(facade);
    }

    /**
     * Pesquisa as funcionalidades para serem mostradas na tela conforme o filtro informado.
     */
    public void search() {
        List<OpcaoAplicacao> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Carrega todas as opções do sistema existentes no Banco de Dados. - Utilizada para recarregar tabela.
     * Método alterado para não retornar dados ao abrir a tela.
     *
     * @return Lista de opção da aplicação.
     */
    @Override
    public Collection<OpcaoAplicacao> getResultList() {
        return resultList;
    }

    /**
     * Carrega todas as aplicações por módulo.
     */
    public void loadAplicacoesPorModulo() {
        this.aplicacoesPorModulo = getFacade()
                .findAplicacoesPorModulo(getDto().getAplicacaoModulo().getIdentificacaoModuloSistema());
    }

    /**
     * Carrega todas as aplicações por módulo.
     */
    public void loadAplicacoesPorModuloOpen(Long identificacaoModulo) {
        this.aplicacoesPorModulo = getFacade()
                .findAplicacoesPorModulo(identificacaoModulo);
    }


    public Collection<AplicacaoModulo> getAplicacoesPorModulo() {
        return aplicacoesPorModulo;
    }

    /**
     * Insere ou atualiza a ajuda da funcionalidade.
     */
    public void manterAjuda() {
        OpcaoAplicacao opcaoAplicacao = getFacade().findOne(getDto().getIdentificacaoOpcaoAplicacao());
        opcaoAplicacao.setAjudaOpcao(getDto().getAjudaOpcao());
        setDto(opcaoAplicacao);
        super.update();
    }

    @Override
    public void update() {
        prepareDto();
        super.update();
    }

    @Override
    public void save() {
        prepareDto();
        super.save();
    }

    private void prepareDto() {
        getResultList().stream().filter(opcaoAplicacao -> opcaoAplicacao
                .getAplicacaoModulo().getIdentificacaoModuloSistema()
                .equals(getDto().getAplicacaoModulo().getIdentificacaoModuloSistema()))
                .findFirst()
                .ifPresent(opcaoAplicacao -> getDto().setAplicacaoModulo(opcaoAplicacao.getAplicacaoModulo()));
    }
}
