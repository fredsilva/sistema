package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoGruposCnaesFacade;
import br.gov.to.sefaz.arr.parametros.business.service.filter.TipoGruposCnaesFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.cat.persistence.entity.AtividadeEconomica;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de grupos cnae.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 09/05/2016 18:01:00
 */
@ManagedBean(name = "tipoGruposCnaeMB")
@ViewScoped
public class TipoGruposCnaesMB extends DefaultCrudMB<TipoGruposCnaes, Integer> {

    private final TipoGruposCnaesFilter filter;
    private final Collection<AtividadeEconomica> cnaesDto;
    private final Collection<AtividadeEconomica> selectedCnaes;
    private Collection<AtividadeEconomica> allCnaes;
    private String selectedIdCnaeDto;

    public TipoGruposCnaesMB() {
        super(TipoGruposCnaes::new);
        filter = new TipoGruposCnaesFilter();
        cnaesDto = new ArrayList<>();
        selectedCnaes = new ArrayList<>();
    }

    @Autowired
    protected void setFacade(TipoGruposCnaesFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected TipoGruposCnaesFacade getFacade() {
        return (TipoGruposCnaesFacade) super.getFacade();
    }

    public TipoGruposCnaesFilter getFilter() {
        return filter;
    }

    public String getSelectedIdCnaeDto() {
        return selectedIdCnaeDto;
    }

    public void setSelectedIdCnaeDto(String selectedIdCnaeDto) {
        this.selectedIdCnaeDto = selectedIdCnaeDto;
    }

    public Collection<AtividadeEconomica> getCnaesDto() {
        return cnaesDto;
    }

    public Collection<AtividadeEconomica> getSelectedCnaes() {
        return selectedCnaes;
    }

    /**
     * Método para buscar todos os CNAEs cadastrados na base.
     * @return lista de {@link AtividadeEconomica}.
     */
    public Collection<AtividadeEconomica> getAllCnaes() {
        if (allCnaes == null) {
            allCnaes = getFacade().findAllCnaes();
        }

        return allCnaes;
    }

    /**
     * Método para carregar os CNAES selecionados.
     */
    public void loadSelectedCnaes() {
        clearCnaesTables();
        selectedCnaes.addAll(getFacade().findAllCnaesByGrupo(getDto().getIdGrupoCnae()));
    }

    /**
     * Adiciona um CNAE à tela.
     */
    public void addCnaeToDto() {
        getFacade().validateGruposCnaes(buildGruposCnae(selectedIdCnaeDto));

        getCnaesDto().add(getAllCnaes().stream()
                .filter(c -> c.getCodigoCnae().equals(selectedIdCnaeDto))
                .findFirst().get());
    }

    /**
     * Remove um CNAE específico da tela.
     */
    public void removeCnaeFromDto() {
        getCnaesDto().removeIf(c -> c.getCodigoCnae().equals(selectedIdCnaeDto));
    }

    /**
     * Método para remover CNAE do grupo selecionado.
     */
    public void removeCnaeFromGrupo() {
        getFacade().removeCnaeFromGrupo(getDto().getIdGrupoCnae(), selectedIdCnaeDto);
        getSelectedCnaes().removeIf(cf -> cf.getCodigoCnae().equals(selectedIdCnaeDto));
    }

    /**
     * Método para limpar as tabelas da tela.
     */
    public void clearCnaesTables() {
        getDto().getGruposCnae().clear();
        selectedCnaes.clear();
        cnaesDto.clear();
    }

    /**
     * Método para buscar {@link TipoGruposCnaes} de acordo com parâmetros descritos em tela.
     */
    public void search() {
        List<TipoGruposCnaes> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    @Override
    public void save() {
        prepareDto();
        super.save();
    }

    @Override
    public void update() {
        prepareDto();
        super.update();
    }

    private void prepareDto() {
        getDto().getGruposCnae().addAll(buildGruposCnae());
    }

    private List<GruposCnae> buildGruposCnae() {
        return getCnaesDto().stream()
                .map(cf -> new GruposCnae(getDto().getIdGrupoCnae(), cf.getCodigoCnae()))
                .collect(Collectors.toList());
    }

    private Collection<GruposCnae> buildGruposCnae(String codigoCnae) {
        List<GruposCnae> gruposCnaes = buildGruposCnae();
        gruposCnaes.add(new GruposCnae(getDto().getIdGrupoCnae(), codigoCnae));
        return gruposCnaes;
    }
}
