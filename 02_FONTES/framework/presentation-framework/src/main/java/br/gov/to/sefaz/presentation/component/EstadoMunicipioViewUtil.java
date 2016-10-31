package br.gov.to.sefaz.presentation.component;

import br.gov.to.sefaz.par.gestao.business.service.EstadoService;
import br.gov.to.sefaz.par.gestao.business.service.MunicipioService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;

import java.util.Collection;

/**
 * Classe que auxilia o controle de estado e município para aa camada de apresentação da aplicação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 26/08/2016 15:27:00
 */
public class EstadoMunicipioViewUtil {

    private Collection<Estado> estados;
    private Collection<Municipio> municipios;
    private final EstadoService estadoService;
    private final MunicipioService municipioService;

    EstadoMunicipioViewUtil(EstadoService estadoService, MunicipioService municipioService) {
        this.estadoService = estadoService;
        this.municipioService = municipioService;
    }

    /**
     * Retorna a coleção dos estados cadastrados na base de dados. Caso a coleção já tenha sido consultada apenas
     * retorna, caso contrário, consulta os estados na base de dados antes de retornar.
     *
     * @return Coleção dos estados cadastrados
     */
    public Collection<Estado> getEstados() {
        if (estados == null) {
            estados = estadoService.findAll();
        }
        return estados;
    }

    /**
     * Retorna a coleção de municipios, caso não tenha nenhum valor sua lista é preenchida conforme o primeiro
     * elemento da lista de {@link #estados}.
     *
     * @return coleção de municípios da UF selecionada
     */
    public Collection<Municipio> getMunicipiosFromFirstUf() {
        if (municipios == null) {
            loadMunicipios(estados.stream().findFirst().orElse(null).getUnidadeFederacao());
        }
        return municipios;
    }

    /**
     * Retorna a coleção de municípios da UF.
     *
     * @return Coleção de municípios da UF selecionada
     */
    public Collection<Municipio> getMunicipios() {
        return municipios;
    }

    /**
     * Carrega a lista de Municípios conforme UF (estado) selecionado.
     *
     * @param uf estado que será utilizado para buscar seus municípios
     */
    public void loadMunicipios(String uf) {
        municipios = municipioService.findByUF(uf);
    }
}
