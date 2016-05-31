package br.gov.to.sefaz.cat.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_PAR.TA_ESTADO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 02/05/2016 14:45:32
 */
@Entity
@Table(name = "TA_ESTADO", schema = "SEFAZ_PAR")
public class Estado extends AbstractEntity<String> {

    private static final long serialVersionUID = 1865190227641759972L;

    @Id
    @NotNull(message = "#{par_msg['estados.unidadeFederacao.obrigatorio']}")
    @Size(min = 2, max = 2, message = "#{par_msg['estados.unidadeFederacao.tamanho']}")
    @Column(name = "UNIDADE_FEDERACAO", nullable = false, length = 2)
    private String unidadeFederacao;

    @Size(max = 40, message = "#{par_msg['estados.nomeEstado.tamanho']}")
    @Column(name = "NOME_ESTADO", length = 40)
    private String nomeEstado;

    @Max(value = 999, message = "#{par_msg['estados.aliquotaOrigemEntrada.maximo']}")
    @Min(value = 1, message = "#{par_msg['estados.aliquotaOrigemEntrada.minimo']}")
    @Column(name = "ALIQUOTA_ORIGEM_ENTRADA")
    private Integer aliquotaOrigemEntrada;

    @Digits(integer = 6, fraction = 2, message = "estados.valorAgregadoFarmaceutico.maximo")
    @Column(name = "VALOR_AGREGADO_FARMACEUTICO")
    private BigDecimal valorAgregadoFarmaceutico;

    @Size(max = 10, message = "#{par_msg['estados.cepGeralUf.tamanho']}")
    @Column(name = "CEP_GERAL_UF", length = 10)
    private String cepGeralUf;

    @Max(value = 9, message = "#{par_msg['estados.codigoRegiao.maximo']}")
    @Min(value = 1, message = "#{par_msg['estados.codigoRegiao.minimo']}")
    @Column(name = "CODIGO_REGIAO")
    private Integer codigoRegiao;

    @Max(value = 999, message = "#{par_msg['estados.cargaTributariaOrigem.maximo']}")
    @Min(value = 1, message = "#{par_msg['estados.cargaTributariaOrigem.minimo']}")
    @Column(name = "CARGA_TRIBUTARIA_ORIGEM")
    private Integer cargaTributariaOrigem;

    public Estado() {
        // Construtor para inicialização por reflexão.
    }

    public Estado(String unidadeFederacao, String nomeEstado, Integer aliquotaOrigemEntrada,
            BigDecimal valorAgregadoFarmaceutico, String cepGeralUf, Integer codigoRegiao,
            Integer cargaTributariaOrigem) {
        super();
        this.unidadeFederacao = unidadeFederacao;
        this.nomeEstado = nomeEstado;
        this.aliquotaOrigemEntrada = aliquotaOrigemEntrada;
        this.valorAgregadoFarmaceutico = valorAgregadoFarmaceutico;
        this.cepGeralUf = cepGeralUf;
        this.codigoRegiao = codigoRegiao;
        this.cargaTributariaOrigem = cargaTributariaOrigem;
    }

    @Override
    public String getId() {
        return unidadeFederacao;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(String unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Integer getAliquotaOrigemEntrada() {
        return aliquotaOrigemEntrada;
    }

    public void setAliquotaOrigemEntrada(Integer aliquotaOrigemEntrada) {
        this.aliquotaOrigemEntrada = aliquotaOrigemEntrada;
    }

    public BigDecimal getValorAgregadoFarmaceutico() {
        return valorAgregadoFarmaceutico;
    }

    public void setValorAgregadoFarmaceutico(BigDecimal valorAgregadoFarmaceutico) {
        this.valorAgregadoFarmaceutico = valorAgregadoFarmaceutico;
    }

    public String getCepGeralUf() {
        return cepGeralUf;
    }

    public void setCepGeralUf(String cepGeralUf) {
        this.cepGeralUf = cepGeralUf;
    }

    public Integer getCodigoRegiao() {
        return codigoRegiao;
    }

    public void setCodigoRegiao(Integer codigoRegiao) {
        this.codigoRegiao = codigoRegiao;
    }

    public Integer getCargaTributariaOrigem() {
        return cargaTributariaOrigem;
    }

    public void setCargaTributariaOrigem(Integer cargaTributariaOrigem) {
        this.cargaTributariaOrigem = cargaTributariaOrigem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Estado estado = (Estado) o;
        return Objects.equals(unidadeFederacao, estado.unidadeFederacao)
                && Objects.equals(nomeEstado, estado.nomeEstado)
                && Objects.equals(aliquotaOrigemEntrada, estado.aliquotaOrigemEntrada)
                && Objects.equals(valorAgregadoFarmaceutico, estado.valorAgregadoFarmaceutico)
                && Objects.equals(cepGeralUf, estado.cepGeralUf) && Objects.equals(codigoRegiao, estado.codigoRegiao)
                && Objects.equals(cargaTributariaOrigem, estado.cargaTributariaOrigem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unidadeFederacao, nomeEstado, aliquotaOrigemEntrada, valorAgregadoFarmaceutico, cepGeralUf,
                codigoRegiao, cargaTributariaOrigem);
    }

    @Override
    public String toString() {
        return "Estado{" + "unidadeFederacao='" + unidadeFederacao + '\'' + ", nomeEstado='" + nomeEstado + '\''
                + ", aliquotaOrigemEntrada=" + aliquotaOrigemEntrada + ", valorAgregadoFarmaceutico="
                + valorAgregadoFarmaceutico + ", cepGeralUf='" + cepGeralUf + '\'' + ", codigoRegiao=" + codigoRegiao
                + ", cargaTributariaOrigem=" + cargaTributariaOrigem + '}';
    }
}
