-- Script para inserir os Tipos de Rejeição de Arquivos
-- Autor: Cristiano Luis Schwaab
-- Data: 13 de junho de 2016

delete from SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS;

insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(01, 'Data de Juliana de Arrecadação Inferior a Data de Recepção do arquivo ou inválida!', 1, 'admin', sysdate, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(02, 'Código NOSSO NUMERO Não Localizado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(03, 'Código do Banco Arrecadador inválido ou Não Localizado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(04, 'Código da Agência Arrecadadora inválida ou Não Localizada', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(05, 'Tipo de Valor Informativo inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(06, 'Tipo de documento inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(07, 'Tipo de Receita inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(08, 'Tipo de Documento não cadastrado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(09, 'Tipo de Receita não cadastrado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(10, 'Tipo de Valor informativo não cadastrado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(11, 'Versão da Barra Recepcionada Diferente da Atual Homologada', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(12, 'CPF/CNPJ/Inscrição Estadual/ Renavan não Localizado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(13, 'Sistema da SEFAZ, gerador da Barra não Identificado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(14, 'NSU Duplicado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(15, 'Erro Não Classificado, Verificar Layout da BARRA', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(16, 'Data do Repasse Financeiro inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(17, 'Data do Repasse Financ. maior que a data corrente', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(18, 'Data do Repasse Financ. menor que dta arrecadação', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(19, 'Quantidade dos Documentos Repassados divergente do Obtido na Arrecadação', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(20, 'Valor do Lançamento Divergente do Valor Informativo Acumulado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(21, 'Numero de Controle do STR já foi Processado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(22, 'Código do Convênio Bancário  não Localizado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(23, 'Código da SEFAZ no Arquivo STR20 diferente de 027', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(24, 'Arquivo sem header', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(25, 'Data de geração do arquivo maior que data atual', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(26, 'Data de geração do arquivo inválida', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(27, 'Versão do leiaute do arquivo inválida', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(28, 'Código de registro inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(29, 'Número sequencial do arquivo já processado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(30, 'Número Sequencial do arquivo não sequenciado', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(31, 'Banco não é centralizador do repasse financeiro', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(32, 'Convênio não Cadastrado para o Banco', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(33, 'Código da Remessa Inválido', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(34, 'Banco Informado não possui Convênios Bancários', 1, 'admin', sysdate);
insert into SEFAZ_ARR.TA_TIPO_REJEICAO_ARQUIVOS(ID_CODIGO_REJEICAO, MOTIVO_REJEICAO, SITUACAO, USUARIO_INSERCAO, DATA_INSERCAO) values(35, 'Arquivo de Pagamento com Divergência entre linhas Detalhe processadas e os valores informados na linha Trailer', 1, 'admin', sysdate);