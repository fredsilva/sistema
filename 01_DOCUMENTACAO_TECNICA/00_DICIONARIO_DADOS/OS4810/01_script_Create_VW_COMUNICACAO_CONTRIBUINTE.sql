-- Script para criação de view VW_COMUNICACAO_CONTRIBUINTE para o caso de uso SCEUC0003 - Consultar Comunicações com os Contribuintes
-- Autor: Volceri D'ávila
-- Data: 05 de setembro de 2016

-- privileges from create view
GRANT CREATE VIEW TO SEFAZ_SEG;

-- Esquema SEFAZ_SEG
ALTER SESSION SET CURRENT_SCHEMA = SEFAZ_SEG;

/* View para o caso de uso SCEUC0003 - Consultar Comunicações com os Contribuintes*/
CREATE OR REPLACE FORCE VIEW SEFAZ_SEG.VW_COMUNICACAO_CONTRIBUINTE
(
   ID_COMUNICACAO,
   CPF_DESTINATARIO,
   NOME_DESTINATARIO,
   DATA_COMUNICACAO,
   DT_HR_COMUNICACAO,
   ASSUNTO,
   CONTEUDO,
   TIPO_COMUNICACAO,
   TEM_ANEXO,
   TEM_ERRO
)
AS
   SELECT ROWIDTOCHAR (cc.ROWID) AS ID_COMUNICACAO,
          cc.cpf_destinatario,
          u.nome_completo_usuario AS nome_destinatario,
          TRUNC (cc.data_envio) AS DATA_COMUNICACAO,
          cc.data_envio AS DT_HR_COMUNICACAO,
          cc.assunto AS assunto,
          cc.conteudo AS conteudo,
          'EMAIL' AS tipo_comunicacao,
          CASE WHEN LENGTH (cc.anexo) > 0 THEN 1 ELSE 0 END AS tem_anexo,
          0 AS tem_erro
     FROM sefaz_seg.ta_correio_contribuinte cc,
          sefaz_seg.ta_usuario_sistema u
    WHERE cc.cpf_destinatario = u.cpf_usuario
   UNION ALL
   SELECT ROWIDTOCHAR (sc.ROWID) AS ID_COMUNICACAO,
          sc.cpf_destinatario,
          u.nome_completo_usuario AS nome_destinatario,
          TRUNC (sc.data_envio) AS DATA_COMUNICACAO,
          sc.data_envio AS DT_HR_COMUNICACAO,
          sc.conteudo AS assunto, --usando conteudo como assunto, pois sms não tem assunto
          TO_CLOB (sc.conteudo) AS conteudo,
          'SMS' AS tipo_comunicacao,
          0 AS tem_anexo,
          0 AS tem_erro
     FROM sefaz_seg.ta_sms_contribuinte sc, sefaz_seg.ta_usuario_sistema u
    WHERE sc.cpf_destinatario = u.cpf_usuario;
	