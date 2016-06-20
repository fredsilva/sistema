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

    /**
     * <p>Retorna uma coleção de {@link BancoAgencias} baseado em um ID de banco.</p>
     * <ol>
     *     <li>Se tiver um id banco setado no {@link #getDto()} as agencias serão carregadas a partir dele.</li>
     *     <li>Se não, se tiver ao menos um banco na lista de bancos {@link #getBancos()} carrega baseado no primeiro
     *         da banco da lista</li>
     *     <li>Se não retorna uma lista vazia</li>
     * </ol>
     *
     * @return uma lista de agencias de um banco
     */
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

    /**
     * Retorna de maneira lazy todas as receitas ativas.
     *
     * @return receitas ativas
     */
    public Collection<Receitas> getReceitas() {
        if (activeReceitas == null) {
            activeReceitas = getFacade().getAllActiveReceitas();
        }
        return activeReceitas;
    }

    /**
     * Valida e adiciona {@link #receitasDto} ao {@link #getDto()}.
     */
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

    /**
     * Remove uma {@link #receitasDto} do {@link #getDto()} baseado no id da receita.
     */
    public void deleteReceita() {
        List<Receitas> receitas = getDto().getReceitas();
        List<Receitas> receitasRemove = receitas.stream()
                .filter(conveniosTarifa -> conveniosTarifa.getIdReceita().equals(receitasDto.getIdReceita()))
                .collect(Collectors.toList());

        getDto().getReceitas().removeAll(receitasRemove);
    }

    /**
     * Retorna o label da receita ("codigo" - "nome").
     *
     * @param receitas entidade receita.
     * @return label da receita
     */
    public String getReceitaLabel(Receitas receitas) {
        return receitas.getIdReceita() + " - " + receitas.getDescricaoReceita();
    }

    /**
     * Carrega todos os convenios tarifas ao {@link #getDto()} baseado no id do convenio.
     */
    public void getConvenioTarifas() {
        Collection<ConveniosTarifas> conveniosTarifas = getFacade()
                .getAllConveniosTarifasByIdConvenioArrec(getDto().getIdConvenio());
        getDto().setConveniosTarifas(conveniosTarifas.stream().collect(Collectors.toList()));
    }

    /**
     * Valida e adiciona uma {@link #conveniosTarifasDto} ao {@link #getDto()}.
     */
    public void addTarifa() {
        ConveniosTarifas conveniosTarifas = new ConveniosTarifas(conveniosTarifasDto.getIdTarifa(),
                conveniosTarifasDto.getFormaPagamento(), conveniosTarifasDto.getDataInicio(),
                conveniosTarifasDto.getValor());
        conveniosTarifas.setDataFim(conveniosTarifasDto.getDataFim());
        conveniosTarifas.setIdConveniosArrec(getDto().getIdConvenio());

        getFacade().validateTarifa(getDto(), conveniosTarifas);

        getDto().addTarifa(conveniosTarifas);
    }

    /**
     * Remove um convenio tarifa do {@link #getDto()} dado a forma de pagamento e a data de inicio do
     * {@link #conveniosTarifasDto}.
     */
    public void deleteConvenioTarifa() {
        List<ConveniosTarifas> conveniosTarifas = getDto().getConveniosTarifas();
        List<ConveniosTarifas> conveniosTarifasRemove = conveniosTarifas.stream()
                .filter(conveniosTarifa -> conveniosTarifa.getFormaPagamento()
                        .equals(conveniosTarifasDto.getFormaPagamento())
                        && conveniosTarifa.getDataInicio().equals(conveniosTarifasDto.getDataInicio()))
                .collect(Collectors.toList());

        getDto().getConveniosTarifas().removeAll(conveniosTarifasRemove);
    }

    /**
     * Carrega as receitas no {@link #getDto()} baseadas no id do convenio do {@link #getDto()}.
     */
    public void getConvenioReceitas() {
        Collection<Receitas> receitasByIdConvenio = getFacade().getAllReceitasByIdConvenio(getDto().getIdConvenio());
        getDto().setReceitas(receitasByIdConvenio.stream().collect(Collectors.toList()));
    }

    /**
     * Busca uma lista de {@link ConveniosArrec} e carrega no {@link #setResultList(Collection)} baseado no
     * {@link #filter}.
     */
    public void search() {
        clearDtos();
        List<ConveniosArrec> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMesage(MessageUtil.ARR, "parametros.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Limpa os registros dos dtos.
     */
    public void clearDtos() {
        conveniosTarifasDto = new ConveniosTarifas();
        receitasDto = new Receitas();
        clearDto();
    }

}
