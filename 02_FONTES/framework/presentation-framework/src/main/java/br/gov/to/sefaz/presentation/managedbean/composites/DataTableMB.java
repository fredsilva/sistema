package br.gov.to.sefaz.presentation.managedbean.composites;

import br.gov.to.sefaz.presentation.managedbean.composites.domain.DataTableActionEvents;
import br.gov.to.sefaz.presentation.managedbean.composites.domain.DataTableActionVisible;
import br.gov.to.sefaz.presentation.managedbean.composites.domain.DataTableField;
import br.gov.to.sefaz.presentation.managedbean.composites.domain.DataTableFieldPrint;
import br.gov.to.sefaz.presentation.managedbean.composites.domain.JsFunctionSignature;
import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.swing.text.MaskFormatter;

import static br.gov.to.sefaz.util.application.ApplicationUtil.LOCALE;

/**
 * Managed Bean de uma datatable, possui metodos para auxiliar na construção e operação de uma datatable.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/05/2016 13:42:00
 */
@ManagedBean(name = "dataTableMB")
@ApplicationScoped
@SuppressWarnings("PMD")
public class DataTableMB {

    //@formatter:off

    private static final String FIRST_LVL_SEPARATOR = ",";
    private static final String SECOND_LVL_SEPARATOR = ":";
    private static final String THIRD_LVL_SEPARATOR = ";";
    private static final String END_PARAMS_MARK = ")";
    private static final String BEGIN_PARAMS_MARK = "(";
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    private static final String DATE_TIME_SECS_PATTERN = "dd/MM/yyyy HH:mm:ss";
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final String CPF_MASK = "###.###.###-##";
    private static final String CNPJ_MASK = "##.###.###/####-##";
    private static final String CNPJ_RAIZ_MASK = "##.###.###";

    private static final int CPF_LENGTH = 11;
    private static final int CNPJ_LENGTH = 14;

    /**
     * <p>
     * Gera uma lista de headers da tabela, a partir de uma String formada por uma sequencia de chaves de mensagens de
     * um resource bundle, separados por virgula. Para cada campos do tipo "hide", colunas em branco serão geradas ao
     * final das colunas visiveis
     * </p>
     * <p>
     * Exemplo:
     * </p>
     * <pre>
     * {@code
     *     List headers = dataTableMB.parseHeaders("header.usuario.codigo, header.usuario.nome", "bundle_name");
     *     System.out.print(headers);
     *     // [Código do usuário, Nome do usuário]
     * }
     * </pre>
     *
     * @param headers chaves das mensagens do internacionalizadas contendo os cabeçalhos da tabela
     * @param fields  definições dos campos que preenchem as colunas
     * @param bundle  identificação do arquivo que contém a mensagem de internacionalização
     * @return lista com os textos do cabeçalho internacionalizados
     * @see SourceBundle#getMessage(String, String)
     */
    public List<String> parseHeaders(String headers, List<DataTableField> fields, String bundle) {
        List<String> headersList = splitAndTrim(headers, FIRST_LVL_SEPARATOR);

        fields.stream().filter(DataTableField::getHide).forEach(h -> headersList.add(""));

        return headersList.stream().map(h -> getFromBundle(h, bundle)).collect(Collectors.toList());
    }

    /**
     * <p>
     * Gera uma lista de campos da tabela a partir de uma String, formada por uma sequencia de nomes de atributos do DTO
     * que compõe cada linha da tabela, separados por virgula.
     * </p>
     * <p>
     * Exemplo:
     * </p>
     * <pre>
     * {@code
     *
     *     List fields = dataTableMB.parseFields("codigo, nome, descricao");
     *     System.out.print(fields);
     *     // [codigo, nome, descricao]
     * }
     * </pre>
     *
     * @param fields nome dos attributos do DTO que compõem as linhas da tabela
     * @return lista de campos da tabela
     */
    public List<DataTableField> parseFields(String fields) {
        List<DataTableField> dataTableFields = new ArrayList<>();
        List<String> fieldsDefs = splitAndTrimNotEmpty(fields, FIRST_LVL_SEPARATOR);

        for (String fieldsDef : fieldsDefs) {
            List<String> defs = splitAndTrim(fieldsDef, SECOND_LVL_SEPARATOR);

            String name = defs.remove(0);
            Boolean hide = defs.contains("hide");
            DataTableFieldPrint printType = DataTableFieldPrint.NONE;

            if (defs.contains("boolean")) {
                printType = DataTableFieldPrint.BOOLEAN;
            } else if (defs.contains("string")) {
                printType = DataTableFieldPrint.STRING;
            } else if (defs.contains("date")) {
                printType = DataTableFieldPrint.DATE;
            } else if (defs.contains("dateTime")) {
                printType = DataTableFieldPrint.DATE_TIME;
            } else if (defs.contains("dateTimeSecs")) {
                printType = DataTableFieldPrint.DATE_TIME_SECS;
            } else if (defs.contains("number")) {
                printType = DataTableFieldPrint.NUMBER;
            } else if (defs.contains("cpf")) {
                printType = DataTableFieldPrint.CPF;
            } else if (defs.contains("cnpj")) {
                printType = DataTableFieldPrint.CNPJ;
            } else if (defs.contains("cnpjRaiz")) {
                printType = DataTableFieldPrint.CNPJ_RAIZ;
            } else if (defs.contains("cpfCnpj")) {
                printType = DataTableFieldPrint.CPF_CNPJ;
            }

            dataTableFields.add(new DataTableField(name, hide, printType));
        }

        return dataTableFields;
    }

    /**
     * <p>Retorna o valor do campo no formato de exibição desejado, de acordo com o
     * {@link DataTableFieldPrint}.</p>
     * <ul>
     * <li>{@link DataTableFieldPrint#NONE}: O fieldValue será retornado sem nenhum tratamento</li>
     * <li>{@link DataTableFieldPrint#STRING}: O será retornado o toString() do fieldValue</li>
     * <li>{@link DataTableFieldPrint#NUMBER}: O será retornado o {@link java.text.NumberFormat} do fieldValue</li>
     * <li>{@link DataTableFieldPrint#DATE}: Se fieldValue for do tipo {@link Calendar}, {@link Date},
     * {@link TemporalAccessor} será retornada uma data no formato {@value #DATE_PATTERN}. Se não for de nenhum
     * dos tipos citados será apenas retornado o toString do fieldValue.
     * </li>
     * <li>{@link DataTableFieldPrint#DATE_TIME}: Se fieldValue for do tipo {@link Calendar}, {@link Date},
     * {@link TemporalAccessor} será retornada uma data no formato {@value #DATE_TIME_PATTERN}. Se não for de nenhum
     * dos tipos citados será apenas retornado o toString do fieldValue.</li>
     * </ul>
     *
     * @param fieldValue valor do campo que será transformado para exibição
     * @param printType  tipo do formato de exibição que será aplicado ao fieldValue
     * @return fieldValue formatado de acordo com o {@link DataTableFieldPrint} informado
     */
    public Object formatToPrint(Object fieldValue, DataTableFieldPrint printType) {
        if (Objects.isNull(fieldValue)) {
            return "";
        }
        switch (printType) {
            case BOOLEAN:
                return booleanFormat(fieldValue);
            case DATE:
                return toDateFormat(fieldValue, DATE_PATTERN);
            case DATE_TIME:
                return toDateFormat(fieldValue, DATE_TIME_PATTERN);
            case DATE_TIME_SECS:
                return toDateFormat(fieldValue, DATE_TIME_SECS_PATTERN);
            case STRING:
                return fieldValue.toString();
            case NUMBER:
                return FormatterUtil.formatNumber((Number) fieldValue);
            case CPF:
                return cpfCnpjFormat(fieldValue, CPF_MASK, CPF_LENGTH);
            case CNPJ:
                return cpfCnpjFormat(fieldValue, CNPJ_MASK, CNPJ_LENGTH);
            case CNPJ_RAIZ:
                return cpfCnpjFormat(fieldValue, CNPJ_RAIZ_MASK, 8);
            case CPF_CNPJ:
                return cpfCnpjFormat(fieldValue);
            case NONE:
            default:
                return fieldValue;
        }
    }

    /**
     * <p>
     * Retorna um texto internacionalizado de um sourceBundle especifico. Em caso de uma String vazia ele retornara uma
     * String vazia também. Caso contrario o comportamento será o mesmo de
     * {@link SourceBundle#getMessage(String, String)}.
     * </p>
     * <p>
     * Exemplo:
     * </p>
     * <pre>
     * {@code
     *
     *  String message;
     *  message = dataTableMB.getFromBundle("header.usuario.codigo", "bundle_name")
     *  System.out.print("\"" + message + "\"");
     *  // "Código do usuário"
     *
     *  message = dataTableMB.getFromBundle("", "bundle_name")
     *  System.out.print(message);
     *  // ""
     * }
     * </pre>
     *
     * @param messageKey Chave da mensagem do arquivo de internacionalização
     * @param bundle     identificação do arqui que contém a mensagem de internacionalização
     * @return mensagem internacionalizada de arquivo/bundle específico, ou String vazia ("") em caso da messageKey
     *      também ser vazia
     */
    public String getFromBundle(String messageKey, String bundle) {
        messageKey = messageKey.trim();

        if (messageKey.isEmpty()) {
            return "";
        }

        return SourceBundle.getMessage(bundle, messageKey);
    }

    /**
     * <p>
     * Gera uma lista de {@link DataTableActionEvents}, que definem as ações (funções javaScript) que serão
     * chamadas dados os eventos (binds do jQuery. Exemplo: "click") de elementos de uma determinada classe (Exemplo:
     * {@code <a class="classe">evento</a>}) HTML.
     * </p>
     * <p>
     * A String de entrada contém uma lista de definições separada por "," (virgula), cada definição possui
     * diverssos atributos que devem seguir o seguinte formato:
     * </p>
     * <pre>
     * class-do-elemento:tipo-de-bind:função-js(parametro-1;parametro-...):tipo-de-evento:complemento
     * </pre>
     * <ul>
     * <li>class-do-elemento*: classe do elemento HTML ao qual o evento será vinculado</li>
     * <li>tipo-de-bind*: tipo do bind vinculado ao elemento HTML que irá disparar o evento. Os tipos de binds
     * básicos suportados são: click, keydown, keypress, keyup, mouseover, mouseout, mouseenter,
     * mouseleave, scroll, focus, blur e resize. Para saber mais detalhes veja
     * <a href="http://api.jquery.com/on/#on-events-selector-data">jQuery API</a></li>
     * <li>função-js*: nome da função javaScript que será chamada quando o evento for disparado</li>
     * <li>(parametro-1;parametro-...): uma lista separada por ";" (ponto e virgula) contendo os indices das
     * colunas de uma linha da tabela, cujo os valores serão passados para a função-js. Caso não sejam
     * informados ou sejam informados de maneira vazia ("()", sem parametros) será passado um array contendo as
     * colunas de uma linha da tabela (Exemplo: data[coluna] = valor)</li>
     * <li>tipo-de-evento: Tipo do evento que será disparado, se não informado assim que o tipo-de-bind for
     * disparado a função-js deve ser chamada. Os tipos de eventos existentes são:
     * <ul>
     * <li>confirmation: quando o bind for chamado em vez de chamar a função-js deve ser exibida uma
     * confirmação, exibindo uma mensagem do source bundle cuja a chave é o complemento, em caso da
     * confirmação ser aceita pelo usuário a função-js deve ser chamada com seus devidos parametros</li>
     * </ul>
     * </li>
     * <li>complemento: Um ou mais parametros utilizados pelo tipo-de-evento</li>
     * </ul>
     * <p><small>* parametros obrigatórios</small></p>
     * <p>O mapeamento dos atributos para o {@link DataTableActionEvents} é feito da seguinte maneira</p>
     * <ul>
     * <li>classe-do-elemento - {@link DataTableActionEvents#getElementClass()}</li>
     * <li>tipo-de-bind - {@link DataTableActionEvents#getBindType()}</li>
     * <li>função-js - {@link DataTableActionEvents#getFunction()}</li>
     * <li>(parametro-1;parametro-...) - {@link DataTableActionEvents#getParams()}</li>
     * <li>tipo-de-evento - {@link DataTableActionEvents#getEventType()}</li>
     * <li>complemento - {@link DataTableActionEvents#getComplement()}</li>
     * </ul>
     * <p>Exemplos</p>
     * <pre>
     * {@code
     *      DataTableActionEvents parsed;
     *      DataTableActionEvents manual;
     *
     *      parsed = dataTable.parseActionEvents("select-row:click:selectRow");
     *      manual = new {@link DataTableActionEvents}("select-row", "click", "selectRow", new ArrayList(), "", "")
     *      System.out.print(parsed.equalsTo(manual));
     *      // true
     *
     *      parsed = dataTable
     *              .parseActionEvents("delete-row:click:deleteRowOfTable(0;1):confirmation:table.msg.confirm");
     *      ArrayList params = new ArrayList();
     *      params.add(0);
     *      params.add(1);
     *      manual = new {@link DataTableActionEvents}("delete-row", "click", "deleteRowOfTable", params,
     *              "confirmation", "table.tem.certeza");
     *      System.out.print(parsed.equalsTo(manual));
     *      // true
     * }
     * </pre>
     *
     * @param actionEvents string formatada contendo as definições do eventos vinculados a elementos da tabela
     * @return lista de {@link DataTableActionEvents} contendo as definições dos eventos
     */
    //@formatter:on
    public List<DataTableActionEvents> parseActionEvents(String actionEvents) {
        List<DataTableActionEvents> parsedActions = new ArrayList<>();

        for (String action : splitAndTrimNotEmpty(actionEvents, FIRST_LVL_SEPARATOR)) {
            List<String> attributes = splitAndTrim(action, SECOND_LVL_SEPARATOR);

            String elementClass = attributes.get(0);
            String bindType = attributes.get(1);

            List<String> functionAndParams = splitAndTrim(attributes.get(2).replace(END_PARAMS_MARK, ""),
                    "\\" + BEGIN_PARAMS_MARK);
            String function = functionAndParams.get(0);

            List<String> params;
            boolean hasParameters = functionAndParams.size() > 1;

            if (hasParameters) {
                params = splitAndTrim(functionAndParams.get(1), THIRD_LVL_SEPARATOR);
            } else {
                params = new ArrayList<>();
            }

            String eventType;
            String complement;
            boolean hasEventType = attributes.size() > 3;

            if (hasEventType) {
                eventType = attributes.get(3);
                complement = attributes.get(4);
            } else {
                eventType = "";
                complement = "";
            }

            parsedActions
                    .add(new DataTableActionEvents(elementClass, bindType, function, params, eventType, complement));
        }

        return parsedActions;
    }

    /**
     * Faz o parse dos campos da tabela que precisam ser - ou não - escondidos. Deve-se ser criado, na tela que
     * utilizará o recurso, um javascript que faça a validação necessária. O retorno dessa função, deve ser
     * obrigatoriamente um boolean. Vide cria-usuario-senha.js.
     * @param actionEvents string de eventos, funções e parâmetros que o componente terá.
     * @return Lista de ações e visibilidade.
     */
    public List<DataTableActionVisible> parseActionVisible(String actionEvents) {
        List<DataTableActionVisible> parsedActions = new ArrayList<>();

        for (String action : splitAndTrimNotEmpty(actionEvents, FIRST_LVL_SEPARATOR)) {
            List<String> attributes = splitAndTrim(action, SECOND_LVL_SEPARATOR);

            String elementClass = attributes.get(0);
            String visibility = attributes.get(1);

            List<String> functionAndParams = splitAndTrim(attributes.get(2).replace(END_PARAMS_MARK, ""),
                    "\\" + BEGIN_PARAMS_MARK);
            String function = functionAndParams.get(0);

            List<String> params;
            boolean hasParameters = functionAndParams.size() > 1;

            if (hasParameters) {
                params = splitAndTrim(functionAndParams.get(1), THIRD_LVL_SEPARATOR);
            } else {
                params = new ArrayList<>();
            }

            parsedActions
                    .add(new DataTableActionVisible(elementClass, visibility, function, params));
        }

        return parsedActions;
    }

    /**
     * Retorna uma JSON list com os parametros das colunas da datatable.
     *
     * @param columnsDefs pré-definições das colunas
     * @param fields      campos da tabela
     * @return todas as definições de todas as colunas
     */
    public String parseColumnDefs(String columnsDefs, List<DataTableField> fields) {
        List<String> defs = parseFieldsDefs(fields);
        defs.add(columnsDefs);

        return "[" + defs.stream().filter(d -> !d.trim().isEmpty()).collect(Collectors.joining(FIRST_LVL_SEPARATOR))
                + "]";
    }

    /**
     * <p>
     * Constroi uma chamada JS a partir de um {@link DataTableActionEvents}, passando como parametros indices
     * especificos do paramName, passado por parametro, ou ele inteiro.
     * </p>
     * <p>
     * Exemplo:
     * </p>
     *
     * <pre>
     * {@code
     *      DataTableActionEvents defs;
     *      defs = new {@link DataTableActionEvents}("delete-row", "click", "deleteRow", new ArrayList(), "", "");
     *      System.out.print(dataTableMb.buildJsCall(defs, "rowOfTable"));
     *      // deleteRow(rowOfTable);
     *
     *      ArrayList params = new ArrayList();
     *      params.add(0);
     *      params.add(1);
     *      defs = new {@link DataTableActionEvents}("delete-row", "click", "deleteRow", params, "", "");
     *      System.out.print(dataTableMb.buildJsCall(defs, "data"));
     *      // deleteRow(data[0], data[1]);
     * }
     * </pre>
     *
     * @param action    definições do função que sera chamada
     * @param paramName nome do parametro que sera passado na chamada da função
     * @return uma chamada de função javaScript
     */
    public String buildJsCall(JsFunctionSignature action, String paramName) {
        return action.getFunction() + BEGIN_PARAMS_MARK + paramName
                + action.getParams().stream().map(p -> "[" + p + "]").collect(Collectors.joining(", " + paramName))
                + ", element" + END_PARAMS_MARK;
    }

    /**
     * Interpreta uma String com as definições dos campos da tabela e retorna uma lista com as definições dos campos em
     * formato JSON.
     *
     * @param fields lista de fields e parametros de fields formatada
     * @return lista de definições dos fields mapeadas pelo indice
     */
    private List<String> parseFieldsDefs(List<DataTableField> fields) {
        List<String> fieldsDefs = new ArrayList<>();

        fieldsDefs.add(parseHiddenColumnsDef(fields));

        return fieldsDefs;
    }

    /**
     * Constroi um JSON definindo os campos escondidos da tabela baseado nas definições dos campos passadas por
     * parametro.
     *
     * @param fields as definições dos campos da tabela
     * @return um JSON definindo os campos da tabela não devem ser visiveis
     */
    private String parseHiddenColumnsDef(List<DataTableField> fields) {
        StringBuilder def = new StringBuilder("{'visible': false, 'targets': [");
        List<String> hiddenIndexes = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getHide()) {
                hiddenIndexes.add(String.valueOf(i));
            }
        }

        def.append(hiddenIndexes.stream().collect(Collectors.joining(FIRST_LVL_SEPARATOR))).append("]}");

        return def.toString();
    }

    /**
     * <p>
     * Formata um valor do tipo {@link Calendar}, {@link Date} ou {@link TemporalAccessor} para uma data formatada de
     * acordo com o pattern passado por parametro. Se o value informado não for de nenhum dos tipos citados será
     * retornado apenas o toString() do value.
     * </p>
     *
     * @param value       valor de tipo data que deseja-se formatar
     * @param datePattern formato da data que será gerada
     * @return o value formatado de acordo com o datePattern passado
     */
    private String toDateFormat(Object value, String datePattern) {
        if (value instanceof Date) {
            DateFormat formatter = new SimpleDateFormat(datePattern, LOCALE);
            return formatter.format((Date) value);
        } else if (value instanceof Calendar) {
            DateFormat formatter = new SimpleDateFormat(datePattern, LOCALE);
            return formatter.format(((Calendar) value).getTime());
        } else if (value instanceof TemporalAccessor) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return formatter.format((TemporalAccessor) value);
        } else {
            return value == null ? "" : value.toString();
        }
    }

    private String cpfCnpjFormat(Object value) {
        String valueToFormat = StringUtils.trim(value.toString());

        if (valueToFormat.length() == CPF_LENGTH) {
            return cpfCnpjFormat(valueToFormat, CPF_MASK, CPF_LENGTH);
        } else {
            return cpfCnpjFormat(valueToFormat, CNPJ_MASK, CNPJ_LENGTH);
        }
    }

    /**
     * <p>
     * Formata um valor do tipo CPF, CNPJ ou CNPJ Raiz para o seu formato brasileiro de apresentação, exmplo cpf:
     * 999.999.999-99, exmplo cnpj: 99.999.999/9999-99.
     * </p>
     *
     * @param value  valor que deseja-se formatar
     * @param mask   máscara da formatação
     * @param digits quantidade de dígitos inteiros do tipo que deseja-se formatar
     * @return CPF, CNPJ ou CNPJ Raiz formatado
     */
    private String cpfCnpjFormat(Object value, String mask, int digits) {

        DecimalFormat df = new DecimalFormat(StringUtils.repeat('0', digits));
        String retorno = df.format(Long.valueOf(value.toString()));

        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setValueContainsLiteralCharacters(false);
            retorno = formatter.valueToString(retorno);
        } catch (ParseException e) {
            String message = "Erro ao formatar o valor " + value.toString() + " com a máscara " + mask;
            throw new IllegalArgumentException(message, e);
        }

        return retorno;
    }

    /**
     * Formata um valor do tipo Boolean em uma resposta Sim para true e Não para false.
     *
     * @param value valor que deseja-se formatar
     * @return Sim ou Não
     */
    private String booleanFormat(Object value) {
        if ((Boolean) value) {
            return SourceBundle.getMessage("label.sim");
        } else {
            return SourceBundle.getMessage("label.nao");
        }
    }

    /**
     * Possui um comportamento semelhante ao {@link #splitAndTrim(String, String)}, porém se a sentence for vazia, uma
     * lista vazia será retornada.
     *
     * @param sentence texto completo
     * @param regex    expressão regular que sera procurada no texto, e se encontrada ira quebrar o texto naquele exato
     *                 local
     * @return retorna os pedaços do texto separados pela regex, ou uma lista vazia em caso de sentence vazia.
     */
    private List<String> splitAndTrimNotEmpty(String sentence, String regex) {
        if (sentence.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return splitAndTrim(sentence, regex);
    }

    /**
     * Quebra um texto a partir de uma regex (expressão regular) e remove os espaços em branco ao inicio e fim de cada
     * pedaço. Em caso de sentence vazia uma lista com uma posição será retornada.
     *
     * @param sentence texto completo
     * @param regex    expressão regular que sera procurada no texto, e se encontrada ira quebrar o texto naquele exato
     *                 local
     * @return retorna os pedaços do texto separados pela regex
     */
    private List<String> splitAndTrim(String sentence, String regex) {
        List<String> pieces = new ArrayList<>();

        for (String piece : sentence.split(regex)) {
            pieces.add(piece.trim());
        }

        return pieces;
    }
}
