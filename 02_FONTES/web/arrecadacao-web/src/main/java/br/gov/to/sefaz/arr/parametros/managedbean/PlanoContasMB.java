package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.PlanoContasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos bancos de arrecadação.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@ManagedBean(name = "planoContasMB")
@ViewScoped
public class PlanoContasMB extends DefaultCrudMB<PlanoContas, Long> {

    private final PlanoContasFilter filter;
    private Collection<TipoGruposCnaes> tipoGruposCnaesCollection;

    @Autowired
    public PlanoContasMB() {
        super(PlanoContas::new);
        filter = new PlanoContasFilter();
    }

    @Autowired
    protected void setFacade(PlanoContasFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PlanoContasFacade getFacade() {
        return (PlanoContasFacade) super.getFacade();
    }

    public PlanoContasFilter getFilter() {
        return filter;
    }

    /**
     * Busca os plano contas de acordo com o filtro preenchido em tela.
     */
    public void search() {
        List<PlanoContas> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMessage("mensagem.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Busca lista de {@link br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes} com o status
     * {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO}.
     * @return Lista de {@link TipoGruposCnaes}.
     */
    public Collection<TipoGruposCnaes> getTipoGruposCnaesCollection() {

        if (Objects.isNull(tipoGruposCnaesCollection)) {
            tipoGruposCnaesCollection = getFacade().findAllActiveTipoGruposCnaes();
        }

        return tipoGruposCnaesCollection;
    }

    public void setTipoGruposCnaesCollection(Collection<TipoGruposCnaes> tipoGruposCnaesCollection) {
        this.tipoGruposCnaesCollection = tipoGruposCnaesCollection;
    }
}
