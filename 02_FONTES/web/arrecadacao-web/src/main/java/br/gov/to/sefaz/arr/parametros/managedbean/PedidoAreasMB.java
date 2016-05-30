package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.PedidoAreasFacade;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.PedidoAreaServidorViewBean;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Delegacias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean de Parametrizar Tipos de Pedidos de Áreas.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 16:01:00
 */
@ManagedBean(name = "pedidoAreasMB")
@ViewScoped
public class PedidoAreasMB extends DefaultCrudMB<PedidoAreas, Integer> {

    private Collection<PedidoTipos> allPedidoTipos;
    private Collection<Delegacias> allDelegacias;
    private Collection<DelegaciaAgencias> delegaciaAgencias;
    private final Collection<PedidoAreaServidorViewBean> pedidoAreaServidores;
    private final Collection<UsuarioSistema> usuariosSistema;
    private final PedidoAreasServidores servidorDto;
    private Long selectedIdServidorDto;
    private Long idServidorSearchDto;
    private String nomeServidorSearchDto;

    public PedidoAreasMB() {
        super(PedidoAreas::new);
        servidorDto = new PedidoAreasServidores();
        pedidoAreaServidores = new ArrayList<>();
        this.usuariosSistema = new ArrayList<>();
    }

    @Autowired
    protected void setFacade(PedidoAreasFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PedidoAreasFacade getFacade() {
        return (PedidoAreasFacade) super.getFacade();
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
        getDto().getPedidoAreasServidores().clear();
        getDto().getPedidoAreasServidores()
                .addAll(buildServidoresList());

        String delegaciaDescricao = getAllDelegacias().stream()
                .filter(d -> d.getIdDelegacia().equals(getDto().getIdDelegacia()))
                .findFirst().map(Delegacias::getDescricao).orElse("");
        String delegaciaAreaDescricao = getDelegaciaAgencias().stream()
                .filter(d -> d.getIdUnidadeDelegacia().equals(getDto().getIdUnidadeDelegacia()))
                .findFirst().map(DelegaciaAgencias::getDescricao).orElse("");

        getDto().getDelegaciaAgencias().setDescricao(delegaciaAreaDescricao);
        getDto().getDelegaciaAgencias().getDelegacias().setDescricao(delegaciaDescricao);
    }

    public String getSelectedTipoDescriprion() {
        return getAllPedidoTipos().stream()
                .filter(pt -> pt.getIdTipoPedido().equals(getDto().getIdTipoPedido()))
                .findFirst()
                .map(PedidoTipos::getDescricao)
                .orElse("");
    }

    public PedidoAreasServidores getServidorDto() {
        return servidorDto;
    }

    public Long getSelectedIdServidorDto() {
        return selectedIdServidorDto;
    }

    public void setSelectedIdServidorDto(Long selectedIdServidorDto) {
        this.selectedIdServidorDto = selectedIdServidorDto;
    }

    public Collection<PedidoTipos> getAllPedidoTipos() {
        if (allPedidoTipos == null) {
            allPedidoTipos = getFacade().findAllPedidoTipos();
        }

        return allPedidoTipos;
    }

    public void loadAreasCadastradas() {
        if (Objects.isNull(getDto().getIdTipoPedido())) {
            setResultList(new ArrayList<>());
        } else {
            setResultList(getFacade().findAllByTipo(getDto().getIdTipoPedido()));
        }
    }

    @Override
    public Collection<PedidoAreas> getResultList() {
        if (resultList == null) {
            loadAreasCadastradas();
        }

        return resultList;
    }

    public Collection<Delegacias> getAllDelegacias() {
        if (allDelegacias == null) {
            allDelegacias = getFacade().findAllDelegacias();
        }

        return allDelegacias;
    }

    public void loadDelegaciaAgencias() {
        Optional<Integer> idDelegacia = Optional.ofNullable(getDto().getIdDelegacia());

        if (!idDelegacia.isPresent()) {
            idDelegacia = getAllDelegacias().stream().findFirst()
                    .map(Delegacias::getIdDelegacia);
        }

        if (idDelegacia.isPresent()) {
            delegaciaAgencias = getFacade().findAgenciasByDelegacia(idDelegacia.get());

            if (delegaciaAgencias.stream().noneMatch(da -> da.getIdUnidadeDelegacia()
                    .equals(getDto().getIdUnidadeDelegacia()))) {
                Optional<DelegaciaAgencias> idUnidadeDelegacia = delegaciaAgencias.stream().findFirst();
                idUnidadeDelegacia.ifPresent(ud -> getDto().setIdUnidadeDelegacia(ud.getIdUnidadeDelegacia()));
            }
        } else {
            delegaciaAgencias = new ArrayList<>();
        }

        updateDelegaciasFromServidores();
    }

    public Collection<DelegaciaAgencias> getDelegaciaAgencias() {
        if (delegaciaAgencias == null) {
            loadDelegaciaAgencias();
        }

        return delegaciaAgencias;
    }

    public Collection<PedidoAreaServidorViewBean> getServidoresViewBean() {
        return pedidoAreaServidores;
    }

    public void loadServidoresTable() {
        if (!Objects.isNull(getDto().getIdPedidoArea())) {
            getServidoresViewBean().clear();
            getFacade().findAllServidoresByPedido(getDto().getIdPedidoArea())
                    .forEach(this::addServidor);
        }
    }

    public void addServidor() {
        PedidoAreasServidores clone = cloneServidor();
        List<PedidoAreasServidores> listToValidate = buildServidoresList();
        listToValidate.add(clone);

        getFacade().validateDuplicatedServidor(listToValidate);
        getFacade().validateServidorChefe(listToValidate);

        addServidor(clone);
    }

    public void updateServidor() {
        PedidoAreasServidores clone = cloneServidor();
        List<PedidoAreasServidores> listToValidate = buildServidoresList();
        listToValidate
                .removeIf(s -> s.getIdServidor().equals(clone.getIdServidor()));
        listToValidate.add(clone);

        getFacade().validateServidorChefe(listToValidate);

        updateServidor(clone);
    }

    public void updateServidor(PedidoAreasServidores servidor) {
        getServidoresViewBean()
                .removeIf(pa -> pa.getIdServidor().equals(servidor.getIdServidor()));

        addServidor(servidor);
    }

    public void addServidor(PedidoAreasServidores servidor) {
        PedidoAreaServidorViewBean viewBean = new PedidoAreaServidorViewBean(servidor);
        getServidoresViewBean().add(viewBean);

        updateDelegaciasFromServidores();
    }

    public void updateDelegaciasFromServidores() {
        String delegaciaDescricao = getAllDelegacias().stream()
                .filter(d -> d.getIdDelegacia().equals(getDto().getIdDelegacia()))
                .findFirst().map(Delegacias::getDescricao).orElse("");
        String delegaciaArea = getDelegaciaAgencias().stream()
                .filter(d -> d.getIdUnidadeDelegacia().equals(getDto().getIdUnidadeDelegacia()))
                .findFirst().map(DelegaciaAgencias::getDescricao).orElse("");

        getServidoresViewBean().forEach(vb -> {
            vb.setDelegaciaDescricao(delegaciaDescricao);
            vb.setDelegaciaArea(delegaciaArea);
        });
    }

    public void removePedidoServidor() {
        Optional<PedidoAreasServidores> seervidor = getFacade()
                .removeServidor(getDto().getIdPedidoArea(), getSelectedIdServidorDto());

        if (seervidor.isPresent()) {
            updateServidor(seervidor.get());
        } else {
            getServidoresViewBean()
                    .removeIf(pa -> pa.getIdServidor().equals(getSelectedIdServidorDto()));
        }
    }

    public void clearPedidoTables() {
        getServidoresViewBean().clear();
        getDto().getPedidoAreasServidores().clear();
    }

    public Collection<UsuarioSistema> getUsuariosSistema() {
        return usuariosSistema;
    }

    public Long getIdServidorSearchDto() {
        return idServidorSearchDto;
    }

    public String getNomeServidorSearchDto() {
        return nomeServidorSearchDto;
    }

    public void setIdServidorSearchDto(Long idServidorSearchDto) {
        this.idServidorSearchDto = idServidorSearchDto;
    }

    public void setNomeServidorSearchDto(String nomeServidorSearchDto) {
        this.nomeServidorSearchDto = nomeServidorSearchDto;
    }

    public void searchServidor() {
        usuariosSistema.clear();
        usuariosSistema.addAll(getFacade()
                .searchServidor(getIdServidorSearchDto(), getNomeServidorSearchDto()));
    }

    private List<PedidoAreasServidores> buildServidoresList() {
        return getServidoresViewBean()
                .stream().map(PedidoAreaServidorViewBean::getAreasServidores)
                .collect(Collectors.toList());
    }

    private PedidoAreasServidores cloneServidor() {
        PedidoAreasServidores clone = new PedidoAreasServidores();
        BeanUtils.copyProperties(getServidorDto(), clone, "usuario");
        BeanUtils.copyProperties(getServidorDto().getUsuario(), clone.getUsuario());

        return clone;
    }
}
