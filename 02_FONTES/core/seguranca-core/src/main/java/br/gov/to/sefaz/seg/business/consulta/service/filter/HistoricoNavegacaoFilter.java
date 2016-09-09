package br.gov.to.sefaz.seg.business.consulta.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe de filtro para a tela de consulta de histórico de acesso ao sistema.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 25/08/2016 10:30:07
 */
public class HistoricoNavegacaoFilter {
    private String cpf;
    private String cpfUsuario;
    private String nomeUsuario;
    private String cpfCnpjProcurado;
    private String nomeProcurado;
    private TipoOperacaoEnum tipoOperacao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String url;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpfCnpjProcurado() {
        return cpfCnpjProcurado;
    }

    public void setCpfCnpjProcurado(String cpfCnpjProcurado) {
        this.cpfCnpjProcurado = cpfCnpjProcurado;
    }

    public String getNomeProcurado() {
        return nomeProcurado;
    }

    public void setNomeProcurado(String nomeProcurado) {
        this.nomeProcurado = nomeProcurado;
    }

    public TipoOperacaoEnum getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtém a data inicial do período de pesquisa com os horários ajustados para a primeira hora do dia. Exemplo:
     * <p>
     * Data: 26/08/2016 retorna 26/08/2016 00:00:00
     * </p>
     *
     * @return retorna data com a parte de hora com valor "00:00:00" ou <code>null</code> caso a data inicial seja nula.
     * @see HistoricoNavegacaoFilter#getDataInicio()
     */
    public LocalDateTime getDataInicialAjustada() {
        if (getDataInicio() == null) {
            return null;
        }

        return getDataInicio().atStartOfDay();
    }


    /**
     * Obtém a data final do período de pesquisa com os horários ajustados para a última hora do dia. Exemplo:
     * <p>
     * Data: 26/08/2016 retorna 26/08/2016 23:59:59.999999999
     * </p>
     *
     * @return retorna data com a parte de hora com valor "23:59:59.999999999" ou <code>null</code> caso a data final
     *         seja nula.
     * @see HistoricoNavegacaoFilter#getDataFim()
     */
    public LocalDateTime getDataFinalAjustada() {
        if (getDataFim() == null) {
            return null;
        }

        return getDataFim().atTime(23, 59, 59, 999999999);
    }
}
