CREATE OR REPLACE PROCEDURE SEFAZ_SEG.CRIAR_TRIGGER_AUDIT(NOME_ESQUEMA VARCHAR2, NOME_TABELA VARCHAR2)
/*
PROGRAMA:         Para criar trigger de auditoria por tabela
AUTOR:            Ing. Juan León Solis (juanleonsolis@gmail.com)
DATA_DE_CRIAÇÃO:  16-novembro-2015
DESCRIPÇÃO:       O seguinte programa permite criar um trigger de auditoria de acordo aos parametros
de entrada (esquema, nome_tabela). O trigger gerado será salvado no diretório "file_repo". Este trigger
só funciona para alteração, vai buscar a diferença nos valores do registro antes da atualização e os 
novos valores. O resultado será salvado no formato XML na tabela OPERACOES_AUDITADAS. No caso de erro, 
o detalhe será salvado na tabela ERRO_PROCESSO_AUDITORIA

O procedimento faz o seguinte:

1.- Verifica se existe a tabela de acordo aos parâmetros de entrada do procedimiento.
2.- Lê o dicionário de dados para obter os metadados (colunas) da tabela.
3.- Abre um arquivo na memória para gerar o trigger.
4.- Percorre cada uma das colunas da tabela e vai gerando o trigger se a operacao e uma alteracao
5.- Salva o arquivo no diretório "file_repo" con o sufixo "_AUD"
*/

IS
existe_tabela int:=0;
quantidade_colunas int:=0;
table_not_exists exception;
columns_not_exists exception;
nome_arquivo varchar2(200):=null;
triggerFileHandler UTL_FILE.FILE_TYPE;
triggerMemoryHandler UTL_FILE.FILE_TYPE;
arquivoLinha  VARCHAR2(250):=null;
arquivoTotal  VARCHAR2(32767):=null;
BEGIN

  BEGIN
        SELECT  1
        INTO    existe_tabela
        FROM    ALL_OBJECTS
        WHERE   owner=NOME_ESQUEMA and object_type='TABLE' and object_name=NOME_TABELA;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE table_not_exists;
  END;
  
  BEGIN 
        SELECT COUNT(column_name)
        INTO   quantidade_colunas
        FROM   all_tab_columns 
        WHERE  OWNER=NOME_ESQUEMA AND TABLE_NAME=NOME_TABELA AND 
               COLUMN_NAME NOT IN ('USUARIO_INSERCAO','DATA_INSERCAO','USUARIO_ALTERACAO','DATA_ALTERACAO','USUARIO_EXCLUSAO','DATA_EXCLUSAO') AND
               DATA_TYPE in ('CHAR', 'DATE', 'FLOAT', 'LONG', 'NCHAR', 'NUMBER', 'NVARCHAR2', 'VARCHAR2', 'TIMESTAMP(6)', 'TIMESTAMP(9)');
        
        IF (quantidade_colunas=0)
          THEN RAISE columns_not_exists;
        END IF;
  END;
  
  nome_arquivo:=lower(NOME_ESQUEMA||'_'||SUBSTR(NOME_TABELA,1,26)||'_AUD'||to_char(sysdate,'YYYYMMDDHH24MISS')||'.sql');
  
  triggerFileHandler := UTL_FILE.FOPEN('FILE_REPO',nome_arquivo, 'w');
  
  /* Criando o trigger*/
  UTL_FILE.put_line(triggerFileHandler,'CREATE OR REPLACE TRIGGER '||NOME_ESQUEMA||'.'||SUBSTR(NOME_TABELA,1,26)||'_AUD');
  UTL_FILE.put_line(triggerFileHandler,'BEFORE INSERT OR UPDATE OR DELETE ON '||NOME_ESQUEMA||'.'||NOME_TABELA);
  UTL_FILE.put_line(triggerFileHandler,'FOR EACH ROW');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'/*');
  UTL_FILE.put_line(triggerFileHandler,'CRIACAO DO TRIGGER PARA AUDITAR AS ALTERACOES DA TABELA');
  UTL_FILE.put_line(triggerFileHandler,'DATA CRIACAO: '||TO_CHAR(SYSDATE,'dd/mm/yyyy'));
  UTL_FILE.put_line(triggerFileHandler,'*/');
  UTL_FILE.put_line(triggerFileHandler,'DECLARE');
  UTL_FILE.put_line(triggerFileHandler,'v_nome_computador     varchar2(20) := substr(rtrim(ltrim(USERENV(''TERMINAL''))),1,20);');
  UTL_FILE.put_line(triggerFileHandler,'v_user_so             varchar2(20) := substr(sys_context(''USERENV'',''OS_USER''),1,20);');
  UTL_FILE.put_line(triggerFileHandler,'v_ip                  varchar2(20):=substr(sys_context(''USERENV'',''IP_ADDRESS''),1,20);');
  UTL_FILE.put_line(triggerFileHandler,'v_programa            sys.v_$session.program%TYPE;');
  UTL_FILE.put_line(triggerFileHandler,'v_usuario             varchar2(30);');
  UTL_FILE.put_line(triggerFileHandler,'v_data                 timestamp:=current_timestamp;');
  UTL_FILE.put_line(triggerFileHandler,'nova_entrada           varchar2(32767):=null;');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'BEGIN');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'v_usuario:= user;');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'IF DELETING THEN');
  UTL_FILE.put_line(triggerFileHandler,'raise_application_error(-20099, v_usuario||'' não é permitido excluir registro da tabela: '||NOME_ESQUEMA||'.'||NOME_TABELA||''');');
  UTL_FILE.put_line(triggerFileHandler,'END IF;');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'SELECT program INTO v_programa');
  UTL_FILE.put_line(triggerFileHandler,'FROM  sys.v_$session');
  UTL_FILE.put_line(triggerFileHandler,'WHERE audsid = USERENV(''SESSIONID'') AND audsid != 0  /* Do not Check SYS Connections */');
  UTL_FILE.put_line(triggerFileHandler,'AND rownum = 1;  /* Parallel processes will have the same AUDSID */');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'IF INSERTING AND :NEW.USUARIO_INSERCAO IS NULL THEN');
  UTL_FILE.put_line(triggerFileHandler,':NEW.USUARIO_INSERCAO:=v_usuario;');
  UTL_FILE.put_line(triggerFileHandler,':NEW.DATA_INSERCAO:=v_data;');
  UTL_FILE.put_line(triggerFileHandler,'END IF;');
  UTL_FILE.put_line(triggerFileHandler,'');
  UTL_FILE.put_line(triggerFileHandler,'IF UPDATING THEN');
  UTL_FILE.put_line(triggerFileHandler,'IF (:NEW.REGISTRO_EXCLUIDO=''S'' AND :NEW.USUARIO_EXCLUSAO IS NULL)');
  UTL_FILE.put_line(triggerFileHandler,'THEN');
  UTL_FILE.put_line(triggerFileHandler,'BEGIN');
  UTL_FILE.put_line(triggerFileHandler,':NEW.USUARIO_EXCLUSAO:=v_usuario;');
  UTL_FILE.put_line(triggerFileHandler,':NEW.DATA_EXCLUSAO:=v_data;');
  UTL_FILE.put_line(triggerFileHandler,'END;');
  UTL_FILE.put_line(triggerFileHandler,'ELSE IF :NEW.USUARIO_ALTERACAO IS NULL THEN');
  UTL_FILE.put_line(triggerFileHandler,'BEGIN');
  UTL_FILE.put_line(triggerFileHandler,':NEW.USUARIO_ALTERACAO:=v_usuario;');
  UTL_FILE.put_line(triggerFileHandler,':NEW.DATA_ALTERACAO:=v_data;');
  UTL_FILE.put_line(triggerFileHandler,':NEW.USUARIO_EXCLUSAO:=NULL;');
  UTL_FILE.put_line(triggerFileHandler,':NEW.DATA_EXCLUSAO:=NULL;');
  UTL_FILE.put_line(triggerFileHandler,'END; ');
  UTL_FILE.put_line(triggerFileHandler,'END IF; ');
  UTL_FILE.put_line(triggerFileHandler,'/* lista as colunas da tabela */');
  UTL_FILE.put_line(triggerFileHandler, 'nova_entrada:=''<?xml version="1.0" encoding="UTF-8"?>'';');
  UTL_FILE.put_line(triggerFileHandler, 'nova_entrada:=nova_entrada||''<colunas rowid="''||:OLD.ROWID||''">'';');
  FOR colunas IN (SELECT column_name
    FROM   all_tab_columns 
    WHERE  OWNER=NOME_ESQUEMA AND TABLE_NAME=NOME_TABELA AND 
            COLUMN_NAME NOT IN ('USUARIO_INSERCAO','DATA_INSERCAO','USUARIO_ALTERACAO','DATA_ALTERACAO','USUARIO_EXCLUSAO','DATA_EXCLUSAO') and
            DATA_TYPE in ('CHAR', 'DATE', 'FLOAT', 'LONG', 'NCHAR', 'NUMBER', 'NVARCHAR2', 'VARCHAR2', 'TIMESTAMP(6)', 'TIMESTAMP(9)')
            ORDER BY column_id)
  LOOP
    UTL_FILE.put_line(triggerFileHandler, 'IF :OLD.'||colunas.COLUMN_NAME||' <> :NEW.'||colunas.COLUMN_NAME);
    UTL_FILE.put_line(triggerFileHandler, 'THEN ');
    UTL_FILE.put_line(triggerFileHandler, '  nova_entrada:=nova_entrada||''<col name="'||colunas.column_name||'"><old>''||:OLD.'||colunas.column_name||'||''</old><new>''||:NEW.'||colunas.column_name||'||''</new></col>'';');
    UTL_FILE.put_line(triggerFileHandler, 'END IF;');
  END LOOP;
  UTL_FILE.put_line(triggerFileHandler, 'nova_entrada:=nova_entrada||''</colunas>'';');
  UTL_FILE.put_line(triggerFileHandler,' INSERT INTO SEFAZ_SEG.TA_OPERACAO_AUDITADA (');
  UTL_FILE.put_line(triggerFileHandler,' IDENTIFICACAO_AUDITORIA,');
  UTL_FILE.put_line(triggerFileHandler,' NOME_TABELA,');
  UTL_FILE.put_line(triggerFileHandler,' NOME_COMPUTADOR,');
  UTL_FILE.put_line(triggerFileHandler,' USUARIO_SISTEMA_OPERACIONAL,');
  UTL_FILE.put_line(triggerFileHandler,' USUARIO_BANCO_DADOS,');
  UTL_FILE.put_line(triggerFileHandler,' APLICACAO_USADA,');
  UTL_FILE.put_line(triggerFileHandler,' ENDERECO_IP,');
  UTL_FILE.put_line(triggerFileHandler,' DATA_OPERACAO,');
  UTL_FILE.put_line(triggerFileHandler,' COLUNAS_ALTERADAS,');
  UTL_FILE.put_line(triggerFileHandler,' USUARIO_INSERCAO,');
  UTL_FILE.put_line(triggerFileHandler,' DATA_INSERCAO)');
  UTL_FILE.put_line(triggerFileHandler,' VALUES(');
  UTL_FILE.put_line(triggerFileHandler,' SEFAZ_SEG.seq_operacoes_auditadas.NEXTVAL,');
  UTL_FILE.put_line(triggerFileHandler,' '''||NOME_TABELA||''',');
  UTL_FILE.put_line(triggerFileHandler,' v_nome_computador,');
  UTL_FILE.put_line(triggerFileHandler,' v_user_so,');
  UTL_FILE.put_line(triggerFileHandler,' v_usuario,');
  UTL_FILE.put_line(triggerFileHandler,' v_programa,');
  UTL_FILE.put_line(triggerFileHandler,' v_ip,');
  UTL_FILE.put_line(triggerFileHandler,' v_data,');
  UTL_FILE.put_line(triggerFileHandler,' nova_entrada,');
  UTL_FILE.put_line(triggerFileHandler,' SYSDATE,');
  UTL_FILE.put_line(triggerFileHandler,' USER);');
  UTL_FILE.put_line(triggerFileHandler,'nova_entrada:=null;');
  UTL_FILE.put_line(triggerFileHandler,'END IF; ');
  UTL_FILE.put_line(triggerFileHandler,'EXCEPTION ');
  UTL_FILE.put_line(triggerFileHandler,' WHEN OTHERS THEN '); 
  UTL_FILE.put_line(triggerFileHandler,'   SEFAZ_SEG.salvar_log_erro(''SEGURIDAD.CRIAR_TRIGGER_AUDIT'',SQLCODE,SUBSTR(SQLERRM,1,64),''ACONTECEU UM ERRO''); ');
  UTL_FILE.put_line(triggerFileHandler,' IF SQLCODE=-20099 THEN'); 
  UTL_FILE.put_line(triggerFileHandler,'   raise_application_error(-20099, v_usuario||'' não é permitido excluir registro da tabela: SEGURIDAD.EXEMPLO'');'); 
  UTL_FILE.put_line(triggerFileHandler,' END IF;'); 
  UTL_FILE.put_line(triggerFileHandler,'END; ');
  --UTL_FILE.put_line(triggerFileHandler,'/');
  UTL_FILE.FCLOSE(triggerFileHandler);

  
  /* Criar o trigger diretamente na tabela */
  arquivoTotal:='';
  triggerMemoryHandler := utl_file.fopen('FILE_REPO',nome_arquivo, 'R');
  LOOP
    BEGIN
      utl_file.get_line(triggerMemoryHandler, arquivoLinha);
      arquivoTotal:=arquivoTotal||arquivoLinha||' ';
    EXCEPTION
      WHEN OTHERS THEN
        EXIT;
    END;
  END LOOP;
  utl_file.fclose(triggerMemoryHandler);
  dbms_output.put_line(arquivoTotal);
  execute immediate arquivoTotal;
  
  --UTL_FILE.FFLUSH(triggerFileHandler);
  --UTL_FILE.FFLUSH(triggerMemoryHandler);
  
 EXCEPTION
  WHEN table_not_exists THEN 
    SEFAZ_SEG.salvar_log_erro('SEFAZ_SEG.CRIAR_TRIGGER_AUDIT',SQLCODE, SUBSTR(SQLERRM,1,64),'A TABELA '||NOME_ESQUEMA||'.'||NOME_TABELA||' NAO EXISTE');
  WHEN columns_not_exists THEN 
    SEFAZ_SEG.salvar_log_erro('SEFAZ_SEG.CRIAR_TRIGGER_AUDIT',-20001,'EXCEPCAO PROPIA', 'A TABELA '||NOME_ESQUEMA||'.'||NOME_TABELA||' NAO TEM COLUNAS');
  WHEN utl_file.invalid_path THEN
    SEFAZ_SEG.salvar_log_erro('SEFAZ_SEG.CRIAR_TRIGGER_AUDIT',-20000,'EXCEPCAO PROPIA', 'ERRO: PATH invalido para arquivo');
  WHEN OTHERS THEN 
    SEFAZ_SEG.salvar_log_erro('SEFAZ_SEG.CRIAR_TRIGGER_AUDIT',SQLCODE,SUBSTR(SQLERRM,1,64),'ACONTECEU UM ERRO');
END;


