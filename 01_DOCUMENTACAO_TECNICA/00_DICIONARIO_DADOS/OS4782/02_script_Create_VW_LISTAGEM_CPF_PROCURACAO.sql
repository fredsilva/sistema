-- Script para criação de view VW_LISTAGEM_CPF_PROCURACAO para listagem dos procurados de cada usuário
-- Autor: Volceri D'ávila
-- Data: 05 de setembro de 2016

-- privileges from another schema
GRANT SELECT, REFERENCES ON SEFAZ_CCI.TA_PESSOA_JURIDICA TO SEFAZ_SEG;
GRANT SELECT, REFERENCES ON SEFAZ_CCI.TA_REPRESENTANTE_LEGAL TO SEFAZ_SEG;
-- privileges from create view
GRANT CREATE VIEW TO SEFAZ_SEG;

-- Esquema SEFAZ_SEG
ALTER SESSION SET CURRENT_SCHEMA = SEFAZ_SEG;

CREATE OR REPLACE FORCE VIEW "SEFAZ_SEG"."VW_LISTAGEM_CPF_PROCURACAO" ("CPF_FILTRO", "CPF_CNPJ", "NOME", "NOME_CONTRIBUINTE") AS 
SELECT /* View para o caso de uso [SEGUC0460 - Manter Procuração e Retirar Opções do Sistema]*/
       distinct cpf_filtro, cpf_cnpj, nome, nome_contribuinte
   FROM ( /* o CPF do ator */
         SELECT 
                us.cpf_usuario AS cpf_filtro,
                us.cpf_usuario AS cpf_cnpj,
                us.nome_completo_usuario AS nome,
                   UPPER (TRIM (us.nome_completo_usuario))
                || ' - [Você] (CPF:'
                || us.cpf_usuario
                || ')'
                   AS nome_contribuinte
           FROM sefaz_seg.ta_usuario_sistema us
         UNION
         /* as empresas onde o ator é usuário principal */
         SELECT 
                upe.cpf_usuario AS cpf_filtro,
                upe.cnpj_empresa AS cpf_cnpj,
                pj.nome_razao_social AS nome,
                pj.nome_razao_social || ' - (CNPJ:'
                || upe.cnpj_empresa
                || ')'
                   AS nome_contribuinte
           FROM sefaz_seg.ta_usuario_principal_empresa upe
           LEFT JOIN sefaz_cci.ta_pessoa_juridica pj
                    ON pj.num_base_cnpj = upe.cnpj_empresa
          WHERE upe.registro_excluido = 'N'
         UNION
         /* as empresas onde o ator é representante legal */
         SELECT 
                rl.num_cpf_representante AS cpf_filtro,
                rl.num_base_cnpj AS cpf_cnpj,
                pj.nome_razao_social AS nome,
                UPPER (TRIM (pj.nome_razao_social))
                || ' - (CNPJ:'
                || rl.num_base_cnpj
                || ')'
                   AS nome_contribuinte
           FROM sefaz_cci.ta_pessoa_juridica pj,
                sefaz_cci.ta_representante_legal rl
          WHERE     pj.num_base_cnpj = rl.num_base_cnpj
                AND TRUNC (SYSDATE) BETWEEN TRUNC (data_inicio_mandato)
                                        AND TRUNC (
                                               NVL (data_final_mandato,
                                                    SYSDATE))
                AND pj.registro_excluido = 'N'
                AND rl.registro_excluido = 'N');

