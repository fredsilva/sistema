package br.gov.to.sefaz.par.gestao.persistence.entity;

import br.gov.to.sefaz.par.gestao.persistence.converter.TipoParametroGeralEnumConverter;
import br.gov.to.sefaz.par.gestao.persistence.enums.TipoParametroGeralEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que refencia a tabela SEFAZ_PAR.TA_PARAMETRO_GERAL.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/07/2016 14:23:00
 */
@Entity
@Table(name = "TA_PARAMETRO_GERAL", schema = "SEFAZ_PAR")
public class ParametroGeral extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -280178682163903873L;

    public static final String LISTAGEM_TIPO_USUARIO = "LISTAGEM_TIPO_USUARIO";
    public static final String LISTAGEM_UF = "LISTAGEM_UF";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PARAMETRO_GERAL")
    @SequenceGenerator(name = "SQ_PARAMETRO_GERAL", schema = "SEFAZ_PAR",
            sequenceName = "SQ_PARAMETRO_GERAL", allocationSize = 1)
    @Column(name = "ID_PARAMETRO_GERAL")
    private Integer idParametroGeral;

    @NotEmpty(message = "#{par_msg['gestao.manutencaoParametros.nomeParametroGeral.obrigatorio']}")
    @Size(max = 50, message = "#{par_msg['par.nomeParametroGeral.tamanho']}")
    @Column(name = "NOME_PARAMETRO_GERAL")
    private String nomeParametroGeral;

    @NotEmpty(message = "#{par_msg['gestao.manutencaoParametros.objetivoParametro.obrigatorio']}")
    @Size(max = 200, message = "#{par_msg['par.objetivoParametro.tamanho']}")
    @Column(name = "OBJETIVO_PARAMETRO")
    private String objetivoParametro;

    @Column(name = "TIPO_PARAMETRO_GERAL")
    @NotNull(message = "#{par_msg['gestao.manutencaoParametros.tipoParametroGeral.obrigatorio']}")
    @Convert(converter = TipoParametroGeralEnumConverter.class)
    private TipoParametroGeralEnum tipoParametroGeral;

    @NotEmpty(message = "#{par_msg['gestao.manutencaoParametros.conteudoValores.obrigatorio']}")
    @Size(max = 4000, message = "#{par_msg['gestao.manutencaoParametros.conteudoValores.tamanho']}")
    @Column(name = "CONTEUDO_VALORES")
    private String conteudoValores;


    public ParametroGeral() {
        // Construtor vazio para instanciação via reflection
    }

    public ParametroGeral(String nomeParametroGeral, String objetivoParametro,
            TipoParametroGeralEnum tipoParametroGeral, String conteudoValores) {
        this.nomeParametroGeral = nomeParametroGeral;
        this.objetivoParametro = objetivoParametro;
        this.tipoParametroGeral = tipoParametroGeral;
        this.conteudoValores = conteudoValores;
    }

    @Override
    public Integer getId() {
        return idParametroGeral;
    }

    public String getNomeParametroGeral() {
        return nomeParametroGeral;
    }

    public void setNomeParametroGeral(String nomeParametroGeral) {
        this.nomeParametroGeral = nomeParametroGeral;
    }

    public String getObjetivoParametro() {
        return objetivoParametro;
    }

    public void setObjetivoParametro(String objetivoParametro) {
        this.objetivoParametro = objetivoParametro;
    }

    public TipoParametroGeralEnum getTipoParametroGeral() {
        return tipoParametroGeral;
    }

    public void setTipoParametroGeral(TipoParametroGeralEnum tipoParametroGeral) {
        this.tipoParametroGeral = tipoParametroGeral;
    }

    public String getConteudoValores() {
        return conteudoValores;
    }

    public void setConteudoValores(String conteudoValores) {
        this.conteudoValores = conteudoValores;
    }

    public Integer getIdParametroGeral() {
        return idParametroGeral;
    }

    public void setIdParametroGeral(Integer idParametroGeral) {
        this.idParametroGeral = idParametroGeral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParametroGeral)) {
            return false;
        }
        ParametroGeral that = (ParametroGeral) o;
        return Objects.equals(getIdParametroGeral(), that.getIdParametroGeral())
                && Objects.equals(getNomeParametroGeral(), that.getNomeParametroGeral())
                && Objects.equals(getObjetivoParametro(), that.getObjetivoParametro())
                && getTipoParametroGeral() == that.getTipoParametroGeral()
                && Objects.equals(getConteudoValores(), that.getConteudoValores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdParametroGeral(), getNomeParametroGeral(),
                getObjetivoParametro(), getTipoParametroGeral(), getConteudoValores());
    }
}
