package br.gov.to.sefaz.persistence.query.builder;

/**
 * Interface de assinatura resposável por métodos da Estrutura da Query.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 09:58:00
 * @param <T> Entidade base.
 * @param <S> Entidade base.
 */
public interface QueryStructureBuilder<T,S> {

    /**
     * Assinatura do Método responsável por executar getRoot.
     *
     * @return retornar o objeto da classe.
     */
    T getRoot();

    /**
     * Assinatura do Método responsável por executar build.
     *
     * @return retornar a montagem da consulta.
     */
    S build();
}