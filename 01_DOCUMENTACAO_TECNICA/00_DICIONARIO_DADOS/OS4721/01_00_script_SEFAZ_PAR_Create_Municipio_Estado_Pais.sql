-- Script para criar as tabelas TA_PAIS, TA_ESTADO, TA_MUNICIPIO no esquema SEFAZ_PAR com as PK, CK, FK e comentarios 
-- Autor: Eng. Juan León Solis
-- Data: 27/Abril/2016

-- Esquema SEFAZ_PAR
ALTER SESSION SET CURRENT_SCHEMA = SEFAZ_PAR;

-- tabela SEFAZ_PAR.TA_PAIS
CREATE TABLE SEFAZ_PAR.TA_PAIS
(
	CODIGO_PAIS 		  varchar2(3),
	NOME_PAIS 			  varchar2(50),
	USUARIO_INSERCAO      varchar2(30), 
	DATA_INSERCAO         timestamp, 
	USUARIO_ALTERACAO     varchar2(30), 
	DATA_ALTERACAO        timestamp, 
	REGISTRO_EXCLUIDO     char(1) default 'N', 
	USUARIO_EXCLUSAO      varchar2(30), 
	DATA_EXCLUSAO         timestamp
);

ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT PK_PAIS PRIMARY KEY (CODIGO_PAIS);
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT CK_PAIS1 CHECK (REGISTRO_EXCLUIDO IN ('S', 'N')); 
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT NN_PAIS1 CHECK (CODIGO_PAIS IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT NN_PAIS2 CHECK (NOME_PAIS IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT NN_PAIS3 CHECK (USUARIO_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT NN_PAIS4 CHECK (DATA_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_PAIS ADD CONSTRAINT NN_PAIS5 CHECK (REGISTRO_EXCLUIDO IS NOT NULL); 

COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.CODIGO_PAIS IS 'Sigla do País';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.NOME_PAIS IS 'Nome do país';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.USUARIO_INSERCAO IS 'Usuário inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.DATA_INSERCAO IS 'Data - hora inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.USUARIO_ALTERACAO IS 'Usuário alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.DATA_ALTERACAO IS 'Data - hora alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.REGISTRO_EXCLUIDO IS 'Identificador do registro excluido (S) Sim ou (N) Não';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.USUARIO_EXCLUSAO IS 'Usuário que fez a exclusão';
COMMENT ON COLUMN SEFAZ_PAR.TA_PAIS.DATA_EXCLUSAO IS 'Data - hora exclusão do registro';

-- tabela SEFAZ_PAR.TA_ESTADO

CREATE TABLE SEFAZ_PAR.TA_ESTADO
(
	UNIDADE_FEDERACAO 			char(2),
	NOME_ESTADO 				varchar2(40),
	ALIQUOTA_ORIGEM_ENTRADA 	number(3),
	VALOR_AGREGADO_FARMACEUTICO number(6,2),
	CEP_GERAL_UF 				varchar2(10),
	CODIGO_REGIAO 				number(1),
	CARGA_TRIBUTARIA_ORIGEM 	number(3),
	USUARIO_INSERCAO      		varchar2(30), 
	DATA_INSERCAO         		timestamp, 
	USUARIO_ALTERACAO     		varchar2(30), 
	DATA_ALTERACAO        		timestamp, 
	REGISTRO_EXCLUIDO     		char(1) default 'N', 
	USUARIO_EXCLUSAO      		varchar2(30), 
	DATA_EXCLUSAO         		timestamp
);

ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT PK_ESTADO PRIMARY KEY (UNIDADE_FEDERACAO);
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT CK_ESTADO1 CHECK (REGISTRO_EXCLUIDO IN ('S', 'N'));
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO1 CHECK (UNIDADE_FEDERACAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO2 CHECK (NOME_ESTADO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO3 CHECK (ALIQUOTA_ORIGEM_ENTRADA IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO4 CHECK (VALOR_AGREGADO_FARMACEUTICO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO5 CHECK (CEP_GERAL_UF IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO6 CHECK (CODIGO_REGIAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO7 CHECK (CARGA_TRIBUTARIA_ORIGEM IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO8 CHECK (USUARIO_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO9 CHECK (DATA_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_ESTADO ADD CONSTRAINT NN_ESTADO10 CHECK (REGISTRO_EXCLUIDO IS NOT NULL); 

COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.UNIDADE_FEDERACAO IS 'UF';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.NOME_ESTADO IS 'Nome do Estado';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.ALIQUOTA_ORIGEM_ENTRADA IS 'Aliquota de Origem na Entrada';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.VALOR_AGREGADO_FARMACEUTICO IS 'Valor Agregado farmaceutico';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.CEP_GERAL_UF IS 'CEP geral da UF';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.CODIGO_REGIAO IS 'Código da região';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.CARGA_TRIBUTARIA_ORIGEM IS 'Carga tributária de origem';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.USUARIO_INSERCAO IS 'Usuário inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.DATA_INSERCAO IS 'Data - hora inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.USUARIO_ALTERACAO IS 'Usuário alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.DATA_ALTERACAO IS 'Data - hora alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.REGISTRO_EXCLUIDO IS 'Identificador do registro excluido (S) Sim ou (N) Não';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.USUARIO_EXCLUSAO IS 'Usuário que fez a exclusão';
COMMENT ON COLUMN SEFAZ_PAR.TA_ESTADO.DATA_EXCLUSAO IS 'Data - hora exclusão do registro';


-- tabela SEFAZ_PAR.TA_MUNICIPIO

CREATE TABLE SEFAZ_PAR.TA_MUNICIPIO
(
	CODIGO_IBGE 			number(7),
	CODIGO_MUNICIPIO 		number(7),
	NOME_MUNICIPIO 			varchar2(50),
	UNIDADE_FEDERACAO		char(2),
	CODIGO_MUNICIPIO_TOM 	number(4),
	CODIGO_MUNICIPIO_SERPRO number(9), 
	E_CAPITAL             	char(1) default 'N', 
	USUARIO_INSERCAO      	varchar2(30), 
	DATA_INSERCAO         	timestamp, 
	USUARIO_ALTERACAO     	varchar2(30), 
	DATA_ALTERACAO        	timestamp, 
	REGISTRO_EXCLUIDO     	char(1) default 'N', 
	USUARIO_EXCLUSAO      	varchar2(30), 
	DATA_EXCLUSAO         	timestamp
);

ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT PK_MUNICIPIO PRIMARY KEY (CODIGO_IBGE);
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT CK_MUNICIPIO1 CHECK (REGISTRO_EXCLUIDO IN ('S', 'N'));
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT CK_MUNICIPIO2 CHECK (E_CAPITAL IN ('S', 'N')); 
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT NN_MUNICIPIO1 CHECK (CODIGO_IBGE IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT NN_MUNICIPIO2 CHECK (E_CAPITAL IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT NN_MUNICIPIO3 CHECK (USUARIO_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT NN_MUNICIPIO4 CHECK (DATA_INSERCAO IS NOT NULL); 
ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT NN_MUNICIPIO5 CHECK (REGISTRO_EXCLUIDO IS NOT NULL); 

COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.CODIGO_IBGE IS 'Código Ibge';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.CODIGO_MUNICIPIO IS 'Código Município';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.NOME_MUNICIPIO IS 'Descrição';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.UNIDADE_FEDERACAO IS 'UF';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.CODIGO_MUNICIPIO_TOM IS 'Código município TOM';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.USUARIO_INSERCAO IS 'Usuário inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.DATA_INSERCAO IS 'Data - hora inserção do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.USUARIO_ALTERACAO IS 'Usuário alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.DATA_ALTERACAO IS 'Data - hora alteração do registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.REGISTRO_EXCLUIDO IS 'Identificador do registro excluido (S) Sim ou (N) Não';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.USUARIO_EXCLUSAO IS 'Usuário que fez a exclusão';
COMMENT ON COLUMN SEFAZ_PAR.TA_MUNICIPIO.DATA_EXCLUSAO IS 'Data - hora exclusão do registro';

ALTER TABLE SEFAZ_PAR.TA_MUNICIPIO ADD CONSTRAINT "FK_MUNICIPIO1" FOREIGN KEY (UNIDADE_FEDERACAO) REFERENCES SEFAZ_PAR.TA_ESTADO (UNIDADE_FEDERACAO);