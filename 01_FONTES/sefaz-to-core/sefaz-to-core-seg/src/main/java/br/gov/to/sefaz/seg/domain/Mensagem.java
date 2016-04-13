package br.gov.to.sefaz.seg.domain;

import java.util.Date;

/**
 * Mensagem direcionada a um usuário.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class Mensagem {
    private Date data;
    private String texto;

    public Mensagem(Date data, String texto) {
        this.data = data;
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
