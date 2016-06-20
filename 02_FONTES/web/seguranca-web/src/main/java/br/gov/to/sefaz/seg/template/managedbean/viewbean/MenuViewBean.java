package br.gov.to.sefaz.seg.template.managedbean.viewbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * View Bean para construção de menus do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/06/2016 14:23:00
 */
public class MenuViewBean implements Comparable<MenuViewBean> {

    private final String name;
    private final Long id;
    private final String ref;
    private final List<MenuViewBean> childs;

    public MenuViewBean(String name, Long id, Collection<MenuViewBean> childs) {
        this.name = name;
        this.id = id;
        this.ref = id.toString();
        this.childs = new ArrayList<>(childs);
    }

    public MenuViewBean(String name, Long id) {
        this(name, id, new ArrayList<>());
    }

    public MenuViewBean(String name, Long id, String ref) {
        this.name = name;
        this.id = id;
        this.ref = ref;
        this.childs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    /**
     * Retorna os menus filhos.
     *
     * @return menus filhos
     */
    public List<MenuViewBean> getChilds() {
        Collections.sort(childs);
        return childs;
    }

    /**
     * Adiciona varios menus filhos.
     *
     * @param menus menus filhos
     */
    public void addAllChilds(Collection<MenuViewBean> menus) {
        childs.addAll(menus);
    }

    /**
     * Adiciona um menu filho.
     *
     * @param menu menu filho.
     */
    public void addChild(MenuViewBean menu) {
        childs.add(menu);
    }

    public String getRef() {
        return ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuViewBean that = (MenuViewBean) o;
        return Objects.equals(name, that.name)
                && Objects.equals(id, that.id)
                && Objects.equals(ref, that.ref)
                && Objects.equals(childs, that.childs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, ref, childs);
    }

    @Override
    public String toString() {
        return "MenuViewBean{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", ref='" + ref + '\''
                + ", childs=" + childs
                + '}';
    }

    @Override
    public int compareTo(MenuViewBean o) {
        return this.name.compareTo(o.name);
    }
}
