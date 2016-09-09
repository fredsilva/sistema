package br.gov.to.sefaz.seg.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade referente a tabela SEFAZ_SEG.VW_LISTAGEM_CPF_PROCURACAO do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/09/2016 14:44:00
 */
@Entity
@Table(name = "VW_LISTAGEM_CPF_PROCURACAO", schema = "SEFAZ_SEG")
public class ListagemCpfProcuracao implements Serializable {

    private static final long serialVersionUID = 5199782231950473498L;

    @Id
    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "CPF_FILTRO")
    private String cpfFiltro;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOME_CONTRIBUINTE")
    private String nomeContribuinte;

    public String getCpfFiltro() {
        return cpfFiltro;
    }

    public void setCpfFiltro(String cpfFiltro) {
        this.cpfFiltro = cpfFiltro;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeContribuinte() {
        return nomeContribuinte;
    }

    public void setNomeContribuinte(String nomeContribuinte) {
        this.nomeContribuinte = nomeContribuinte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListagemCpfProcuracao that = (ListagemCpfProcuracao) o;
        return Objects.equals(cpfFiltro, that.cpfFiltro)
                && Objects.equals(cpfCnpj, that.cpfCnpj)
                && Objects.equals(nome, that.nome)
                && Objects.equals(nomeContribuinte, that.nomeContribuinte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfFiltro, cpfCnpj, nome, nomeContribuinte);
    }

    @Override
    public String toString() {
        return "ListagemCpfProcuracao{"
                + "cpfFiltro='" + cpfFiltro + '\''
                + ", cpfCnpj='" + cpfCnpj + '\''
                + ", nome='" + nome + '\''
                + ", nomeContribuinte='" + nomeContribuinte + '\''
                + '}';
    }
}
