package br.gov.to.sefaz.seg.domain;

import java.util.List;

/**
 * Representa um usuário.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class Usuario {
    private String nome;
    private List<Mensagem> mensagens;
    private List<Alerta> alertas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
}
