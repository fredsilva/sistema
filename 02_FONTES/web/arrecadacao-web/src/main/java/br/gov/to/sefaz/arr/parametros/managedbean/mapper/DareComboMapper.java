package br.gov.to.sefaz.arr.parametros.managedbean.mapper;

import br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.presentation.mapper.ViewMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Classe que auxilia a criação do mapa de valores para os Combos aninhados referente a
 * {@link br.gov.to.sefaz.arr.parametros.managedbean.DareMB}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/08/2016 15:49:00
 */
@Component
public class DareComboMapper {

    private ViewMapper<TipoUnidadeEnum, TipoContribuinteEnum> tipoContribuinteMap;
    private ViewMapper<TipoContribuinteEnum, TipoPessoaEnum> tipoContribuinteIdentificacaoMap;
    private ViewMapper<TipoUnidadeEnum, TipoImpostoEnum> tipoImpostoMap;
    private ViewMapper<TipoImpostoEnum, OrigemDebitoEnum> origemDebitoMap;

    /**
     * Retorna um {@link br.gov.to.sefaz.presentation.mapper.ViewMapper} referente ao
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e seus possíveis valores
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum}.
     *
     * @return {@link  br.gov.to.sefaz.presentation.mapper.ViewMapper} entre
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e seu
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum}
     */
    public ViewMapper<TipoContribuinteEnum, TipoPessoaEnum> getTipoContribuinteIdentificacaoMap() {
        if (Objects.isNull(tipoContribuinteIdentificacaoMap)) {
            createTipoContribuinteIdentificacaoMap();
        }
        return tipoContribuinteIdentificacaoMap;
    }

    /**
     * Retorna um {@link br.gov.to.sefaz.presentation.mapper.ViewMapper} referente ao
     * {@link br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum} e seus possíveis valores
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum}.
     *
     * @return {@link  br.gov.to.sefaz.presentation.mapper.ViewMapper} entre
     * {@link br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum} e seu
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum}
     */
    public ViewMapper<TipoUnidadeEnum, TipoContribuinteEnum> getTipoContribuinteMap() {
        if (Objects.isNull(tipoContribuinteMap)) {
            createTipoContribuinteMap();
        }
        return tipoContribuinteMap;
    }

    /**
     * Retorna um {@link br.gov.to.sefaz.presentation.mapper.ViewMapper} referente ao
     * {@link br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum} e seus possíveis valores
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum}.
     *
     * @return {@link  br.gov.to.sefaz.presentation.mapper.ViewMapper} entre
     * {@link br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum} e seu
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum}
     */
    public ViewMapper<TipoUnidadeEnum, TipoImpostoEnum> getTipoImpostoMap() {
        if (Objects.isNull(tipoImpostoMap)) {
            createTipoImpostoMap();
        }
        return tipoImpostoMap;
    }

    /**
     * Retorna um {@link br.gov.to.sefaz.presentation.mapper.ViewMapper} referente ao
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e seus possíveis valores
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum}.
     *
     * @return {@link  br.gov.to.sefaz.presentation.mapper.ViewMapper} entre
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e seu
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum}
     */
    public ViewMapper<TipoImpostoEnum, OrigemDebitoEnum> getOrigemDebitoMap() {
        if (Objects.isNull(origemDebitoMap)) {
            createOrigemDebitoMap();
        }
        return origemDebitoMap;
    }

    private void createTipoContribuinteMap() {
        tipoContribuinteMap = new ViewMapper<>();
        tipoContribuinteMap.put(null, TipoContribuinteEnum.values());
        tipoContribuinteMap.put(TipoUnidadeEnum.SEFAZ, TipoContribuinteEnum.values());
        tipoContribuinteMap.put(TipoUnidadeEnum.ORGAOS_EXTERNOS, TipoContribuinteEnum.NAO_CONTRIBUINTE);
    }

    private void createTipoContribuinteIdentificacaoMap() {
        tipoContribuinteIdentificacaoMap = new ViewMapper<>();
        tipoContribuinteIdentificacaoMap.put(null, TipoPessoaEnum.values());
        tipoContribuinteIdentificacaoMap.put(TipoContribuinteEnum.ICMS,
                TipoPessoaEnum.INSCRICAO);
        tipoContribuinteIdentificacaoMap.put(TipoContribuinteEnum.IPVA, TipoPessoaEnum.RENAVAM);
        tipoContribuinteIdentificacaoMap.put(TipoContribuinteEnum.ITCD,
                TipoPessoaEnum.CNPJ, TipoPessoaEnum.CPF);
        tipoContribuinteIdentificacaoMap.put(TipoContribuinteEnum.NAO_CONTRIBUINTE,
                TipoPessoaEnum.CNPJ, TipoPessoaEnum.CPF);

    }

    private void createTipoImpostoMap() {
        tipoImpostoMap = new ViewMapper<>();
        tipoImpostoMap.put(null, TipoImpostoEnum.values());
        tipoImpostoMap.put(TipoUnidadeEnum.SEFAZ, TipoImpostoEnum.values());
        tipoImpostoMap.put(TipoUnidadeEnum.ORGAOS_EXTERNOS, TipoImpostoEnum.TAXAS_TRIBUTARIAS,
                TipoImpostoEnum.TAXAS_NAO_TRIBUTARIAS);
    }

    private void createOrigemDebitoMap() {
        origemDebitoMap = new ViewMapper<>();
        origemDebitoMap.put(null, OrigemDebitoEnum.values());
        origemDebitoMap.put(TipoImpostoEnum.ICMS, OrigemDebitoEnum.DECLARADO_ICMS,
                OrigemDebitoEnum.DECLARADO_ICMS_ST,
                OrigemDebitoEnum.DECLARADO_ICMS_COMPL,
                OrigemDebitoEnum.COBRANCA_TRANSITO,
                OrigemDebitoEnum.AUTO_INFRACAO_NLD,
                OrigemDebitoEnum.PARCELAMENTO,
                OrigemDebitoEnum.PARCELAMENTO_DIVIDA_ATIVA,
                OrigemDebitoEnum.DIVIDA_ATIVA,
                OrigemDebitoEnum.NOTA_AVULSA,
                OrigemDebitoEnum.ICMS_FRETE,
                OrigemDebitoEnum.NF_OPERACOES_EXPONTANEAS_ST,
                OrigemDebitoEnum.DPCA);
        origemDebitoMap.put(TipoImpostoEnum.IPVA, OrigemDebitoEnum.IPVA,
                OrigemDebitoEnum.PARCELAMENTO,
                OrigemDebitoEnum.PARCELAMENTO_DIVIDA_ATIVA,
                OrigemDebitoEnum.DIVIDA_ATIVA);
        origemDebitoMap.put(TipoImpostoEnum.ITCD,
                OrigemDebitoEnum.ITCD,
                OrigemDebitoEnum.PARCELAMENTO,
                OrigemDebitoEnum.PARCELAMENTO_DIVIDA_ATIVA,
                OrigemDebitoEnum.DIVIDA_ATIVA);
        origemDebitoMap.put(TipoImpostoEnum.TAXAS_NAO_TRIBUTARIAS,
                OrigemDebitoEnum.MULTAS,
                OrigemDebitoEnum.TAXAS,
                OrigemDebitoEnum.OUTRAS_RECEITAS);
        origemDebitoMap.put(TipoImpostoEnum.TAXAS_TRIBUTARIAS, OrigemDebitoEnum.MULTAS,
                OrigemDebitoEnum.TAXAS,
                OrigemDebitoEnum.OUTRAS_RECEITAS);
    }
}
