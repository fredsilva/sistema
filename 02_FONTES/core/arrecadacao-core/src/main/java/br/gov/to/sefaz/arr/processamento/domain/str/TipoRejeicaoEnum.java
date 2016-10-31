package br.gov.to.sefaz.arr.processamento.domain.str;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Rejeições de Arquivos STR por código e descrição.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 10:58:00
 */
public enum TipoRejeicaoEnum implements EnumCodeLabel<Integer> {

    DT_ARREC_INF_RECP_INVALIDA(1, "Data de Juliana de Arrecadação Inferior a Data de Recepção do arquivo ou inválida."),
    COD_NOSSO_NR_N_LOCALIZADO(2, "Código NOSSO NUMERO Não Localizado."),
    COD_BANCO_ARREC_INVALIDO(3, "Código do Banco Arrecadador inválido ou Não Localizado."),
    COD_AGENCIA_ARREC_INVALIDO(4, "Código da Agência Arrecadadora inválida ou Não Localizada."),
    TP_VL_INF_INVALIDO(5, "Tipo de Valor Informativo inválido."),
    TP_DOC_INVALIDO(6, "Tipo de documento inválido."),
    TP_RECEITA_INVALIDO(7, "Tipo de Receita inválido."),
    TP_DOC_N_CAD(8, "Tipo de Documento não cadastrado."),
    TP_RECEITA_N_CAD(9, "Tipo de Receita não cadastrado."),
    TP_VL_INF_N_CAD(10, "Tipo de Valor informativo não cadastrado."),
    VERSAO_BARRA_RECEP_DIF_HOMOL(11, "Versão da Barra Recepcionada Diferente da Atual Homologada."),
    CNPJ_N_LOCALIZADO(12, "CPF/CNPJ/Inscrição Estadual/Renavam não Localizado."),
    SIS_SEFAZ_GER_BARRA_N_LOCALIZADO(13, "Sistema da SEFAZ, gerador da Barra não Identificado."),
    NSU_DUPLICADO(14, "NSU Duplicado."),
    ERRO_N_CLASSIFICADO(15, "Erro Não Classificado, Verificar Layout da BARRA."),
    DT_REPASSE_FIN_INVALIDA(16, "Data do Repasse Financeiro inválido."),
    DT_REPASSE_FIN_MAIOR_DT_ATUAL(17, "Data do Repasse Financ. maior que a data corrente."),
    DT_REPASSE_FIN_MENOR_DT_ARREC(18, "Data do Repasse Financ. menor que dta arrecadação."),
    QT_DOCS_DIVERG_ARREC(19, "Quantidade dos Documentos Repassados divergente do Obtido na Arrecadação."),
    VL_LANC_DIVERG_VL_INF_ACUMULADO(20, "Valor do Lançamento Divergente do Valor Informativo Acumulado."),
    NR_CONTROLE_STR_JA_PROCESSADO(21, "Numero de Controle do STR já foi Processado."),
    COD_CONV_BANC_N_LOCALIZADO(22, "Código do Convênio Bancário  não Localizado."),
    COD_SEFAZ_DIF_027(23, "Código da SEFAZ no Arquivo STR20 diferente de 027"),
    ARQ_HEADER(24, "Arquivo sem header"),
    DATA_GERACAO_MAIOR(25, "Data de geração do arquivo maior que data atual"),
    DATA_GERACAO_INVALIDA(26, "Data de geração do arquivo inválida"),
    VERSAO_LEIAUTE_INVALIDA(27, "Versão do leiaute do arquivo inválida"),
    CODIGO_REG_INVALIDO(28, "Código de registro inválido"),
    NR_SEQ_PROCESSADO(29, "Número sequencial do arquivo já processado"),
    NR_SEQ_N_SEQ(30, "Número Sequencial do arquivo não sequenciado"),
    BANCO_N_CENTRALIZADOR(31, "Banco não é centralizador do repasse financeiro"),
    CONVENIO_N_CADASTRADO(32, "Convênio não Cadastrado para o Banco"),
    COD_REMESSA_INVALIDO(33, "Código da Remessa Inválido"),
    BANCO_SEM_CONVENIOS(34, "Banco Informado não possui Convênios Bancários"),
    TRAILLER_DIVERGENTE(35, "Arquivo de Pagamento com Divergência entre linhas Detalhe processadas e os valores "
            + "informados na linha Trailer");


    private Integer code;
    private String label;

    TipoRejeicaoEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * Encontra o {@link TipoRejeicaoEnum} correspondente ao código passado como parâmetro.
     *
     * @param code codigo da rejeição.
     * @return {@link TipoRejeicaoEnum} correspondente ao código passado como parâmetro.
     */
    public static TipoRejeicaoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoRejeicaoEnum: codigo="
                        + code));
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
