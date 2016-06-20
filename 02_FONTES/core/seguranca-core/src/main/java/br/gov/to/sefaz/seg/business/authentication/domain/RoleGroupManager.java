package br.gov.to.sefaz.seg.business.authentication.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Gerenciador de permissões de acesso (roles) de um usuario no sistema. É possóvel definir diferentes roles para
 * diferentes grupos de roles identificados por {@link RoleGroupKey}, porém apenas um grupo pode estar ativo por vez.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/06/2016 17:49:00
 */
public class RoleGroupManager {

    private final Map<RoleGroupKey, Set<GrantedAuthority>> groups;
    private Optional<RoleGroupKey> activeGroup;

    public RoleGroupManager() {
        this.activeGroup = Optional.empty();
        this.groups = new HashMap<>();
    }

    /**
     * Ativa um grupo de permissões de acesso, ao chamar {@link #getRoles()} apenas as roles pertencentes ao grupo
     * ativo serão retornadas.
     *
     * @param type tipo do grupo de roles
     * @param id identificação do grupo
     */
    public void setActiveGroup(RoleGroupType type, Long id) {
        setActiveGroup(new RoleGroupKey(type, id));
    }

    /**
     * Ativa um grupo de permissões de acesso, ao chamar {@link #getRoles()} apenas as roles pertencentes ao grupo
     * ativo serão retornadas.
     *
     * @param activeGroup chave de identificação do grupo
     */
    public void setActiveGroup(RoleGroupKey activeGroup) {
        this.activeGroup = groups.keySet().stream().filter(activeGroup::equals).findFirst();
    }

    /**
     * Retorna as permissões de acesso (roles) do grupo que esta ativo no momento.
     *
     * @see #setActiveGroup(RoleGroupKey)
     * @return roles do usuario para o grupo ativo
     */
    public Set<GrantedAuthority> getRoles() {
        return groups.getOrDefault(activeGroup.orElse(null), new HashSet<>());
    }

    /**
     * Retorna os grupos de permissões de acesso (roles) que o usuário possui na seesão atual.
     *
     * @return grupos de roles que o usuário tem ativo na sessão
     */
    public Set<RoleGroupKey> getKeys() {
        return groups.keySet().stream().collect(Collectors.toSet());
    }

    /**
     * Adiciona uma permissões de acesso para um grupo.
     *
     * @param type tipo do grupo de roles
     * @param id identificação do grupo
     * @param roles permissões de acesso
     */
    public void addRoles(RoleGroupType type, Long id, String... roles) {
        addRoles(new RoleGroupKey(type, id), Arrays.asList(roles));
    }

    /**
     * Adiciona uma permissões de acesso para um grupo.
     *
     * @param type tipo do grupo de roles
     * @param id identificação do grupo
     * @param roles permissões de acesso
     */
    public void addRoles(RoleGroupType type, Long id, Collection<String> roles) {
        addRoles(new RoleGroupKey(type, id), roles);
    }

    /**
     * Adiciona uma permissões de acesso para um grupo.
     *
     * @param key chave para identificação de um grupo
     * @param roles permissões de acesso
     */
    public void addRoles(RoleGroupKey key, String... roles) {
        addRoles(key, Arrays.asList(roles));
    }

    /**
     * Adiciona uma permissões de acesso para um grupo.
     *
     * @param key chave para identificação de um grupo
     * @param roles permissões de acesso
     */
    public void addRoles(RoleGroupKey key, Collection<String> roles) {
        createIfAbsent(key);
        groups.get(key).addAll(roles.stream()
                .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                .collect(Collectors.toSet()));
    }

    public Optional<RoleGroupKey> getActiveGroup() {
        return activeGroup;
    }

    private void createIfAbsent(RoleGroupKey key) {
        groups.putIfAbsent(key, new HashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleGroupManager that = (RoleGroupManager) o;
        return Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups);
    }
}
