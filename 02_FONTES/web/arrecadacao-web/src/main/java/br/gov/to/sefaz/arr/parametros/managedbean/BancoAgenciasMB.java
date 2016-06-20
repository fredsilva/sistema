package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.BancoAgenciasFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean responsável por operações de manutenção de agências bancárias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 03/05/2016 11:26:08
 */
@ManagedBean(name = "bancoAgenciasMB")
@ViewScoped
public class BancoAgenciasMB extends DefaultCrudMB<BancoAgencias, BancoAgenciasPK> implements Serializable {

    private static final long serialVersionUID = -2205611188211308061L;

    private Collection<Estado> estados;

    private Collection<Municipio> municipios;

    private Collection<BancoAgencias> agenciasAdicionadas;

    public BancoAgenciasMB() {
        super(BancoAgencias::new);
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachado de BancoAgencias
     */
    @Autowired
    public void setFacade(BancoAgenciasFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected BancoAgenciasFacade getFacade() {
        return (BancoAgenciasFacade) super.getFacade();
    }

    /**
     * Retorna a lista de agências adicionadas.
     *
     * @return Lista de agências adicionadas
     */
    public Collection<BancoAgencias> getAgenciasAdicionadas() {
        if (agenciasAdicionadas == null) {
            agenciasAdicionadas = new ArrayList<>();
        }
        return agenciasAdicionadas;
    }

    /**
     * Retorna a lista das agências carregadas no método loadBancoAgencias.
     */
    @Override
    public Collection<BancoAgencias> getResultList() {
        if (super.getResultList() == null) {
            loadBancoAgencias();
        }
        return super.getResultList();
    }

    /**
     * Carrega as agências do banco {@link BancoAgencias#idBanco} selecinado em tela.
     */
    public void loadBancoAgencias() {
        if (getDto().getIdBanco() == null) {
            setResultList(Collections.emptyList());
        } else {
            setResultList(getFacade().findByIdBanco(getDto().getIdBanco()));
        }
    }

    /**
     * Persiste a lista de agenciasAdicionadas na base de dados.
     */
    public void saveAgenciasAdicionadas() {
        getFacade().save(agenciasAdicionadas);
        loadBancoAgencias();
        agenciasAdicionadas = new ArrayList<>();
        clearDto();
        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");
    }

    /**
     * Adiciona uma agência na lista de agenciasAdicionadas conforme informações informadas em view.
     */
    @Override
    public void save() {
        getFacade().validateSave(getDto(), buildBancoAgencias());
        atualizaMunicipio();
        agenciasAdicionadas.add(getDto());
        clearDto();
    }

    /**
     * Atualizada uma agência na lista de agenciasAdicionadas, conforme informações informadas em view.
     */
    @Override
    public void update() {
        getFacade().validateUpdate(getDto(), buildBancoAgencias());
        removerBancoAgencias();
        atualizaMunicipio();
        agenciasAdicionadas.add(getDto());
        clearDto();
    }

    /**
     * Remove uma agência da lista de agenciasAdicionadas.
     */
    public void removerBancoAgencias() {
        agenciasAdicionadas.removeIf(ag -> ag.getIdBanco().equals(getDto().getIdBanco())
                && ag.getIdAgencia().equals(getDto().getIdAgencia()));
    }

    /**
     * Retorna a coleção dos estados cadastrados na base de dados. Caso a coleção já tenha sido consultada apenas
     * retorna, caso contrário, consulta os estados na base de dados antes de retornar.
     *
     * @return Coleção dos estados cadastrados
     */
    public Collection<Estado> getEstados() {
        if (estados == null) {
            estados = getFacade().findAllEstados();
        }
        return estados;
    }

    /**
     * Retorna a coleção de municípios da UF.
     *
     * @return Coleção de munípios da UF selecionada
     */
    public Collection<Municipio> getMunicipios() {
        if (municipios == null) {
            loadMunicipios();
        }
        return municipios;
    }

    /**
     * Carrega a lista de Municípios conforme UF (estado) selecionado.
     *
     */
    public void loadMunicipios() {

        Optional<String> uf = Optional.ofNullable(getDto().getUnidadeFederacao());

        if (!uf.isPresent()) {
            uf = getEstados().stream().findFirst().map(e -> e.getUnidadeFederacao());
        }

        if (uf.isPresent()) {
            municipios = getFacade().findMunicipiosByUF(uf.get());
        }

    }

    private List<BancoAgencias> buildBancoAgencias() {
        List<BancoAgencias> list = agenciasAdicionadas.stream().collect(Collectors.toList());
        list.add(getDto());
        return list;
    }

    @Override
    public void clearDto() {
        Integer idBanco = null;
        if (getDto() != null) {
            idBanco = getDto().getIdBanco();
        }
        super.clearDto();
        getDto().setIdBanco(idBanco);
    }

    private void atualizaMunicipio() {
        Municipio municipio = municipios.stream().filter(m -> m.getCodigoIbge().equals(getDto().getIdMunicipio()))
                .findFirst().get();
        getDto().setMunicipio(municipio);
    }

}
