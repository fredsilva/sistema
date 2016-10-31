-- Script para criação de view VW_HISTORICO_NAVEGACAO o caso de uso Implementação do caso SEGUC0630 - Consultar por Diversos Filtros do Histórico de Acesso ao Sistema
-- Autor: Volceri D'ávila
-- Data: 05 de setembro de 2016

-- privileges from create view
GRANT CREATE VIEW TO SEFAZ_SEG;

-- Esquema SEFAZ_SEG
ALTER SESSION SET CURRENT_SCHEMA = SEFAZ_SEG;
				  
-- View para o caso de uso Implementação do caso SEGUC0630 - Consultar por Diversos Filtros do Histórico de Acesso ao Sistema
CREATE OR REPLACE FORCE VIEW SEFAZ_SEG.VW_HISTORICO_NAVEGACAO
(
   IDENTIFICACAO_NAVEGACAO,
   CPF_USUARIO,
   DATA_OPERACAO,
   TIPO_OPERACAO,
   DETALHE_NAVEGACAO,
   CPF_CNPJ_PROCURADO,
   NOME_PROCURADO,
   URL_ACESSO
)
AS
   SELECT LN."IDENTIFICACAO_NAVEGACAO",
          LN."CPF_USUARIO",
          LN."DATA_OPERACAO",
          LN."TIPO_OPERACAO",
          LN."DETALHE_NAVEGACAO",]
          LN."CPF_CNPJ_PROCURADO",
          CASE
             WHEN CPF_CNPJ_PROCURADO IS NOT NULL
             THEN
                (  SELECT LCP.nome
                     FROM SEFAZ_SEG.VW_LISTAGEM_CPF_PROCURACAO LCP
                    WHERE LCP.cpf_cnpj = LN.CPF_CNPJ_PROCURADO
                 GROUP BY LCP.cpf_cnpj, LCP.nome)
             ELSE
                NULL
          END
             AS NOME_PROCURADO,
          LN."URL_ACESSO"
     FROM SEFAZ_SEG.TA_LOG_NAVEGACAO LN
    WHERE LN.REGISTRO_EXCLUIDO = 'N';