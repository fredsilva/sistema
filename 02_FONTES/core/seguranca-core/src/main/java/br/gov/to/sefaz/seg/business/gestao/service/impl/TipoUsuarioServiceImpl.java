package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.par.gestao.business.service.ParametroGeralService;
import br.gov.to.sefaz.seg.business.gestao.converter.TipoUsuarioConverter;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementação do serviço da entidade TipoUsuario.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private static final String LISTAGEM_TIPO_USUARIO = "LISTAGEM_TIPO_USUARIO";

    private final ParametroGeralService parametroGeralService;

    private final UsuarioSistemaService usuarioSistemaService;

    @Autowired
    public TipoUsuarioServiceImpl(ParametroGeralService parametroGeralService, UsuarioSistemaService
            usuarioSistemaService) {
        this.parametroGeralService = parametroGeralService;
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @Override
    public List<TipoUsuario> findByFilter(TipoUsuarioFilter filter) {
        List<TipoUsuario> list = findAll();
        list.forEach( tipoUsuario -> tipoUsuario.setQuantidadeUsuarios(usuarioSistemaService.countByTipoUsuario(
                tipoUsuario)));
        if (Objects.isNull(filter) || StringUtils.isEmpty(filter.getDescricaoTipoUsuario()) ) {
            return list;
        }
        return list.stream().filter(tipoUsuario -> tipoUsuario.getDescricaoTipoUsuario().toLowerCase()
                .contains(filter.getDescricaoTipoUsuario().toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<TipoUsuario> findAll() {
        return parametroGeralService.findCodeData(new TipoUsuarioConverter(), LISTAGEM_TIPO_USUARIO);
    }

    @Override
    public List<TipoUsuario> findAllCountUsers() {
        List<TipoUsuario> list = findAll();
        list.forEach( tipoUsuario -> tipoUsuario.setQuantidadeUsuarios(usuarioSistemaService.countByTipoUsuario(
                tipoUsuario)));
        return list;
    }

}