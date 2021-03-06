package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.ReceitasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ReceitasFilter;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:33:00
 */
@ManagedBean(name = "receitasMB")
@ViewScoped
public class ReceitasMB extends DefaultCrudMB<Receitas, Integer> {

    private ReceitasFilter filter;
    private ReceitasTaxas receitasTaxasDto;
    private ReceitasRepasse receitasRepasseDto;

    @Autowired
    public ReceitasMB() {
        super(Receitas::new);
        filter = new ReceitasFilter();
        receitasTaxasDto = new ReceitasTaxas();
        receitasRepasseDto = new ReceitasRepasse();
    }

    @Autowired
    protected void setFacade(ReceitasFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected ReceitasFacade getFacade() {
        return (ReceitasFacade) super.getFacade();
    }

    public ReceitasFilter getFilter() {
        return filter;
    }

    public void setFilter(ReceitasFilter filter) {
        this.filter = filter;
    }

    public ReceitasTaxas getReceitasTaxasDto() {
        return receitasTaxasDto;
    }

    public void setReceitasTaxasDto(ReceitasTaxas receitasTaxasDto) {
        this.receitasTaxasDto = receitasTaxasDto;
    }

    public ReceitasRepasse getReceitasRepasseDto() {
        return receitasRepasseDto;
    }

    public void setReceitasRepasseDto(ReceitasRepasse receitasRepasseDto) {
        this.receitasRepasseDto = receitasRepasseDto;
    }

    /**
     * Busca utilizando o filtro preenchido em tela.
     */
    public void search() {
        clearDtos();
        List<Receitas> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMessage("mensagem.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Limpa os DTOs.
     */
    public void clearDtos() {
        clearDto();
        receitasTaxasDto = new ReceitasTaxas();
        receitasRepasseDto = new ReceitasRepasse();
    }

    public List<PlanoContas> getAllPlanoContas() {
        return getFacade().getAllPlanoContas();
    }

    /**
     * Busca os ReceitasTaxas e adiciona na view.
     */
    public void getReceitasTaxas() {
        Collection<ReceitasTaxas> conveniosTarifas = getFacade()
                .getReceitasTaxasByIdReceita(getDto().getIdReceita());
        getDto().setReceitasTaxas(conveniosTarifas.stream().collect(Collectors.toList()));
    }

    /**
     * Adiciona Taxa na view.
     */
    public void addTaxa() {
        ReceitasTaxas receitasTaxas = new ReceitasTaxas();
        BeanUtils.copyProperties(receitasTaxasDto, receitasTaxas);

        getDto().addTaxa(receitasTaxas);
    }

    /**
     * Atualiza taxa.
     */
    public void updateTaxa() {
        List<ReceitasTaxas> receitasTaxas = getDto().getReceitasTaxas();

        Optional<ReceitasTaxas> anyTaxa = receitasTaxas.stream()
                .filter(taxas -> taxas.getId().equals(receitasTaxasDto.getId()))
                .findAny();
        if (anyTaxa.isPresent()) {
            receitasTaxas.remove(anyTaxa.get());
            receitasTaxas.add(receitasTaxasDto);
        } else {
            addTaxa();
        }
    }

    /**
     * Remove Taxa.
     */
    public void deleteTaxa() {
        List<ReceitasTaxas> taxas = getDto().getReceitasTaxas();
        List<ReceitasTaxas> taxasRemove = taxas.stream()
                .filter(taxa -> taxa.getSubcodigo().equals(receitasTaxasDto.getSubcodigo()))
                .collect(Collectors.toList());

        getDto().getReceitasTaxas().removeAll(taxasRemove);
    }

    /**
     * Busca as ReceitasRepasses e adiciona-os na view.
     */
    public void getReceitasRepasses() {
        Collection<ReceitasRepasse> receitasRepasses = getFacade()
                .getReceitasRepasseByIdReceita(getDto().getIdReceita());
        getDto().setReceitasRepasse(receitasRepasses.stream().collect(Collectors.toList()));
    }

    /**
     * Adiciona um Repasse na view.
     */
    public void addRepasse() {
        ReceitasRepasse receitasRepasse = new ReceitasRepasse();
        BeanUtils.copyProperties(receitasRepasseDto, receitasRepasse);
        receitasRepasse.setIdReceita(getDto().getId());
        getFacade().validateReceitasRepasse(receitasRepasse);
        getDto().getReceitasRepasse().add(receitasRepasse);
    }

    /**
     * Atualiza um Repasse.
     */
    public void updateRepasse() {
        List<ReceitasRepasse> receitasRepasse = getDto().getReceitasRepasse();

        Optional<ReceitasRepasse> anyRepasse = receitasRepasse.stream()
                .filter(repasse -> repasse.getTipoRepasse().equals(receitasRepasseDto.getTipoRepasse())
                        && repasse.getDataInicio().equals(receitasRepasseDto.getDataInicio()))
                .findAny();
        if (anyRepasse.isPresent()) {
            receitasRepasseDto.setIdReceita(getDto().getId());
            getFacade().validateReceitasRepasse(receitasRepasseDto);
            receitasRepasse.remove(anyRepasse.get());
            receitasRepasse.add(receitasRepasseDto);
        } else {
            addRepasse();
        }
    }

    /**
     * Remove um Repasse.
     */
    public void deleteRepasse() {
        List<ReceitasRepasse> repasses = getDto().getReceitasRepasse();
        List<ReceitasRepasse> repassesRemove = repasses.stream()
                .filter(repasse -> repasse.getTipoRepasse().equals(receitasRepasseDto.getTipoRepasse())
                        && repasse.getDataInicio().equals(receitasRepasseDto.getDataInicio()))
                .collect(Collectors.toList());

        getDto().getReceitasRepasse().removeAll(repassesRemove);
    }

}
