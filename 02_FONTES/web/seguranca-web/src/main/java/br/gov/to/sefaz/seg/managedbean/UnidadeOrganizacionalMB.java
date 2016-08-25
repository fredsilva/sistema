package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.UnidadeOrganizacionalFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUnidade;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da manutenção das Unidades Organizacionais.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 16:39:00
 */
@ManagedBean(name = "unidadeOrganizacionalMB")
@ViewScoped
public class UnidadeOrganizacionalMB extends DefaultCrudMB<UnidadeOrganizacional, Long> {

    private final UnidadeOrganizacionalFilter filter;
    private Collection<UnidadeOrganizacional> allUnidadeOrganizacionais;
    private Collection<TipoUnidade> allTiposUnidades;

    public UnidadeOrganizacionalMB() {
        super(UnidadeOrganizacional::new);
        filter = new UnidadeOrganizacionalFilter();
    }

    public UnidadeOrganizacionalFilter getFilter() {
        return filter;
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachado de UnidadeOrganizacional
     */
    @Autowired
    public void setFacade(UnidadeOrganizacionalFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected UnidadeOrganizacionalFacade getFacade() {
        return (UnidadeOrganizacionalFacade) super.getFacade();
    }

    @Override
    public Collection<UnidadeOrganizacional> getResultList() {
        return resultList;
    }

    /**
     * Filtra as Unidades Organizacionais de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    public Collection<UnidadeOrganizacional> getAllUnidadeOrganizacionais() {
        if (Objects.isNull(allUnidadeOrganizacionais)) {
            allUnidadeOrganizacionais = getFacade().findAll();
        }
        return allUnidadeOrganizacionais;
    }


    /**
     * Carrega todos os Tipos de Unidadees.
     *
     * @return Lista dos tipos de unidades.
     */
    public Collection<TipoUnidade> getAllTiposUnidades() {
        if (Objects.isNull(allTiposUnidades)) {
            allTiposUnidades = getFacade().findTiposUnidades();
        }
        return allTiposUnidades;
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.form.sucesso.operacao");
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.form.sucesso.alterar");
    }

    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.tabela.excluir.sucesso");
    }

    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.tabela.excluir.sucesso");
    }

    @Override
    protected void executeAfterSave(UnidadeOrganizacional unidadeOrganizacional) {
        allUnidadeOrganizacionais.add(unidadeOrganizacional);
    }

    @Override
    protected void executeAfterUpdate(UnidadeOrganizacional unidadeOrganizacional) {
        allUnidadeOrganizacionais.removeIf(e -> e.getId().equals(unidadeOrganizacional.getId()));
        allUnidadeOrganizacionais.add(unidadeOrganizacional);
        for (UnidadeOrganizacional unidade : getResultList()) {
            if (!Objects.isNull(unidade.getUnidOrganizacPai())
                    && unidadeOrganizacional.getId().compareTo(unidade.getUnidOrganizacPai()) == 0) {
                unidade.setUnidOrganizacPai(unidadeOrganizacional.getId());
                unidade.setUnidadeOrganizacionalPai(unidadeOrganizacional);
            }
        }
    }

    @Override
    protected void executeAfterDelete(Long id) {
        allUnidadeOrganizacionais.removeIf(e -> e.getId().equals(id));
    }

}
