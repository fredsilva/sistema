-- Script para inserir os módulos, aplicações e opções do sistema respectivamente em TA_MODULO_SISTEMA, TA_APLICACAO_MODULO e TA_OPCAO_APLICACAO
-- Autor: Cristiano Luis Schwaab
-- Data: 08 de agosto de 2016

-- Módulo PAR
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'PAR', 'parâmetros do Sistema', 'admin', current_timestamp);

-- Aplicação GESTÃO de PAR
INSERT INTO SEFAZ_SEG.TA_APLICACAO_MODULO (IDENTIFICACAO_APLICACAO_MODULO, IDENTIFICACAO_MODULO_SISTEMA, DESCRICAO_APLICACAO_MODULO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT SEFAZ_SEG.SQ_APLICACAO_MODULO.NEXTVAL, IDENTIFICACAO_MODULO_SISTEMA, 'GESTÃO', user, current_timestamp FROM SEFAZ_SEG.TA_MODULO_SISTEMA WHERE ABREVIACAO_MODULO = 'PAR';

-- Opções do Sistema
INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'PARUC0010', 'par/gestao/manutencao-parametros.jsf', 'Manutenção dos Parâmetros Gerais do Sistema', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'PAR' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'PARUC0020', 'par/gestao/consulta-parametros.jsf', 'Consulta dos Parâmetros Gerais do Sistema', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'PAR' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'SEGUC0430', 'seg/gestao/manter-papel-sistema.jsf', 'Manutenção dos papéis (roles)', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'SEG' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'SEGUC0440', 'seg/gestao/manter-perfil-sistema.jsf', 'Manutenção do cadastro de perfil', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'SEG' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'SEGUC0450', 'seg/gestao/atribuir-perfil-usuario.jsf', 'Atribuição de perfis aos usuários', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'SEG' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT 
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'SEGUC0460', 'seg/gestao/manter-procuracao.jsf', 'Procuração/Retirada das opções do sistema', 'url_wiki', user, current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'SEG' AND DESCRICAO_APLICACAO_MODULO = 'GESTÃO';