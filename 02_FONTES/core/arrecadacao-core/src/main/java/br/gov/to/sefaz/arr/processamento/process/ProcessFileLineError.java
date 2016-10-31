package br.gov.to.sefaz.arr.processamento.process;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoErro;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoDetalhePagosService;
import br.gov.to.sefaz.arr.processamento.service.ArquivoErroService;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processa um erro referente a linha de detalhe do arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 14:45:00
 */
@Component
public class ProcessFileLineError {

    private static final int CONTEM_ERRO = 1;

    private final ArquivoErroService arquivoErroService;
    private final ArquivoDetalhePagosService arquivoDetalhePagosService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public ProcessFileLineError(ArquivoErroService arquivoErroService,
            ArquivoDetalhePagosService arquivoDetalhePagosService, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.arquivoErroService = arquivoErroService;
        this.arquivoDetalhePagosService = arquivoDetalhePagosService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Verifica se já existe um erro com o codigo de rejeição para o mesmo detalhe de arquivo e caso não exista, cria
     * um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoErro} com o codigo de rejeição e
     * {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} fornecidos.
     *
     * @param valorLinha          conteúdo da linha do detalhe
     * @param codigoRejeicao      código de rejeição para o erro informado
     * @param arquivoDetalhePagos entidade que contém as informações para o detalhe da linha do arquivo
     */
    public void processErroArquivo(String valorLinha, Integer codigoRejeicao,
            ArquivoDetalhePagos arquivoDetalhePagos) {
        boolean existsError = arquivoErroService.existsWith(codigoRejeicao, arquivoDetalhePagos.getIdDetalheArquivo());
        if (!existsError) {
            ArquivoErro arquivoErro = new ArquivoErro(valorLinha, codigoRejeicao,
                    arquivoDetalhePagos.getIdDetalheArquivo());
            arquivoErroService.save(arquivoErro);
            arquivoDetalhePagos.setErroLinha(CONTEM_ERRO);
            arquivoDetalhePagosService.update(arquivoDetalhePagos);

            ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
            arquivoRecepcao.setSituacao(SituacaoArquivoEnum.PROCESSADO_COM_ERROS);
            arquivoRecepcaoService.update(arquivoRecepcao);
        }
    }
}
