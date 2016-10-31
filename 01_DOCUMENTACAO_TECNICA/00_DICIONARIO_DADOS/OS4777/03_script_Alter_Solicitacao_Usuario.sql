-- Script para a remover a tabela SEFAZ_SEG.TA_TIPO_USUARIO
-- Autor: Cristiano Luis Schwaab
-- Data: 27 de Setembro de 2016

alter table SEFAZ_SEG.TA_SOLICITACAO_USUARIO drop constraint NN_SOLICITACAO_USUARIO3;
alter table SEFAZ_SEG.TA_SOLICITACAO_USUARIO drop constraint NN_SOLICITACAO_USUARIO4;
commit;
