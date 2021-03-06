﻿-- Script para criar a tabela TA_ATIVIDADE_ECONOMICA no esquema SEFAZ_PAR
-- Autor: Cristiano Luis Schwaab
-- Data: 01 de Junho de 2016

-- Esquema SEFAZ_PAR
ALTER SESSION SET CURRENT_SCHEMA = SEFAZ_PAR;

-- Deleta a tabela TA_ATIVIDADE_ECONOMICA
DROP TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA CASCADE CONSTRAINTS;

CREATE TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA (
	CODIGO_CNAE 			VARCHAR2(9) CONSTRAINT NN_ATIVIDADE_ECONOMICA1 NOT NULL,
	DESCRICAO_CNAE 			VARCHAR2(250) CONSTRAINT NN_ATIVIDADE_ECONOMICA2 NOT NULL,
	CODIGO_SUPERIOR_CNAE 	CHAR(7) CONSTRAINT NN_ATIVIDADE_ECONOMICA3 NOT NULL,
	ID_GRUPO_CNAE 			NUMBER(2) CONSTRAINT NN_ATIVIDADE_ECONOMICA4 NOT NULL,
	PORCENTAGEM_CNAE 		NUMBER(3) CONSTRAINT NN_ATIVIDADE_ECONOMICA5 NOT NULL,
	SITUACAO_CNAE 			CHAR(1) DEFAULT 'A' CONSTRAINT NN_ATIVIDADE_ECONOMICA6 NOT NULL,
	USUARIO_INSERCAO 		VARCHAR2(30) CONSTRAINT NN_ATIVIDADE_ECONOMICA7 NOT NULL,
	DATA_INSERCAO 			DATE CONSTRAINT NN_ATIVIDADE_ECONOMICA8 NOT NULL,
	USUARIO_ALTERACAO 		VARCHAR2(30),
	DATA_ALTERACAO 			DATE,
	REGISTRO_EXCLUIDO		CHAR(1) DEFAULT 'N' CONSTRAINT NN_ATIVIDADE_ECONOMICA9 NOT NULL,
	USUARIO_EXCLUSAO 		VARCHAR2(30),
	DATA_EXCLUSAO			TIMESTAMP
);

ALTER TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA ADD CONSTRAINT PK_ATIVIDADE_ECONOMICA PRIMARY KEY (CODIGO_CNAE);
ALTER TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA ADD CONSTRAINT CK_ATIVIDADE_ECONOMICA1 CHECK (PORCENTAGEM_CNAE between 0 and 100);
ALTER TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA ADD CONSTRAINT CK_ATIVIDADE_ECONOMICA2 CHECK (SITUACAO_CNAE IN ('A','D'));
ALTER TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA ADD CONSTRAINT CK_ATIVIDADE_ECONOMICA3 CHECK (REGISTRO_EXCLUIDO IN ('S','N'));

COMMENT ON TABLE SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA IS 'Tabela do Classificação Nacional de Atividades Econômicas - CNAE';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.CODIGO_CNAE IS 'Código CNAE com subdivisão';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.DESCRICAO_CNAE IS 'Descrição CNAE';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.CODIGO_SUPERIOR_CNAE IS 'Código CNAE sem subdivisão';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.ID_GRUPO_CNAE IS 'Identificação Grupo CNAE';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.PORCENTAGEM_CNAE IS 'Porcentagem CNAE';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.SITUACAO_CNAE IS 'Situação CNAE';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.USUARIO_INSERCAO IS 'Usuário que inseriu registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.DATA_INSERCAO IS 'Data que inseriu registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.USUARIO_ALTERACAO IS 'Usuário que alterou registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.DATA_ALTERACAO IS 'Data que alterou o registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.REGISTRO_EXCLUIDO IS 'Está excluido?';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.USUARIO_EXCLUSAO IS 'Usuário que exclusou registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.DATA_EXCLUSAO IS 'Data que exclusou o registro';