package br.gov.to.sefaz.seg.domain;

import java.util.Objects;

/**
 * Pojo para representar uma rota do breadcrumb.
 *
 * @author gabriel.dias
 */
public class Breadcrumb {

    private final boolean active;
    private final String link;
    private final String name;

    public Breadcrumb(boolean active, String link, String name) {
        this.active = active;
        this.link = link;
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Breadcrumb that = (Breadcrumb) obj;
        return active == that.active
                && Objects.equals(link, that.link)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, link, name);
    }

    @Override
    public String toString() {
        return "Breadcrumb{"
                + "active=" + active
                + ", link='" + link + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
