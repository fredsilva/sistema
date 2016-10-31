package br.gov.to.sefaz.seg.business.gestao.service.filter;

import java.time.LocalDateTime;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.ModuloSistema}.
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 05/07/2016 16:31:00
 */
public class ModuloSistemaFilter {

    private Long identificacaoModuloSistema;
    private String abreviacaoModulo;
    private String descricaoModuloSistema;
    private String usuarioInclusao;
    private LocalDateTime dataInclusao;

    public Long getIdentificacaoModuloSistema() {
        return identificacaoModuloSistema;
    }

    public void setIdentificacaoModuloSistema(Long identificacaoModuloSistema) {
        this.identificacaoModuloSistema = identificacaoModuloSistema;
    }

    public String getAbreviacaoModulo() {
        return abreviacaoModulo;
    }

    public void setAbreviacaoModulo(String abreviacaoModulo) {
        this.abreviacaoModulo = abreviacaoModulo;
    }

    public String getDescricaoModuloSistema() {
        return descricaoModuloSistema;
    }

    public void setDescricaoModuloSistema(String descricaoModuloSistema) {
        this.descricaoModuloSistema = descricaoModuloSistema;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
