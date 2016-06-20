package br.gov.to.sefaz.seg.business.gestao.builder;

import br.gov.to.sefaz.seg.persistence.entity.LogNavegacao;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import br.gov.to.sefaz.util.json.JsonMapperUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.hibernate.engine.jdbc.ClobProxy;

import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * Builder para a classe {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/06/2016 11:50:00
 */
public class LogNavegacaoBuilder {

    private final LogNavegacao logNavegacao;

    public LogNavegacaoBuilder() {
        logNavegacao = new LogNavegacao();
    }

    /**
     * Adiciona o CPF do Usuário em {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}.
     *
     * @param cpfUsuario cpf do usuário
     * @return instância da classe
     */
    public LogNavegacaoBuilder withCpfUsuario(String cpfUsuario) {
        logNavegacao.setCpfUsuario(cpfUsuario);

        return this;
    }

    /**
     * Adiciona a Data da Operação realizada pelo usuário em {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}
     * .
     *
     * @param dateTime data e hora da requisição da operação
     * @return instância da classe
     */
    public LogNavegacaoBuilder withDataOperacao(LocalDateTime dateTime) {
        logNavegacao.setDataOperacao(dateTime);

        return this;
    }

    /**
     * Adiciona o {@link DetalheNavegacao} através da conversão do objeto em um JSON e transformado em um
     * {@link java.sql.Clob}.
     *
     * @param detalheNavegacao informações necessárias para os detalhes da navegação
     * @return instância da classe
     * @throws JsonProcessingException exceção quando json não consegue processar a informação
     */
    public LogNavegacaoBuilder withDetalheNavegacao(DetalheNavegacao detalheNavegacao) throws JsonProcessingException {
        String json = JsonMapperUtils.objectToJson(detalheNavegacao);
        Clob detalhe = ClobProxy.generateProxy(json);

        logNavegacao.setDetalheNavegacao(detalhe);

        return this;
    }

    /**
     * Adiciona o {@link br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum} no
     * {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}.
     *
     * @param tipoOperacao se foi uma operação de navegação ou uma operação negada
     * @return instância da classe
     */
    public LogNavegacaoBuilder withTipoOperacao(TipoOperacaoEnum tipoOperacao) {
        logNavegacao.setTipoOperacao(tipoOperacao);

        return this;
    }

    /**
     * Constroi o {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao} conforme a construção desejada.
     *
     * @return {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao} construido através dos métodos de construção
     *         da classe
     */
    public LogNavegacao build() {
        return logNavegacao;
    }

}
