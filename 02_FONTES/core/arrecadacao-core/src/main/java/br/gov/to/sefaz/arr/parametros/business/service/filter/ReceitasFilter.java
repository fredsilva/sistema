package br.gov.to.sefaz.arr.parametros.business.service.filter;

import br.gov.to.sefaz.arr.persistence.enums.ClassificacaoReceitaEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaEnum;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Objects;

/**
 * POJO para representar os campos de filtro para pesquisa de
 * {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} na base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/05/2016 12:00:00
 */
public class ReceitasFilter {

    private Integer idReceita;
    private String descricaoReceita;
    private TipoReceitaEnum tipoReceita;
    private ClassificacaoReceitaEnum classificacaoReceita;
    private SituacaoEnum situacao;

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public TipoReceitaEnum getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceitaEnum tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public ClassificacaoReceitaEnum getClassificacaoReceita() {
        return classificacaoReceita;
    }

    public void setClassificacaoReceita(ClassificacaoReceitaEnum classificacaoReceita) {
        this.classificacaoReceita = classificacaoReceita;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceitasFilter that = (ReceitasFilter) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(descricaoReceita, that.descricaoReceita)
                && tipoReceita == that.tipoReceita
                && classificacaoReceita == that.classificacaoReceita
                && situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, descricaoReceita, tipoReceita, classificacaoReceita, situacao);
    }

    @Override
    public String toString() {
        return "ReceitasFilter{"
                + "idReceita=" + idReceita
                + ", descricaoReceita='" + descricaoReceita + '\''
                + ", tipoReceita=" + tipoReceita
                + ", classificacaoReceita=" + classificacaoReceita
                + ", situacao=" + situacao
                + '}';
    }
}
