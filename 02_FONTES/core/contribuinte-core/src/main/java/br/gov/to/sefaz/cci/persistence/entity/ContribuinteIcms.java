package br.gov.to.sefaz.cci.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

/**
 * Entidade referente a tabela SEFAZ_CCI.TA_CONTRIBUINTE_ICMS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 17/08/2016 15:19:30
 */
@SuppressWarnings("PMD.TooManyFields")
@Entity
@Table(name = "TA_CONTRIBUINTE_ICMS", schema = "SEFAZ_CCI")
public class ContribuinteIcms extends AbstractEntity<String> {

    private static final long serialVersionUID = -49675899460523198L;

    @Id
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "NUM_INSCRICAO_ESTADUAL")
    private String numInscricaoEstadual;

    @Size(max = 11)
    @Column(name = "NUM_CPF")
    private String numCpf;

    @Size(max = 14)
    @Column(name = "NUM_CNPJ")
    private String numCnpj;

    @Size(max = 13)
    @Column(name = "NUM_INSCRICAO_ESTADUAL_EXT")
    private String numInscricaoEstadualExt;

    @NotNull
    @Column(name = "IND_CATEGORIA_CONTRIBUINTE")
    private Integer indCategoriaContribuinte;

    @Column(name = "COD_REGIME_RECOLHIMENTO")
    private Integer codRegimeRecolhimento;

    @Column(name = "CO_UNIDADE")
    private Integer coUnidade;

    @Column(name = "IDEN_CLASSE_OBRIGADOS")
    private Integer idenClasseObrigados;

    @NotNull
    @Column(name = "SIGLA_SITUACAO_CONTRIBUINTE")
    private Integer siglaSituacaoContribuinte;

    @NotNull
    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_INICIO_ATIVIDADE")
    private LocalDate dataInicioAtividade;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_FINAL_ATIVIDADE")
    private LocalDate dataFinalAtividade;

    @NotNull
    @Column(name = "TIPO_SITUACAO_NFE")
    private Character tipoSituacaoNfe;

    @NotNull
    @Column(name = "TIPO_DIVIDA_ATIVA")
    private Character tipoDividaAtiva;

    @Column(name = "NUM_PROCESSO_SEFAZ")
    private Long numProcessoSefaz;

    @Column(name = "IND_USUARIO_PED")
    private Character indUsuarioPed;

    @Column(name = "DATA_INICIO_USO_PED")
    private Long dataInicioUsoPed;

    @NotNull
    @Column(name = "IND_ORIGEM_INSERCAO")
    private Character indOrigemInsercao;

    @NotNull
    @Column(name = "IND_ORIGEM_INFORMACAO")
    private Character indOrigemInformacao;

    @Override
    public String getId() {
        return getNumInscricaoEstadual();
    }

    public String getNumInscricaoEstadual() {
        return numInscricaoEstadual;
    }

    public void setNumInscricaoEstadual(String numInscricaoEstadual) {
        this.numInscricaoEstadual = numInscricaoEstadual;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public String getNumCnpj() {
        return numCnpj;
    }

    public void setNumCnpj(String numCnpj) {
        this.numCnpj = numCnpj;
    }

    public String getNumInscricaoEstadualExt() {
        return numInscricaoEstadualExt;
    }

    public void setNumInscricaoEstadualExt(String numInscricaoEstadualExt) {
        this.numInscricaoEstadualExt = numInscricaoEstadualExt;
    }

    public Integer getIndCategoriaContribuinte() {
        return indCategoriaContribuinte;
    }

    public void setIndCategoriaContribuinte(Integer indCategoriaContribuinte) {
        this.indCategoriaContribuinte = indCategoriaContribuinte;
    }

    public Integer getCodRegimeRecolhimento() {
        return codRegimeRecolhimento;
    }

    public void setCodRegimeRecolhimento(Integer codRegimeRecolhimento) {
        this.codRegimeRecolhimento = codRegimeRecolhimento;
    }

    public Integer getCoUnidade() {
        return coUnidade;
    }

    public void setCoUnidade(Integer coUnidade) {
        this.coUnidade = coUnidade;
    }

    public Integer getIdenClasseObrigados() {
        return idenClasseObrigados;
    }

    public void setIdenClasseObrigados(Integer idenClasseObrigados) {
        this.idenClasseObrigados = idenClasseObrigados;
    }

    public Integer getSiglaSituacaoContribuinte() {
        return siglaSituacaoContribuinte;
    }

    public void setSiglaSituacaoContribuinte(Integer siglaSituacaoContribuinte) {
        this.siglaSituacaoContribuinte = siglaSituacaoContribuinte;
    }

    public LocalDate getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(LocalDate dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public LocalDate getDataFinalAtividade() {
        return dataFinalAtividade;
    }

    public void setDataFinalAtividade(LocalDate dataFinalAtividade) {
        this.dataFinalAtividade = dataFinalAtividade;
    }

    public Character getTipoSituacaoNfe() {
        return tipoSituacaoNfe;
    }

    public void setTipoSituacaoNfe(Character tipoSituacaoNfe) {
        this.tipoSituacaoNfe = tipoSituacaoNfe;
    }

    public Character getTipoDividaAtiva() {
        return tipoDividaAtiva;
    }

    public void setTipoDividaAtiva(Character tipoDividaAtiva) {
        this.tipoDividaAtiva = tipoDividaAtiva;
    }

    public Long getNumProcessoSefaz() {
        return numProcessoSefaz;
    }

    public void setNumProcessoSefaz(Long numProcessoSefaz) {
        this.numProcessoSefaz = numProcessoSefaz;
    }

    public Character getIndUsuarioPed() {
        return indUsuarioPed;
    }

    public void setIndUsuarioPed(Character indUsuarioPed) {
        this.indUsuarioPed = indUsuarioPed;
    }

    public Long getDataInicioUsoPed() {
        return dataInicioUsoPed;
    }

    public void setDataInicioUsoPed(Long dataInicioUsoPed) {
        this.dataInicioUsoPed = dataInicioUsoPed;
    }

    public Character getIndOrigemInsercao() {
        return indOrigemInsercao;
    }

    public void setIndOrigemInsercao(Character indOrigemInsercao) {
        this.indOrigemInsercao = indOrigemInsercao;
    }

    public Character getIndOrigemInformacao() {
        return indOrigemInformacao;
    }

    public void setIndOrigemInformacao(Character indOrigemInformacao) {
        this.indOrigemInformacao = indOrigemInformacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContribuinteIcms that = (ContribuinteIcms) o;
        return Objects.equals(numInscricaoEstadual, that.numInscricaoEstadual)
                && Objects.equals(numCpf, that.numCpf)
                && Objects.equals(numCnpj, that.numCnpj)
                && Objects.equals(numInscricaoEstadualExt, that.numInscricaoEstadualExt)
                && Objects.equals(indCategoriaContribuinte, that.indCategoriaContribuinte)
                && Objects.equals(codRegimeRecolhimento, that.codRegimeRecolhimento)
                && Objects.equals(coUnidade, that.coUnidade)
                && Objects.equals(idenClasseObrigados, that.idenClasseObrigados)
                && Objects.equals(siglaSituacaoContribuinte, that.siglaSituacaoContribuinte)
                && Objects.equals(dataInicioAtividade, that.dataInicioAtividade)
                && Objects.equals(dataFinalAtividade, that.dataFinalAtividade)
                && Objects.equals(tipoSituacaoNfe, that.tipoSituacaoNfe)
                && Objects.equals(tipoDividaAtiva, that.tipoDividaAtiva)
                && Objects.equals(numProcessoSefaz, that.numProcessoSefaz)
                && Objects.equals(indUsuarioPed, that.indUsuarioPed)
                && Objects.equals(dataInicioUsoPed, that.dataInicioUsoPed)
                && Objects.equals(indOrigemInsercao, that.indOrigemInsercao)
                && Objects.equals(indOrigemInformacao, that.indOrigemInformacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numInscricaoEstadual, numCpf, numCnpj, numInscricaoEstadualExt, indCategoriaContribuinte,
                codRegimeRecolhimento, coUnidade, idenClasseObrigados, siglaSituacaoContribuinte, dataInicioAtividade,
                dataFinalAtividade, tipoSituacaoNfe, tipoDividaAtiva, numProcessoSefaz, indUsuarioPed, dataInicioUsoPed,
                indOrigemInsercao, indOrigemInformacao);
    }

    @Override
    public String toString() {
        return "ContribuinteIcms{"
                + "numInscricaoEstadual='" + numInscricaoEstadual + '\''
                + ", numCpf='" + numCpf + '\''
                + ", numCnpj='" + numCnpj + '\''
                + ", numInscricaoEstadualExt='" + numInscricaoEstadualExt + '\''
                + ", indCategoriaContribuinte=" + indCategoriaContribuinte
                + ", codRegimeRecolhimento=" + codRegimeRecolhimento
                + ", coUnidade=" + coUnidade
                + ", idenClasseObrigados=" + idenClasseObrigados
                + ", siglaSituacaoContribuinte=" + siglaSituacaoContribuinte
                + ", dataInicioAtividade=" + dataInicioAtividade
                + ", dataFinalAtividade=" + dataFinalAtividade
                + ", tipoSituacaoNfe=" + tipoSituacaoNfe
                + ", tipoDividaAtiva=" + tipoDividaAtiva
                + ", numProcessoSefaz=" + numProcessoSefaz
                + ", indUsuarioPed=" + indUsuarioPed
                + ", dataInicioUsoPed=" + dataInicioUsoPed
                + ", indOrigemInsercao=" + indOrigemInsercao
                + ", indOrigemInformacao=" + indOrigemInformacao
                + '}';
    }
}
