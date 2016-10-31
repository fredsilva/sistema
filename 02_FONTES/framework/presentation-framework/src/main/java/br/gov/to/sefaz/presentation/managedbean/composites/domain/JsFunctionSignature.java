package br.gov.to.sefaz.presentation.managedbean.composites.domain;

import java.util.List;

/**
 * Interface que define uma assinatura de uma função JavaScript.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/06/2016 11:22:00
 */
public interface JsFunctionSignature {

    /**
     * Busca a função que será utilizada no elemento da tabela.
     * @return nome da função.
     */
    String getFunction();

    /**
     * Busca os parâmetros que serão utilizadas na função do elemento da tabela.
     * @return lista de parâmetros que serão passados na função.
     */
    List<String> getParams();
}
