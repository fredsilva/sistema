package br.gov.to.sefaz.seg.filter;

import br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

/**
 * Builder para a classe {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/06/2016 12:19:00
 */
public class DetalheNavegacaoRequestBuilder {

    private final DetalheNavegacao detalheNavegacao;

    public DetalheNavegacaoRequestBuilder() {
        detalheNavegacao = new DetalheNavegacao();
    }

    /**
     * Adiciona as informações obtidas do {@link javax.servlet.http.HttpServletRequest} para o
     * {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
     *
     * @param request informações que serão extraídas do request
     * @return instância da classe
     */
    public DetalheNavegacaoRequestBuilder withHttpRequest(HttpServletRequest request) {
        String serverName = request.getServerName();
        String userAgent = request.getHeader("User-Agent");
        String language = request.getHeader("Accept-Language");
        String httpMethod = request.getMethod();
        String country = request.getLocale().getDisplayCountry();
        String pathInfo = Optional.ofNullable(request.getPathInfo()).orElse(StringUtils.EMPTY);
        Map<String, String[]> requestParameters = request.getParameterMap();
        String localAddress = request.getLocalAddr();
        String remoteAddress = request.getRemoteAddr();
        String resource = request.getRequestURL().toString();

        detalheNavegacao.setServerName(serverName);
        detalheNavegacao.setUserAgent(userAgent);
        detalheNavegacao.setLanguage(language);
        detalheNavegacao.setHttpMethod(httpMethod);
        detalheNavegacao.setCountry(country);
        detalheNavegacao.setPathInfo(pathInfo);
        detalheNavegacao.setRequestParameters(requestParameters);
        detalheNavegacao.setLocalAddress(localAddress);
        detalheNavegacao.setRemoteAddress(remoteAddress);
        detalheNavegacao.setResource(resource);

        return this;
    }

    /**
     * Adiciona o CPF do Usuário em {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
     *
     * @param cpfUsuario cpf do usuário
     * @return instância da classe
     */
    public DetalheNavegacaoRequestBuilder withCpfUsuario(String cpfUsuario) {
        detalheNavegacao.setNomeUsuario(cpfUsuario);

        return this;
    }

    /**
     * Adiciona a Data da Operação realizada pelo usuário em
     * {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
     *
     * @param localDateTime data e hora da requisição da operação
     * @return instância da classe
     */
    public DetalheNavegacaoRequestBuilder withDataOperacao(LocalDateTime localDateTime) {
        String timeStamp = localDateTime.toString();
        detalheNavegacao.setTimeStamp(timeStamp);

        return this;
    }

    /**
     * Adiciona o tempo de operação em {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
     *
     * @param timeEllapsed tempo em milisegundos para o tempo da operação.
     * @return instância da classe
     */
    public DetalheNavegacaoRequestBuilder withElapsedTime(long timeEllapsed) {
        detalheNavegacao.setTimeEllapsed(timeEllapsed);

        return this;
    }

    /**
     * Adiciona o cpf/cnpj do procurado no {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao}.
     *
     * @param cpfCnpj cpf ou cnpj do procurado
     * @return instância da classe
     */
    public DetalheNavegacaoRequestBuilder withAtuacaoNomeDe(String cpfCnpj) {
        detalheNavegacao.setAtuacaoNomeDe(Objects.isNull(cpfCnpj) ? StringUtils.EMPTY : cpfCnpj);

        return this;
    }

    /**
     * Constrói o {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao} conforme a construção desejada.
     *
     * @return {@link br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao} construido através dos métodos de
     *         construção da classe
     */
    public DetalheNavegacao build() {
        return detalheNavegacao;
    }
}
