CREATE OR REPLACE PROCEDURE SEFAZ_SEG.SALVAR_LOG_ERRO ( V_NOME_PROGRAMA     VARCHAR2,
												        V_CODIGO_ERRO_SQL   NUMBER,
												        V_ERRO_MENSAGEM_SQL VARCHAR2,
												        V_ERRO_MENSAGEM     VARCHAR2)
/*
PROGRAMA:         Para salvar os logs de erro das triggers de auditoria
AUTOR:            Cristiano Luis Schwaab
DATA_DE_CRIAÇÃO:  06-junho-2016
DESCRIPÇÃO:       O seguinte programa salva um log na tabela SEFAZ_SEG.TA_ERRO_PROCESSO_AUDITORIA com os erros encontrados nas triggers de auditoria.
*/

IS
BEGIN
	INSERT INTO SEFAZ_SEG.TA_ERRO_PROCESSO_AUDITORIA ( IDENTIFICACAO_ERRO_LOG, 
													   NOME_PROGRAMA, 
													   CODIGO_ERRO_SQL, 
													   ERRO_MENSAGEM_SQL, 
													   ERRO_MENSAGEM, 
													   DATA_ERRO, 
													   USUARIO_INSERCAO, 
													   DATA_INSERCAO, 
													   USUARIO_ALTERACAO, 
													   DATA_ALTERACAO, 
													   REGISTRO_EXCLUIDO, 
													   USUARIO_EXCLUSAO, 
													   DATA_EXCLUSAO )
											  VALUES ( SQ_ERRO_PROCESSO_AUDITORIA.NEXTVAL,
													   V_NOME_PROGRAMA,
													   V_CODIGO_ERRO_SQL,
													   V_ERRO_MENSAGEM_SQL,
													   V_ERRO_MENSAGEM, 
													   SYSDATE,
													   USER,
													   SYSDATE,
													   NULL,
													   NULL,
													   'N',
													   NULL,
													   NULL
													   );

	EXCEPTION
	WHEN OTHERS THEN
		dbms_output.put_line(Sqlerrm);

END;
/