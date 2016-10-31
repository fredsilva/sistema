-- Script para criar a VIEW SEFAZ_ARR.VW_CONTRIBUINTES
-- Autor: Gabriel Santos
-- Data: 27 de Outubro de 2016

CREATE OR REPLACE FORCE VIEW "SEFAZ_ARR"."VW_CONTRIBUINTES" ("TIPO_CONTRIBUINTE", "TIPO_PESSOA", "ID_PESSOA", "RAZAO_SOCIAL_NOME", "SITUACAO", "RENAVAM") AS 
SELECT "TIPO_CONTRIBUINTE","TIPO_PESSOA","ID_PESSOA","RAZAO_SOCIAL_NOME","SITUACAO","RENAVAM" FROM SEFAZ_ARR.VW_TA_CONTRIBUINTES;
 