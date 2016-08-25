package br.gov.to.sefaz.par.gestao.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.par.gestao.business.service.ParametroGeralService;
import br.gov.to.sefaz.par.gestao.business.service.filter.ParametroGeralFilter;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.par.gestao.persistence.repository.ParametroGeralRepository;
import br.gov.to.sefaz.persistence.domain.CodeData;
import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}.
 *
 * @author <a href="mailto:carlos.junior@ntconsult.com.br">carlos.junior</a>
 * @since 23/06/2016 17:25:29
 */
@Service
public class ParametroGeralServiceImpl extends DefaultCrudService<ParametroGeral, Integer>
        implements ParametroGeralService {

    private static Map<String, List<CodeData>> parametros = new HashMap<>();

    @Autowired
    public ParametroGeralServiceImpl(
            ParametroGeralRepository repository) {
        super(repository);
    }

    @Override
    public ParametroGeralRepository getRepository() {
        return (ParametroGeralRepository) super.getRepository();
    }

    @Override
    public ParametroGeral save(@ValidationSuite(context = ValidationContext.SAVE) ParametroGeral entity) {
        ParametroGeral parametroGeral = super.save(entity);
        this.parseParametroGeral(parametroGeral);
        return parametroGeral;
    }

    @Override
    public Collection<ParametroGeral> save(@ValidationSuite(context = ValidationContext.SAVE)
            Collection<ParametroGeral> list) {
        return list.stream().map(parametroGeral -> parametroGeral = this.save(parametroGeral)).collect(Collectors
                .toList());
    }

    @Override
    public ParametroGeral update(@ValidationSuite(context = ValidationContext.UPDATE) ParametroGeral entity) {
        ParametroGeral parametroGeral = super.update(entity);
        this.parseParametroGeral(parametroGeral);
        return parametroGeral;
    }

    @Override
    public Collection<ParametroGeral> update(@ValidationSuite(context = ValidationContext.UPDATE)
            Collection<ParametroGeral> list) {
        return list.stream().map(parametroGeral -> parametroGeral = this.update(parametroGeral)).collect(Collectors
                .toList());
    }

    @Override
    public Optional<ParametroGeral> delete(Integer id) {
        Optional<ParametroGeral> parametroGeral = super.delete(id);
        parametros.remove(parametroGeral.get().getNomeParametroGeral());
        return parametroGeral;
    }

    @Override
    public void delete(Iterable<Integer> ids) {
        Iterator<Integer> iterator = ids.iterator();
        while (iterator.hasNext()) {
            this.delete(iterator.next());
        }
    }

    @Override
    public List<ParametroGeral> find(ParametroGeralFilter filter) {
        return getRepository().find(select -> select.where()
                .opt().like("nomeParametroGeral", filter.getNomeParametroGeral()).orderById());
    }

    @Override
    public ParametroGeral findByNomeParametro(String nomeParametro) {
        return getRepository().findByNome(nomeParametro);
    }

    @Override
    public List<CodeData> findCodeData(String nomeParametro, String... params) {
        // retorna caso esteja em cache
        if (parametros.containsKey(nomeParametro)) {
            return parametros.get(nomeParametro);
        }
        // Caso contrário busca na base de dados
        ParametroGeral parametroGeral = this.findByNomeParametro(nomeParametro);
        if (Objects.isNull(parametroGeral)) {
            return Collections.emptyList();
        } else {
            return this.parseParametroGeral(parametroGeral, params);
        }
    }

    @Override
    public <R extends CodeData> List<R> findCodeData(Function<List<CodeData>, List<R>> converter, String
            nomeParametro, String... params) {
        List<CodeData> list = findCodeData(nomeParametro, params);
        return converter.apply(list);
    }

    /**
     * Verifica se o parâmetro geral é estático ou dinâmico e realiza o parser do mesmo conforme o caso.
     *
     * @param parametroGeral parâmetro geral consultado no banco de dados
     * @param params         parametros do nativeSQL - usado apenas para os parâmetros dinâmicos
     * @return @return lista de código valor
     */
    private List<CodeData> parseParametroGeral(ParametroGeral parametroGeral, String... params) {
        switch (parametroGeral.getTipoParametroGeral()) {
            case ESTATICO:
                List<CodeData> list = this.parseParametroGeralEstatico(parametroGeral.getConteudoValores());
                parametros.put(parametroGeral.getNomeParametroGeral(), list);
                return list;
            case DINAMICO:
                return this.parseParametroGeralDinamico(parametroGeral.getConteudoValores(), params);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Realiza o parser do Parâmetro Geral Estático.
     *
     * @param conteudoValores é uma string no formato 'codigo,valor|codigo,valor|codigo,valor...'
     * @return lista de código valor
     */
    private List<CodeData> parseParametroGeralEstatico(String conteudoValores) {
        List<CodeData> listCodeData = new ArrayList<>();
        for (String row : conteudoValores.split("\\|")) {
            String[] col = row.split(",");
            listCodeData.add(new CodeData(col[0], col[1]));
        }
        return listCodeData;
    }

    /**
     * Realiza o parser do Parâmetro Geral Dinâmico.
     *
     * @param conteudoValores é uma string contendo um nativeSQL
     * @param params          parametros do nativeSQL
     * @return lista de código valor
     */
    private List<CodeData> parseParametroGeralDinamico(String conteudoValores, String... params) {
        // Obejeto de parâmetros bind da consulta dinêmica
        ParamsBuilder paramsBuilder = ParamsBuilder.empty();
        conteudoValores = conteudoValores.replaceAll(";\\s*\\Z", "");
        // Captura todos os parâmetros bind da consulta dinâmica
        Matcher matcher = Pattern.compile("(:)(\\w+)").matcher(conteudoValores);
        int count = 0;
        // Itera os parâmetros bind da consulta dinâmica
        while (matcher.find()) {
            // Atribui os valores para parâmetro bind da consulta dinâmica
            paramsBuilder.put(matcher.group(2), params[count++]);
        }
        // Realiza a consulta do parêmetroGeral Dinâmico
        return getRepository().findPArametroGeralDinamico(conteudoValores, paramsBuilder);
    }

}
