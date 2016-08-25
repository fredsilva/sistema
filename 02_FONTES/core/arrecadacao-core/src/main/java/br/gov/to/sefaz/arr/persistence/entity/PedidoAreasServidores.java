package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_AREAS_SERVIDORES.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
@Entity
@Table(name = "TA_PEDIDO_AREAS_SERVIDORES", schema = "SEFAZ_ARR")
@IdClass(PedidoAreasServidoresPK.class)
public class PedidoAreasServidores extends AbstractEntity<PedidoAreasServidoresPK> {

    private static final Long serialVersionUID = 1515515974786766005L;

    @Id
    @NotNull
    @Column(name = "ID_PEDIDO_AREA")
    private Integer idPedidoArea;

    @Id
    @NotNull(message = "#{arr_msg['parametros.pedidoAreasServidores.idServidor.obrigatorio']}")
    @Column(name = "ID_SERVIDOR")
    private Long idServidor;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreasServidores.emailServidor.obrigatorio']}")
    @Column(name = "EMAIL_SERVIDOR")
    private String emailServidor;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreasServidores.situacao.obrigatorio']}")
    @Column(name = "SITUACAO")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @Column(name = "SUPERVISOR")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    private Boolean supervisor;

    @JoinColumn(name = "ID_SERVIDOR", referencedColumnName = "CPF_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuarioSistema usuario;

    public PedidoAreasServidores() {
        usuario = new UsuarioSistema();
        // Construtor para inicialização por reflexão.
    }

    public PedidoAreasServidores(
            Integer idPedidoArea, Long idServidor, String emailServidor, SituacaoEnum situacao,
            Boolean supervisor, UsuarioSistema usuario) {
        this.idPedidoArea = idPedidoArea;
        this.idServidor = idServidor;
        this.emailServidor = emailServidor;
        this.situacao = situacao;
        this.supervisor = supervisor;
        this.usuario = usuario;
    }

    @Override
    public PedidoAreasServidoresPK getId() {
        return new PedidoAreasServidoresPK(getIdPedidoArea(), getIdServidor());
    }

    public Integer getIdPedidoArea() {
        return idPedidoArea;
    }

    public void setIdPedidoArea(Integer idPedidoArea) {
        this.idPedidoArea = idPedidoArea;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    /**
     * Altera a identificação do servidor.
     * @param idServidor novo.
     */
    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
        this.usuario.setCpfUsuario(String.valueOf(idServidor));
    }

    public String getEmailServidor() {
        return emailServidor;
    }

    public void setEmailServidor(String emailServidor) {
        this.emailServidor = emailServidor;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Boolean getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Boolean supervisor) {
        this.supervisor = supervisor;
    }

    public UsuarioSistema getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSistema usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoAreasServidores that = (PedidoAreasServidores) o;
        return Objects.equals(idPedidoArea, that.idPedidoArea)
                && Objects.equals(idServidor, that.idServidor)
                && Objects.equals(emailServidor, that.emailServidor)
                && situacao == that.situacao
                && Objects.equals(supervisor, that.supervisor)
                && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoArea, idServidor, emailServidor, situacao, supervisor, usuario);
    }

    @Override
    public String toString() {
        return "PedidoAreasServidores{"
                + "idPedidoArea=" + idPedidoArea
                + ", idServidor=" + idServidor
                + ", emailServidor='" + emailServidor + '\''
                + ", situacao=" + situacao
                + ", supervisor=" + supervisor
                + ", usuario=" + usuario
                + '}';
    }
}
