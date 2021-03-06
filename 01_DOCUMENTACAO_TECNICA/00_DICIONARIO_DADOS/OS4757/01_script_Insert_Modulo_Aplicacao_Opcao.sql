﻿-- Script para inserir os módulos, aplicações e opções do sistema respectivamente em TA_MODULO_SISTEMA, TA_APLICACAO_MODULO e TA_OPCAO_APLICACAO
-- Autor: Cristiano Luis Schwaab
-- Data: 20 de maio de 2016

-- Módulos do Sistema
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'ARR', 'Arrecadação', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'CCI', 'Cadastro de Contribuinte', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'CCC', 'Conta Corrente', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'COB', 'Cobrança', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'COM', 'Consulta', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'ECF', 'Equipamento Emissor de Cupom Fiscal', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'FIE', 'Fiscalização Estabelecimento', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'FTR', 'Fiscalização de Trânsito de Mercadorias', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'GED', 'Gerenciamento Eletrônico de Documentos', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'ISE', 'Isenções', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'NFE', 'NFE', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'OTR', 'Outras Receitas', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'REE', 'Regimes Especiais', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'RES', 'Restituições', 'admin', current_timestamp);
INSERT INTO SEFAZ_SEG.TA_MODULO_SISTEMA (IDENTIFICACAO_MODULO_SISTEMA, ABREVIACAO_MODULO, DESCRICAO_MODULO_SISTEMA, USUARIO_INSERCAO, DATA_INSERCAO) VALUES (SEFAZ_SEG.SQ_MODULO_SISTEMA.NEXTVAL, 'SEG', 'Segurança do Sistema', 'admin', current_timestamp);

-- Aplicações do Módulo
INSERT INTO SEFAZ_SEG.TA_APLICACAO_MODULO (IDENTIFICACAO_APLICACAO_MODULO, IDENTIFICACAO_MODULO_SISTEMA, DESCRICAO_APLICACAO_MODULO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT SEFAZ_SEG.SQ_APLICACAO_MODULO.NEXTVAL, IDENTIFICACAO_MODULO_SISTEMA, 'PARÂMETROS', 'admin', current_timestamp FROM SEFAZ_SEG.TA_MODULO_SISTEMA WHERE ABREVIACAO_MODULO = 'ARR';

-- Opções das da Aplicação de Parametrização do Módulo de Arrecadação
INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0910', 'arr/parametros/bancos.jsf', 'Agentes Bancários', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0920', 'arr/parametros/convenios-arrec.jsf', 'Convênios da Arrecadação', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0930', 'arr/parametros/tipo-grupos-cnaes.jsf', 'Grupos de CNAE''s', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0940', 'arr/parametros/plano-contas.jsf', 'Plano de Contas', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0950', 'arr/parametros/receitas.jsf', 'Receitas e Transferências', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0960', 'arr/parametros/pedido-areas.jsf', 'Tipos de Pedidos de Áreas', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0970', 'arr/parametros/tipo-pedido-documento.jsf', 'Tipos de Pedidos de Documento e Ações', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';

INSERT INTO SEFAZ_SEG.TA_OPCAO_APLICACAO (IDENTIFICACAO_OPCAO_APLICACAO, IDENTIFICACAO_APLICACAO_MODULO, CASO_USO, OPCAO_URL, DESCRIPCAO_OPCAO, AJUDA_OPCAO, USUARIO_INSERCAO, DATA_INSERCAO) SELECT
SEFAZ_SEG.SQ_OPCAO_APLICACAO.NEXTVAL, IDENTIFICACAO_APLICACAO_MODULO, 'ARRUC0980', 'arr/parametros/tipo-rejeicao-arquivo.jsf', 'Tipos de Rejeições dos Arquivos', 'url_wiki', 'admin', current_timestamp FROM SEFAZ_SEG.TA_APLICACAO_MODULO am INNER JOIN SEFAZ_SEG.TA_MODULO_SISTEMA sm ON am.IDENTIFICACAO_MODULO_SISTEMA = sm.IDENTIFICACAO_MODULO_SISTEMA
WHERE ABREVIACAO_MODULO = 'ARR' AND DESCRICAO_APLICACAO_MODULO = 'PARÂMETROS';