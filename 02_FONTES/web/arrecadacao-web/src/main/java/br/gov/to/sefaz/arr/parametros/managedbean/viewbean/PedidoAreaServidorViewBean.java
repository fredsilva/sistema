package br.gov.to.sefaz.arr.parametros.managedbean.viewbean;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Objects;

/**
 * View Bean de Servidore x Pedido Servidor.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 23/05/2016 15:12:00
 */
public class PedidoAreaServidorViewBean {

    private String delegaciaDescricao;
    private String delegaciaArea;
    private final PedidoAreasServidores areasServidores;

    public PedidoAreaServidorViewBean(String delegaciaDescricao, String delegaciaArea,
            PedidoAreasServidores areasServidores) {
        this.delegaciaDescricao = delegaciaDescricao;
        this.delegaciaArea = delegaciaArea;
        this.areasServidores = areasServidores;
    }

    public PedidoAreaServidorViewBean(PedidoAreasServidores servidor) {
        this.areasServidores = servidor;
    }

    public Long getIdServidor() {
        return areasServidores.getIdServidor();
    }

    public void setIdServidor(Long idServidor) {
        this.areasServidores.setIdServidor(idServidor);
    }

    public String getDelegaciaDescricao() {
        return delegaciaDescricao;
    }

    public void setDelegaciaDescricao(String delegaciaDescricao) {
        this.delegaciaDescricao = delegaciaDescricao;
    }

    public String getDelegaciaArea() {
        return delegaciaArea;
    }

    public void setDelegaciaArea(String delegaciaArea) {
        this.delegaciaArea = delegaciaArea;
    }

    public String getEmail() {
        return areasServidores.getEmailServidor();
    }

    public void setEmail(String email) {
        this.areasServidores.setEmailServidor(email);
    }

    public String getNomeServidor() {
        return this.areasServidores.getUsuario().getNomeCompletoUsuario();
    }

    public SituacaoEnum getSituacao() {
        return areasServidores.getSituacao();
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.areasServidores.setSituacao(situacao);
    }

    public Boolean getSupervisor() {
        return areasServidores.getSupervisor();
    }

    public PedidoAreasServidores getAreasServidores() {
        return areasServidores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoAreaServidorViewBean that = (PedidoAreaServidorViewBean) o;
        return Objects.equals(delegaciaDescricao, that.delegaciaDescricao)
                && Objects.equals(delegaciaArea, that.delegaciaArea)
                && areasServidores.equals(that.areasServidores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegaciaDescricao, delegaciaArea, areasServidores);
    }

    @Override
    public String toString() {
        return "PedidoAreaServidorViewBean{"
                + ", delegaciaDescricao='" + delegaciaDescricao + '\''
                + ", delegaciaArea='" + delegaciaArea + '\''
                + ", areasServidores=" + areasServidores
                + '}';
    }
}
