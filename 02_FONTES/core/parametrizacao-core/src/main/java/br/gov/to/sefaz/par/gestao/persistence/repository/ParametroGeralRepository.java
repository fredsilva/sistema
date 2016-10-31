package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.persistence.domain.CodeData;
import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:20:21
 */
@Repository
public class ParametroGeralRepository extends BaseRepository<ParametroGeral, Integer> {

    /**
     * Busca uma lista de Parâmetros Gerais Filtrada por Nome.
     *
     * @param nome Nome do Parâmetro Geral.
     * @return lista de ParametroGeral.
     */
    public ParametroGeral findByNome(String nome) {
        return findOne(select -> select.where().equal("upper(nomeParametroGeral)", nome.toUpperCase(ApplicationUtil
                .LOCALE)));
    }

    /**
     * Consulta parâmetros gerais dinâmicos, ou seja, os que possuem uma consulta sql cadastrada.
     *
     * @param sql sql da consulta dinâmica
     * @param params parâmetros da consulta dinâmica
     * @return Lista de Código, Valor da consulta dinâmica.
     */
    public List<CodeData> findPArametroGeralDinamico(String sql, ParamsBuilder params) {
        List<Object[]> list = findNativeQuery(sql, params);
        return list.stream().map(o -> new CodeData<>(o[0], o[1])).collect(Collectors.toList());
    }

    /**
     * Verifica se existe algum registro com
     * {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral#nomeParametroGeral}
     * informado diferente do {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral#idParametroGeral}.
     *
     * @param idParametroGeral id do parâmetro
     * @param nomeParametroGeral nome do parâmetro
     * @return se existe algum registro
     */
    public boolean findExitsNome(Integer idParametroGeral, String nomeParametroGeral) {
        return exists(select -> select.where()
                .different("idParametroGeral", idParametroGeral)
                .and().equal("nomeParametroGeral", nomeParametroGeral));
    }

}
