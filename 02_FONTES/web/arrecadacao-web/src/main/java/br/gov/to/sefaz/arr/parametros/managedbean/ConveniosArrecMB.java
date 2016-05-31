package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.ConveniosArrecFacade;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da entidade {@link ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:04:32
 */
@ManagedBean(name = "conveniosArrecMB")
@ViewScoped
public class ConveniosArrecMB extends DefaultCrudMB<ConveniosArrec, Long> {

    private ConveniosTarifas conveniosTarifasDto;
    private Receitas receitasDto;
    private Collection<Receitas> activeReceitas;
    private ConveniosArrecFilter filter;

    @Autowired
    public ConveniosArrecMB() {
        super(ConveniosArrec::new);
        conveniosTarifasDto = new ConveniosTarifas();
        receitasDto = new Receitas();
        filter = new ConveniosArrecFilter();
    }

    @Autowired
    protected void setFacade(ConveniosArrecFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected ConveniosArrecFacade getFacade() {
        return (ConveniosArrecFacade) super.getFacade();
    }

    public ConveniosTarifas getConveniosTarifasDto() {
        return conveniosTarifasDto;
    }

    public void setConveniosTarifasDto(ConveniosTarifas conveniosTarifasDto) {
        this.conveniosTarifasDto = conveniosTarifasDto;
    }

    public Receitas getReceitasDto() {
        return receitasDto;
    }

    public void setReceitasDto(Receitas receitasDto) {
        this.receitasDto = receitasDto;
    }

    public ConveniosArrecFilter getFilter() {
        return filter;
    }

    public void setFilter(ConveniosArrecFilter filter) {
        this.filter = filter;
    }

    public Collection<Bancos> getBancos() {
        return getFacade().getAllActiveBancos();
    }

    public Collection<BancoAgencias> getBancoAgencias() {
        Optional<Integer> idBanco = Optional.ofNullable(getDto().getIdBanco());

        if (!idBanco.isPresent()) {
            idBanco = getBancos().stream().findFirst()
                    .map(Bancos::getIdBanco);
        }

        if (idBanco.isPresent()) {
            return getFacade().getAllActiveBancoAgenciasFromIdBanco(idBanco.get());
        }

        return new ArrayList<>();
    }

    public Collection<Receitas> getReceitas() {
        if (activeReceitas == null) {
            activeReceitas = getFacade().getAllActiveReceitas();
        }
        return activeReceitas;
    }

    public void addReceita() {
        getFacade().validateReceita(getDto(), receitasDto);

        getReceitas().stream()
                .filter(receitas -> receitas.getIdReceita().equals(receitasDto.getIdReceita()))
                .findFirst()
                .ifPresent(receitas -> {
                    Receitas receita = new Receitas();
                    receita.setIdReceita(receitas.getIdReceita());
                    receita.setDescricaoReceita(receitas.getDescricaoReceita());

                    getDto().addReceita(receitas);
                });
    }

    public void deleteReceita() {
        List<Receitas> receitas = getDto().getReceitas();
        List<Receitas> receitasRemove = receitas.stream()
                .filter(conveniosTarifa -> conveniosTarifa.getIdReceita().equals(receitasDto.getIdReceita()))
                .collect(Collectors.toList());

        getDto().getReceitas().removeAll(receitasRemove);
    }

    public String getReceitaLabel(Receitas receitas) {
        return receitas.getIdReceita() + " - " + receitas.getDescricaoReceita();
    }

    public void getConvenioTarifas() {
        Collection<ConveniosTarifas> conveniosTarifas = getFacade()
                .getAllConveniosTarifasByIdConvenioArrec(getDto().getIdConvenio());
        getDto().setConveniosTarifas(conveniosTarifas.stream().collect(Collectors.toList()));
    }

    public void addTarifa() {
        ConveniosTarifas conveniosTarifas = new ConveniosTarifas(conveniosTarifasDto.getIdTarifa(),
                conveniosTarifasDto.getFormaPagamento(), conveniosTarifasDto.getDataInicio(),
                conveniosTarifasDto.getValor());
        conveniosTarifas.setDataFim(conveniosTarifasDto.getDataFim());
        conveniosTarifas.setIdConveniosArrec(getDto().getIdConvenio());

        getFacade().validateTarifa(getDto(), conveniosTarifas);

        getDto().addTarifa(conveniosTarifas);
    }

    public void deleteConvenioTarifa() {
        List<ConveniosTarifas> conveniosTarifas = getDto().getConveniosTarifas();
        List<ConveniosTarifas> conveniosTarifasRemove = conveniosTarifas.stream()
                .filter(conveniosTarifa -> conveniosTarifa.getFormaPagamento()
                        .equals(conveniosTarifasDto.getFormaPagamento())
                        && conveniosTarifa.getDataInicio().equals(conveniosTarifasDto.getDataInicio()))
                .collect(Collectors.toList());

        getDto().getConveniosTarifas().removeAll(conveniosTarifasRemove);
    }

    public void getConvenioReceitas() {
        Collection<Receitas> receitasByIdConvenio = getFacade().getAllReceitasByIdConvenio(getDto().getIdConvenio());
        getDto().setReceitas(receitasByIdConvenio.stream().collect(Collectors.toList()));
    }

    public void search() {
        clearDtos();
        List<ConveniosArrec> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    public void clearDtos() {
        conveniosTarifasDto = new ConveniosTarifas();
        receitasDto = new Receitas();
        clearDto();
    }

}
