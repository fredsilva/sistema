package br.gov.to.sefaz.seg.domain;

/**
 * Alerta direcionado a um usuário.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class Alerta {
    private String texto;
    private Nivel nivel;

    /**
     * Nivel do alerta.
     *
     * @author roger.gouveia@ntconsult.com.br
     */
    public enum Nivel {
        Default("label-default"), Primary("label-primary"), Success("label-success"), Info("label-info"), Warning(
                "label-warning"), Danger("label-danger");

        private String styleClass;

        private Nivel(String styleClass) {
            this.styleClass = styleClass;
        }

        public String getStyleClass() {
            return styleClass;
        }

        public void setStyleClass(String styleClass) {
            this.styleClass = styleClass;
        }
    }

    public Alerta(String texto) {
        this.texto = texto;
        this.nivel = Nivel.Default;
    }

    public Alerta(String texto, Nivel nivel) {
        this.texto = texto;
        this.nivel = nivel;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

}
