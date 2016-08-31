package br.gov.to.sefaz.presentation.component;

import br.gov.to.sefaz.par.gestao.business.service.EstadoService;
import br.gov.to.sefaz.par.gestao.business.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Auxilia a criação de um {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/08/2016 14:17:00
 */
@Component
public class EstadoMunicipioViewFactory {

    private final EstadoService estadoService;
    private final MunicipioService municipioService;

    @Autowired
    public EstadoMunicipioViewFactory(EstadoService estadoService, MunicipioService municipioService) {
        this.estadoService = estadoService;
        this.municipioService = municipioService;
    }

    /**
     * Cria um novo {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil}.
     *
     * @return um novo {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil}
     */
    public EstadoMunicipioViewUtil createEstadoMunicipioViewUtil() {
        return new EstadoMunicipioViewUtil(estadoService, municipioService);
    }
}
