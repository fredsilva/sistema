CREATE SEQUENCE SEFAZ_PAR.SQ_PARAMETRO_GERAL MINVALUE 1 START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL(
	ID_PARAMETRO_GERAL		number(10) CONSTRAINT NN_PARAMETRO_GERAL1 NOT NULL,
	NOME_PARAMETRO_GERAL	varchar2(50) CONSTRAINT NN_PARAMETRO_GERAL2 NOT NULL,
	OBJETIVO_PARAMETRO		varchar2(200)  CONSTRAINT NN_PARAMETRO_GERAL3 NOT NULL,
	TIPO_PARAMETRO_GERAL	char(1) DEFAULT 'E' CONSTRAINT NN_PARAMETRO_GERAL4 NOT NULL,
	CONTEUDO_VALORES		varchar2(4000) CONSTRAINT NN_PARAMETRO_GERAL5 NOT NULL,
	USUARIO_INSERCAO		varchar2(30) CONSTRAINT NN_PARAMETRO_GERAL6 NOT NULL, 
	DATA_INSERCAO			timestamp CONSTRAINT NN_PARAMETRO_GERAL7 NOT NULL, 
	USUARIO_ALTERACAO		varchar2(30), 
	DATA_ALTERACAO			timestamp, 
	REGISTRO_EXCLUIDO		char(1) default 'N' CONSTRAINT NN_PARAMETRO_GERAL8 NOT NULL, 
	USUARIO_EXCLUSAO		varchar2(30), 
	DATA_EXCLUSAO			timestamp
) TABLESPACE TBS_PAR_DADOS;

ALTER TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL ADD CONSTRAINT PK_PARAMETRO_GERAL PRIMARY KEY (ID_PARAMETRO_GERAL);
ALTER TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL ADD CONSTRAINT CK_PARAMETRO_GERAL1 CHECK (TIPO_PARAMETRO_GERAL IN ('E','D'));
ALTER TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL ADD CONSTRAINT CK_PARAMETRO_GERAL2 CHECK (REGISTRO_EXCLUIDO IN ('S','N'));
ALTER TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL ADD CONSTRAINT UQ_PARAMETRO_GERAL1 UNIQUE (NOME_PARAMETRO_GERAL);

COMMENT ON TABLE SEFAZ_PAR.TA_PARAMETRO_GERAL IS 'Tabela de Parâmetros Gerais do Sistema, podem ser valores estaticos ou dinamicos';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.ID_PARAMETRO_GERAL IS 'Identificação do Parâmetro Geral do Sistema';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.NOME_PARAMETRO_GERAL IS 'Nome do Parâmetro Geral do Sistema';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.OBJETIVO_PARAMETRO IS 'Objetivo do Parâmetro Geral do Sistema';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.TIPO_PARAMETRO_GERAL IS 'Tipo do Parâmetro Geral do Sistema. (E)statico ou (D)inamico';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.CONTEUDO_VALORES IS 'Conteudo de Valores ou SQL do Parâmetro Geral do Sistema';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.USUARIO_INSERCAO IS 'Usuário que inseriu registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.DATA_INSERCAO IS 'Data que inseriu registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.USUARIO_ALTERACAO IS 'Usuário que alterou registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.DATA_ALTERACAO IS 'Data que alterou o registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.REGISTRO_EXCLUIDO IS 'Está excluido?';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.USUARIO_EXCLUSAO IS 'Usuário que exclusou registro';
COMMENT ON COLUMN SEFAZ_PAR.TA_PARAMETRO_GERAL.DATA_EXCLUSAO IS 'Data que exclusou o registro';

CREATE SEQUENCE SEFAZ_PAR.SQ_PARAMETRO_GERAL MINVALUE 1 START WITH 1 INCREMENT BY 1 NOCACHE;

INSERT INTO SEFAZ_PAR.TA_PARAMETRO_GERAL (ID_PARAMETRO_GERAL, NOME_PARAMETRO_GERAL, OBJETIVO_PARAMETRO, TIPO_PARAMETRO_GERAL, CONTEUDO_VALORES, USUARIO_INSERCAO, DATA_INSERCAO, USUARIO_ALTERACAO, DATA_ALTERACAO, REGISTRO_EXCLUIDO, USUARIO_EXCLUSAO, DATA_EXCLUSAO )
VALUES (SEFAZ_PAR.SQ_PARAMETRO_GERAL.NEXTVAL, 'LISTAGEM_TIPO_USUARIO','LISTAGEM DOS TIPOS DE USUARIO DO SISTEMA','E','1,Contabilista|2,Contribuinte|3,Contribuinte outro estado|4,Funcionário Público',USER, CURRENT_TIMESTAMP, NULL, NULL,'N',NULL,NULL);

INSERT INTO SEFAZ_PAR.TA_PARAMETRO_GERAL (ID_PARAMETRO_GERAL, NOME_PARAMETRO_GERAL, OBJETIVO_PARAMETRO, TIPO_PARAMETRO_GERAL, CONTEUDO_VALORES, USUARIO_INSERCAO, DATA_INSERCAO, USUARIO_ALTERACAO, DATA_ALTERACAO, REGISTRO_EXCLUIDO, USUARIO_EXCLUSAO, DATA_EXCLUSAO )
VALUES (SEFAZ_PAR.SQ_PARAMETRO_GERAL.NEXTVAL, 'LISTAGEM_UF','LISTAGEM DOS ESTADOS DO BRASIL','D','SELECT UNIDADE_FEDERACAO AS CODIGO, NOME_ESTADO AS VALOR FROM SEFAZ_CAT.TA_ESTADO WHERE REGISTRO_EXCLUIDO=''N'';',USER, CURRENT_TIMESTAMP, NULL, NULL,'N',NULL,NULL);

SELECT * FROM SEFAZ_CAT.TA_PARAMETRO_GERAL;