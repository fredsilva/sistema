package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.PedidoTiposFacade;
import br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum;
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
 * ManagedBean da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PedidoTipos}.
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

    /**
     * busca todos os TipoPedidoCampos.
     * @return lista de TipoPedidoCampos.
     */
    public List<TipoPedidoCampoEnum> getTiposPedidoCampos() {
        if (tiposPedidoCampos == null) {
            loadTiposPedidoCampos();
        }
        return tiposPedidoCampos;
    }

    /**
     * Carrega as tabelas da tela de TiposPedidoCampos.
     */
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

    /**
     * Busca os PedidosDocsExigidos pelo IdTipoPedido.
     */
    public void getPedidoDocsExigidosByIdTipoPedido() {
        Collection<PedidoDocsExigidos> pedidoDocsExigidos = getFacade()
                .getPedidoDocsExigidosByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoDocsExigidos(pedidoDocsExigidos.stream().collect(Collectors.toList()));
    }

    /**
     * Busca os PedidoCamposAcoes pelo IdTipoPedido.
     */
    public void getPedidoCamposAcoesByIdTipoPedido() {
        Collection<PedidoCamposAcoes> pedidoDocsExigidos = getFacade()
                .getPedidoCamposAcoesByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoCamposAcoes(pedidoDocsExigidos.stream().collect(Collectors.toList()));
    }

    /**
     * Busca os PedidoReceitas pelo IdTipoPedido.
     */
    public void getPedidoReceitasByIdTipoPedido() {
        Collection<PedidoReceita> pedidoReceitas = getFacade()
                .getPedidoReceitaByIdTipoPedido(getDto().getIdTipoPedido());
        getDto().setPedidoReceitas(pedidoReceitas.stream().collect(Collectors.toList()));
    }

    /**
     * Busca todos PedidoTipoDoc.
     * @return lista de PedidoTipoDocs.
     */
    public List<PedidoTipoDocs> getAllPedidoTipoDoc() {
        if (allPedidoTipoDoc == null) {
            allPedidoTipoDoc = getFacade().getAllPedidoTipoDoc();
        }

        return allPedidoTipoDoc;
    }

    /**
     * Busca todas as Receitas.
     * @return lista de Receitas.
     */
    public List<Receitas> getAllReceitas() {
        if (allReceitas == null) {
            allReceitas = getFacade().getAllReceitas();
        }

        return allReceitas;
    }

    /**
     * Busca todas as ReceitasTaxas.
     * @return lista de ReceitasTaxas.
     */
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

    /**
     * Adiciona um PedidoDoc.
     */
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

    /**
     * Atualiza um PedidoDoc.
     */
    public void updatePedidoDoc() {
        List<PedidoDocsExigidos> pedidoDocsExigidos = getDto().getPedidoDocsExigidos();

        Optional<PedidoDocsExigidos> anyPedidoDocsExigidos = pedidoDocsExigidos.stream()
                .filter(pedidoDoc -> pedidoDoc.getId().equals(pedidoDocsExigidosDto.getId())).findAny();
        if (anyPedidoDocsExigidos.isPresent()) {
            pedidoDocsExigidos.remove(anyPedidoDocsExigidos.get());
        }

        addPedidoDoc();
    }

    /**
     * Adiciona um PedidoReceitas.
     */
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

    /**
     * Atualiza um PedidoReceitas.
     */
    public void updatePedidoReceitas() {
        List<PedidoReceita> pedidoReceitas = getDto().getPedidoReceitas();

        Optional<PedidoReceita> anyPedidoReceita = pedidoReceitas.stream()
                .filter(pedidoReceita -> pedidoReceita.getId().equals(pedidoReceitaDto.getId())).findAny();
        if (anyPedidoReceita.isPresent()) {
            pedidoReceitas.remove(anyPedidoReceita.get());
        }

        addPedidoReceitas();
    }

    /**
     * Adiciona um PedidoAcao.
     */
    public void addPedidoAcao() {
        PedidoTipoAcoes pedidoTipoAcoes = new PedidoTipoAcoes();
        BeanUtils.copyProperties(pedidoTipoAcoesDto, pedidoTipoAcoes);

        PedidoCamposAcoes pedidoCamposAcoes = new PedidoCamposAcoes();
        pedidoCamposAcoesDto.setPedidoTipoAcoes(pedidoTipoAcoes);
        BeanUtils.copyProperties(pedidoCamposAcoesDto, pedidoCamposAcoes);

        getDto().getPedidoCamposAcoes().add(pedidoCamposAcoes);
    }

    /**
     * Atualiza um PedidoAcao.
     */
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

    /**
     * Limpa os DTOs.
     */
    public void clearDtos() {
        clearDto();
        pedidoTipoAcoesDto = new PedidoTipoAcoes();
        pedidoReceitaDto = new PedidoReceita();
        pedidoCamposAcoesDto = new PedidoCamposAcoes();
    }
}
