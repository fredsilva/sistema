package br.gov.to.sefaz.seg.controller;

import br.gov.to.sefaz.seg.domain.Alerta;
import br.gov.to.sefaz.seg.domain.Mensagem;
import br.gov.to.sefaz.seg.domain.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * ManageBean do template do projeto.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
@ManagedBean(name = "templateController")
@SessionScoped
public class TemplateController {
    private Usuario usuario;

    /**
     * Construtor padrão da classe.
     */
    public TemplateController() {
        usuario = new Usuario();
        usuario.setNome("Dude");
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        mensagens.add(new Mensagem(new Date(), "mensagem1"));
        mensagens.add(new Mensagem(new Date(), "mensagem2"));
        usuario.setMensagens(mensagens);

        List<Alerta> alertas = new ArrayList<Alerta>();
        alertas.add(new Alerta("alerta1"));
        alertas.add(new Alerta("alerta2", Alerta.Nivel.Danger));
        alertas.add(new Alerta("alerta3", Alerta.Nivel.Success));
        alertas.add(new Alerta("alerta4", Alerta.Nivel.Warning));

        usuario.setAlertas(alertas);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
