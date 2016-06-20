package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.UnidadeOrganizacionalFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    /**
     * Filtra as Unidades Organizacionais de acordo com os dados informados em tela.
     */
    public void search() {
        List<UnidadeOrganizacional> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMesage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    @Override
    public Collection<UnidadeOrganizacional> getResultList() {
        return resultList;
    }

    public Long getUnidOrganizacPai() {
        return getDto().getUnidOrganizacPai();
    }

    /**
     * Seta o objeto {@link UnidadeOrganizacional#unidadeOrganizacionalPai} para utilização das combos da tela.
     *
     * @param unidOrganizacPai identificação da Unidade Pai.
     */
    public void setUnidOrganizacPai(Long unidOrganizacPai) {
        allUnidadeOrganizacionais.stream()
                .filter(uo -> uo.getId().equals(unidOrganizacPai))
                .findFirst()
                .ifPresent(unidadeOrganizacional -> {
                    getDto().setUnidOrganizacPai(unidOrganizacPai);
                    getDto().setUnidadeOrganizacionalPai(unidadeOrganizacional);
                });
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    public Collection<UnidadeOrganizacional> getAllUnidadeOrganizacionais() {
        if (allUnidadeOrganizacionais == null) {
            loadAllUnidadeOrganizacionais();
        }
        return allUnidadeOrganizacionais;
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados.
     */
    public void loadAllUnidadeOrganizacionais() {
        allUnidadeOrganizacionais = getFacade().findAll();
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMesage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.form.sucesso.operacao");
    }

    @Override
    public void delete() {
        Optional<UnidadeOrganizacional> delete = getFacade().delete(getDto().getId());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));

        if (delete.isPresent()) {
            showDeleteMessage();
            getResultList().add(delete.get());
        } else {
            showDeleteMessage();
        }

        clearDto();
    }

    /**
     * Mostra a mensagem de deleção específica para Unidades Organizacionais.
     */
    public void showDeleteMessage() {
        MessageUtil.addMesage(MessageUtil.SEG, "seg.gestao.unidadesOrgazinacionais.tabela.excluir.sucesso");
    }
}
