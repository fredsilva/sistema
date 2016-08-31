package br.gov.to.sefaz.cci.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade de Pessoa Jurídica referente à Tabela SEFAZ_CCI.TA_PESSOA_JURIDICA da base de dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 11:11:00
 */
@Entity
@Table(name = "TA_PESSOA_JURIDICA", schema = "SEFAZ_CCI")
public class PessoaJuridica extends AbstractEntity<String> {

    private static final long serialVersionUID = 8090186595820094945L;

    @Id
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NUM_BASE_CNPJ")
    private String numBaseCnpj;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOME_RAZAO_SOCIAL")
    private String nomeRazaoSocial;

    @Size(max = 150)
    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Min(value = 9)
    @Max(value = 9999)
    @Column(name = "VALOR_CAPITAL_INTEGRALIZADO")
    private BigDecimal valorCapitalIntegralizado;

    @NotNull
    @Column(name = "IDEN_NATUREZA_JURIDICA")
    private Integer idenNaturezaJuridica;

    @NotNull
    @Column(name = "IND_ORIGEM_INFORMACAO")
    private Character indOrigemInformacao;

    @Column(name = "TIPO_OPCAO_SIMPLES")
    private Character tipoOpcaoSimples;

    @Column(name = "DATA_OPCAO_SIMPLES")
    private LocalDate dataOpcaoSimples;

    @Column(name = "DATA_EXCLUSAO_SIMPLES")
    private LocalDate dataExclusaoSimples;

    @Size(max = 14)
    @Column(name = "NUM_BASE_CNPJ_SUCEDIDA")
    private String numBaseCnpjSucedida;

    @Size(max = 14)
    @Column(name = "NUM_BASE_CNPJ_SUCESSORA")
    private String numBaseCnpjSucessora;

    @JoinColumn(name = "COD_PORTE", referencedColumnName = "COD_PORTE_EMPRESA")
    @ManyToOne
    private PorteEmpresa codPorte;

    @Override
    public String getId() {
        return numBaseCnpj;
    }

    public String getNumBaseCnpj() {
        return numBaseCnpj;
    }

    public void setNumBaseCnpj(String numBaseCnpj) {
        this.numBaseCnpj = numBaseCnpj;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public BigDecimal getValorCapitalIntegralizado() {
        return valorCapitalIntegralizado;
    }

    public void setValorCapitalIntegralizado(BigDecimal valorCapitalIntegralizado) {
        this.valorCapitalIntegralizado = valorCapitalIntegralizado;
    }

    public int getIdenNaturezaJuridica() {
        return idenNaturezaJuridica;
    }

    public void setIdenNaturezaJuridica(int idenNaturezaJuridica) {
        this.idenNaturezaJuridica = idenNaturezaJuridica;
    }

    public Character getIndOrigemInformacao() {
        return indOrigemInformacao;
    }

    public void setIndOrigemInformacao(Character indOrigemInformacao) {
        this.indOrigemInformacao = indOrigemInformacao;
    }

    public Character getTipoOpcaoSimples() {
        return tipoOpcaoSimples;
    }

    public void setTipoOpcaoSimples(Character tipoOpcaoSimples) {
        this.tipoOpcaoSimples = tipoOpcaoSimples;
    }

    public LocalDate getDataOpcaoSimples() {
        return dataOpcaoSimples;
    }

    public void setDataOpcaoSimples(LocalDate dataOpcaoSimples) {
        this.dataOpcaoSimples = dataOpcaoSimples;
    }

    public LocalDate getDataExclusaoSimples() {
        return dataExclusaoSimples;
    }

    public void setDataExclusaoSimples(LocalDate dataExclusaoSimples) {
        this.dataExclusaoSimples = dataExclusaoSimples;
    }

    public String getNumBaseCnpjSucedida() {
        return numBaseCnpjSucedida;
    }

    public void setNumBaseCnpjSucedida(String numBaseCnpjSucedida) {
        this.numBaseCnpjSucedida = numBaseCnpjSucedida;
    }

    public String getNumBaseCnpjSucessora() {
        return numBaseCnpjSucessora;
    }

    public void setNumBaseCnpjSucessora(String numBaseCnpjSucessora) {
        this.numBaseCnpjSucessora = numBaseCnpjSucessora;
    }

    public PorteEmpresa getCodPorte() {
        return codPorte;
    }

    public void setCodPorte(PorteEmpresa codPorte) {
        this.codPorte = codPorte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PessoaJuridica that = (PessoaJuridica) o;
        return idenNaturezaJuridica == that.idenNaturezaJuridica
                && Objects.equals(numBaseCnpj, that.numBaseCnpj)
                && Objects.equals(nomeRazaoSocial, that.nomeRazaoSocial)
                && Objects.equals(nomeFantasia, that.nomeFantasia)
                && Objects.equals(valorCapitalIntegralizado, that.valorCapitalIntegralizado)
                && Objects.equals(indOrigemInformacao, that.indOrigemInformacao)
                && Objects.equals(tipoOpcaoSimples, that.tipoOpcaoSimples)
                && Objects.equals(dataOpcaoSimples, that.dataOpcaoSimples)
                && Objects.equals(dataExclusaoSimples, that.dataExclusaoSimples)
                && Objects.equals(numBaseCnpjSucedida, that.numBaseCnpjSucedida)
                && Objects.equals(numBaseCnpjSucessora, that.numBaseCnpjSucessora)
                && Objects.equals(usuarioInsercao, that.usuarioInsercao)
                && Objects.equals(dataInsercao, that.dataInsercao)
                && Objects.equals(usuarioAlteracao, that.usuarioAlteracao)
                && Objects.equals(dataAlteracao, that.dataAlteracao)
                && Objects.equals(registroExcluido, that.registroExcluido)
                && Objects.equals(usuarioExclusao, that.usuarioExclusao)
                && Objects.equals(dataExclusao, that.dataExclusao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBaseCnpj,
                nomeRazaoSocial, nomeFantasia, valorCapitalIntegralizado, idenNaturezaJuridica, indOrigemInformacao,
                tipoOpcaoSimples, dataOpcaoSimples, dataExclusaoSimples, numBaseCnpjSucedida, numBaseCnpjSucessora,
                usuarioInsercao, dataInsercao, usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao,
                dataExclusao);
    }

    @Override
    public String toString() {
        return "PessoaJuridica{"
                + "numBaseCnpj='" + numBaseCnpj + '\''
                + ", nomeRazaoSocial='" + nomeRazaoSocial + '\''
                + ", nomeFantasia='" + nomeFantasia + '\''
                + ", valorCapitalIntegralizado=" + valorCapitalIntegralizado
                + ", idenNaturezaJuridica=" + idenNaturezaJuridica
                + ", indOrigemInformacao=" + indOrigemInformacao
                + ", tipoOpcaoSimples=" + tipoOpcaoSimples
                + ", dataOpcaoSimples=" + dataOpcaoSimples
                + ", dataExclusaoSimples=" + dataExclusaoSimples
                + ", numBaseCnpjSucedida='" + numBaseCnpjSucedida + '\''
                + ", numBaseCnpjSucessora='" + numBaseCnpjSucessora + '\''
                + ", codPorte=" + codPorte
                + '}';
    }
}
