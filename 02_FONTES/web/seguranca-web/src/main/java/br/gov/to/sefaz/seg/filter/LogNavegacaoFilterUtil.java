package br.gov.to.sefaz.seg.filter;

import br.gov.to.sefaz.seg.business.gestao.builder.DetalheNavegacao;
import br.gov.to.sefaz.seg.business.gestao.builder.LogNavegacaoBuilder;
import br.gov.to.sefaz.seg.business.gestao.service.LogNavegacaoService;
import br.gov.to.sefaz.seg.persistence.entity.LogNavegacao;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

/**
 * Utilitário para armazenar no Banco de Dados as informações referente a entidade
 * {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/06/2016 13:44:00
 */
@Component
public class LogNavegacaoFilterUtil {

    @Autowired
    private LogNavegacaoService logNavegacaoService;

    /**
     * Sava no Banco de Dados o {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao} obtido através do parâmetros
     * passados.
     *
     * @param request informações do request realizado pelo usuário
     * @param cpfUsuario cpf do usuário
     * @param localDateTime data e hora da operação
     * @param tipoOperacao se foi uma operação de navegação ou uma operação negada
     * @param elapsedTime tempo da realização da operação
     * @throws JsonProcessingException exceção quando json não consegue processar a informação
     */
    public void saveLogNavegacao(HttpServletRequest request, String cpfUsuario, LocalDateTime localDateTime,
            TipoOperacaoEnum tipoOperacao, long elapsedTime) throws JsonProcessingException {
        DetalheNavegacao detalheNavegacao = createDetalheNavegacao(request, cpfUsuario, localDateTime, elapsedTime);
        LogNavegacao logNavegacao = createLogNavegacao(cpfUsuario, localDateTime, detalheNavegacao, tipoOperacao);
        logNavegacaoService.save(logNavegacao);
    }

    private LogNavegacao createLogNavegacao(String cpfUsuario, LocalDateTime localDateTime,
            DetalheNavegacao detalheNavegacao, TipoOperacaoEnum tipoOperacao) throws JsonProcessingException {
        LogNavegacaoBuilder logNavegacaoBuilder = new LogNavegacaoBuilder();
        return logNavegacaoBuilder.withCpfUsuario(cpfUsuario)
                .withDataOperacao(localDateTime)
                .withDetalheNavegacao(detalheNavegacao)
                .withTipoOperacao(tipoOperacao)
                .build();
    }

    private DetalheNavegacao createDetalheNavegacao(HttpServletRequest request, String cpfUsuario,
            LocalDateTime localDateTime, long elapsedTime) {
        DetalheNavegacaoRequestBuilder builder = new DetalheNavegacaoRequestBuilder();
        return builder.withCpfUsuario(cpfUsuario)
                .withHttpRequest(request)
                .withDataOperacao(localDateTime)
                .withElapsedTime(elapsedTime)
                .build();
    }
}
