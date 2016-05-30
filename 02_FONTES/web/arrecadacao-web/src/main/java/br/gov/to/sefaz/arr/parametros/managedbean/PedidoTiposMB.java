package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.PedidoTiposFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 16:06:00
 */
@ManagedBean(name = "pedidoTiposMB")
@ViewScoped
public class PedidoTiposMB extends DefaultCrudMB<PedidoTipos, Integer> {

    private PedidoTipoAcoes pedidoTipoAcoesDto;
    private PedidoReceita pedidoReceitaDto;
    private PedidoCamposAcoes pedidoCamposAcoesDto;
    private PedidoDocsExigidos pedidoDocsExigidosDto;

    private List<PedidoTipoDocs> allPedidoTipoDoc;
    private List<Receitas> allReceitas;
    private List<ReceitasTaxas> allReceitasTaxas;
    private List<TipoPedidoCampoEnum> tiposPedidoCampos;

    public PedidoTiposMB() {
        super(PedidoTipos::new);
        pedidoTipoAcoesDto = new PedidoTipoAcoes();
        pedidoReceitaDto = new PedidoReceita();
        pedidoCamposAcoesDto = new PedidoCamposAcoes();
        pedidoDocsExigidosDto = new PedidoDocsExigidos();
    }

    public List<TipoPedidoCampoEnum> getTiposPedidoCampos() {
        if (tiposPedidoCampos == null) {
            loadTiposPedidoCampos();
        }
        return tiposPedidoCampos;
    }

    public void loadTiposPedidoCampos() {
        tiposPedidoCampos = getFacade().getTipoPedidoCampoEnumValues(getDto().getIdTipoPedido(),
                pedidoTipoAcoesDto.getTipoAcao());
    }

    @Autowired
    protected void setFacade(PedidoTiposFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PedidoTiposFacade getFacade() {
        return (PedidoTiposFacade) super.getFacade();
    }

    public PedidoTipoAcoes getPedidoTipoAcoesDto() {
        return pedidoTipoAcoesDto;
    }

    public void setPedidoTipoAcoesDto(PedidoTipoAcoes pedidoTipoAcoesDto) {
        this.pedidoTipoAcoesDto = pedidoTipoAcoesDto;
    }

    public PedidoReceita getPedidoReceitaDto() {
        return pedidoReceitaDto;
    }

    public void setPedidoReceitaDto(PedidoReceita pedidoReceitaDto) {
        this.pedidoReceitaDto = pedidoReceitaDto;
    }

    public PedidoCamposAcoes getPedidoCamposAcoesDto() {
        return pedidoCamposAcoesDto;
    }

    public void setPedidoCamposAcoesDto(PedidoCamposAcoes pedidoCamposAcoesDto) {
        this.pedidoCamposAcoesDto = pedidoCamposAcoesDto;
    }

    public PedidoDocsExigidos getPedidoDocsExigidosDto() {
        return pedidoDocsExigidosDto;
    }

    public void setPedidoDocsExigidosDto(PedidoDocsExigidos pedidoDocsExigidosDto) {
        this.pedidoDocsExigidosDto = pedidoDocsExigidosDto;
    }

    public void getPedidoDocsExigidosByIdTipoPedido() {
        Collection<PedidoDocsExigidos> pedidoDocsExigidos = getFacade()
                .getPedidoDocsExigidosByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoDocsExigidos(pedidoDocsExigidos.stream().collect(Collectors.toList()));
    }

    public void getPedidoCamposAcoesByIdTipoPedido() {
        Collection<PedidoCamposAcoes> pedidoDocsExigidos = getFacade()
                .getPedidoCamposAcoesByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoCamposAcoes(pedidoDocsExigidos.stream().collect(Collectors.toList()));
    }

    public void getPedidoReceitasByIdTipoPedido() {
        Collection<PedidoReceita> pedidoReceitas = getFacade()
                .getPedidoReceitaByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoReceitas(pedidoReceitas.stream().collect(Collectors.toList()));
    }

    public List<PedidoTipoDocs> getAllPedidoTipoDoc() {
        if (allPedidoTipoDoc == null) {
            allPedidoTipoDoc = getFacade().getAllPedidoTipoDoc();
        }

        return allPedidoTipoDoc;
    }

    public List<Receitas> getAllReceitas() {
        if (allReceitas == null) {
            allReceitas = getFacade().getAllReceitas();
        }

        return allReceitas;
    }

    public List<ReceitasTaxas> getAllReceitasTaxas() {
        Optional<Integer> idReceita = Optional.ofNullable(pedidoReceitaDto.getIdReceita());

        if (!idReceita.isPresent()) {
            idReceita = getAllReceitas().stream().findFirst()
                    .map(Receitas::getIdReceita);
        }

        if (idReceita.isPresent()) {
            allReceitasTaxas = getFacade().getAllReceitasTaxasFromIdReceita(idReceita.get());
        } else {
            allReceitasTaxas = new ArrayList<>();
        }

        return allReceitasTaxas;
    }

    public String getReceitaLabel(Receitas receitas) {
        return receitas.getIdReceita() + " - " + receitas.getDescricaoReceita();
    }

    public String getReceitaTaxaLabel(ReceitasTaxas taxas) {
        return taxas.getSubcodigo() + " - " + taxas.getDescricao();
    }

    public String getPedidoDocLabel(PedidoTipoDocs pedidoTipoDocs) {
        return pedidoTipoDocs.getIdTipoDocs() + " - " + pedidoTipoDocs.getDescricao();
    }

    public void addPedidoDoc() {
        PedidoDocsExigidos pedidoDocsExigidos = new PedidoDocsExigidos();

        Optional<PedidoTipoDocs> anyPedidoTipoDoc = allPedidoTipoDoc.stream()
                .filter(pedidoTipoDocs -> pedidoTipoDocs.getIdTipoDocs().equals(pedidoDocsExigidosDto.getIdTipoDocs()))
                .findAny();

        if (anyPedidoTipoDoc.isPresent()) {
            pedidoDocsExigidosDto.setPedidoTipoDocs(anyPedidoTipoDoc.get());
        }

        BeanUtils.copyProperties(pedidoDocsExigidosDto, pedidoDocsExigidos);

        getDto().getPedidoDocsExigidos().add(pedidoDocsExigidos);
    }

    public void updatePedidoDoc() {
        List<PedidoDocsExigidos> pedidoDocsExigidos = getDto().getPedidoDocsExigidos();

        Optional<PedidoDocsExigidos> anyPedidoDocsExigidos = pedidoDocsExigidos.stream()
                .filter(pedidoDoc -> pedidoDoc.getId().equals(pedidoDocsExigidosDto.getId())).findAny();
        if (anyPedidoDocsExigidos.isPresent()) {
            pedidoDocsExigidos.remove(anyPedidoDocsExigidos.get());
        }

        addPedidoDoc();
    }

    public void addPedidoReceitas() {
        PedidoReceita pedidoReceita = new PedidoReceita();

        Optional<Receitas> anyReceita = allReceitas.stream()
                .filter(receitas -> receitas.getIdReceita().equals(pedidoReceitaDto.getIdReceita()))
                .findAny();

        if (anyReceita.isPresent()) {
            pedidoReceitaDto.setReceitas(anyReceita.get());
        }

        Optional<ReceitasTaxas> anyReceitasTaxas = allReceitasTaxas.stream()
                .filter(taxas -> taxas.getIdSubcodigo().equals(pedidoReceitaDto.getIdSubcodigo()))
                .findAny();

        if (anyReceitasTaxas.isPresent()) {
            pedidoReceitaDto.setReceitasTaxas(anyReceitasTaxas.get());
        }

        BeanUtils.copyProperties(pedidoReceitaDto, pedidoReceita);

        getDto().getPedidoReceitas().add(pedidoReceita);
    }

    public void updatePedidoReceitas() {
        List<PedidoReceita> pedidoReceitas = getDto().getPedidoReceitas();

        Optional<PedidoReceita> anyPedidoReceita = pedidoReceitas.stream()
                .filter(pedidoReceita -> pedidoReceita.getId().equals(pedidoReceitaDto.getId())).findAny();
        if (anyPedidoReceita.isPresent()) {
            pedidoReceitas.remove(anyPedidoReceita.get());
        }

        addPedidoReceitas();
    }

    public void addPedidoAcao() {
        PedidoTipoAcoes pedidoTipoAcoes = new PedidoTipoAcoes();
        BeanUtils.copyProperties(pedidoTipoAcoesDto, pedidoTipoAcoes);

        PedidoCamposAcoes pedidoCamposAcoes = new PedidoCamposAcoes();
        pedidoCamposAcoesDto.setPedidoTipoAcoes(pedidoTipoAcoes);
        BeanUtils.copyProperties(pedidoCamposAcoesDto, pedidoCamposAcoes);

        getDto().getPedidoCamposAcoes().add(pedidoCamposAcoes);
    }

    public void updatePedidoAcao() {
        List<PedidoCamposAcoes> pedidoCamposAcoes = getDto().getPedidoCamposAcoes();

        Optional<PedidoCamposAcoes> anyPedidoCamposAcoes = pedidoCamposAcoes.stream()
                .filter(pedidoCampo -> pedidoCampo.getTipoCampo().equals(pedidoCamposAcoesDto.getTipoCampo())
                        && pedidoCampo.getTipoAcao().equals(pedidoTipoAcoesDto.getTipoAcao()))
                .findAny();

        if (anyPedidoCamposAcoes.isPresent()) {
            pedidoCamposAcoes.remove(anyPedidoCamposAcoes.get());
        }

        addPedidoAcao();
    }

    public void clearDtos() {
        clearDto();
        pedidoTipoAcoesDto = new PedidoTipoAcoes();
        pedidoReceitaDto = new PedidoReceita();
        pedidoCamposAcoesDto = new PedidoCamposAcoes();
    }
}
