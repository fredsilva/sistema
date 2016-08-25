package br.gov.to.sefaz.arr.processamento.process.content.util;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagos;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.processamento.creator.LotesPagosArrecBdarCreator;
import br.gov.to.sefaz.arr.processamento.creator.LotesPagosArrecTparCreator;
import br.gov.to.sefaz.arr.processamento.service.LotesPagosArrecService;
import br.gov.to.sefaz.arr.processamento.service.LotesPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Auiliador de operações referentes a {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} e
 * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 10:17:00
 */
@Component
public class LotesPagosManager {

    private final LotesPagosArrecService lotesPagosArrecService;
    private final LotesPagosService lotesPagosService;
    private final LotesPagosArrecTparCreator tparCreator;
    private final LotesPagosArrecBdarCreator bdarCreator;

    @Autowired
    public LotesPagosManager(LotesPagosArrecService lotesPagosArrecService, LotesPagosService lotesPagosService,
            LotesPagosArrecTparCreator tparCreator, LotesPagosArrecBdarCreator bdarCreator) {
        this.lotesPagosArrecService = lotesPagosArrecService;
        this.lotesPagosService = lotesPagosService;
        this.tparCreator = tparCreator;
        this.bdarCreator = bdarCreator;
    }

    /**
     * Incrementa a quantidade de docs, atualiza o valor do lote, atualiza o número da quantidade recepcionado, que
     * contém a quantidade de docs e quantidade de erros, atualiza o valor recepcionado, que contém o valor do lote
     * somando ao valor dos erros encontrados.
     *
     * @param valorLote       valor do lote a ser adicionado ao TPAR ou BDAR.
     * @param lotesPagosArrec TPAR ou BDAR que será atualizado.
     */
    public void updateQuantidadeAndValor(BigDecimal valorLote, LotesPagosArrec lotesPagosArrec) {
        lotesPagosArrec.setQuantidadeDocs(lotesPagosArrec.getQuantidadeDocs() + 1);
        lotesPagosArrec.setValorLote(lotesPagosArrec.getValorLote().add(valorLote));

        lotesPagosArrec.setQuantidadeRecepcionado(lotesPagosArrec.getQuantidadeDocs()
                + lotesPagosArrec.getQuantidadeErros());
        lotesPagosArrec.setValorRecepcionado(lotesPagosArrec.getValorErros().add(lotesPagosArrec.getValorLote()));

        lotesPagosArrecService.update(lotesPagosArrec);
    }

    /**
     * Incrementa a quantidade de docs com erro, atualiza o valor de erros, atualiza o número da quantidade
     * recepcionado, que contém a quantidade de docs e quantidade de erros, atualiza o valor recepcionado, que contém
     * o valor do lote somando ao valor dos erros encontrados.
     *
     * @param valorLote       valor do lote a ser adicionado ao TPAR ou BDAR.
     * @param lotesPagosArrec TPAR ou BDAR que será atualizado.
     */
    public void updateQuantidadeAndValorError(BigDecimal valorLote, LotesPagosArrec lotesPagosArrec) {
        lotesPagosArrec.setQuantidadeErros(lotesPagosArrec.getQuantidadeErros() + 1);
        lotesPagosArrec.setValorErros(lotesPagosArrec.getValorErros().add(valorLote));

        lotesPagosArrec.setQuantidadeRecepcionado(lotesPagosArrec.getQuantidadeDocs()
                + lotesPagosArrec.getQuantidadeErros());
        lotesPagosArrec.setValorRecepcionado(lotesPagosArrec.getValorErros().add(lotesPagosArrec.getValorLote()));

        lotesPagosArrecService.update(lotesPagosArrec);
    }


    /**
     * Verifica através dos parâmetros fornecidos, se um TPAR já existe na Base de Dados. Se existir retorna o TPAR
     * encotrado, se não, cria um novo TPAR.
     *
     * @param codigoConvenio  código do Convênio de Arrecadação com a agência e banco
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param dataArrecadacao Data da arrecadação.
     * @param bdar            contém a identificação do BDAR utilizado para referenciar o TPAR criado.
     * @return {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} referente ao TPAR conforme
     *      os parâmetros fornecidos
     */
    public LotesPagosArrec getTpar(Long codigoConvenio, Integer codigoBanco, Integer codigoAgencia, LocalDateTime
            dataArrecadacao, LotesPagosArrec bdar) {
        LotesPagosArrec tpar = lotesPagosArrecService.findTpar(codigoBanco, codigoAgencia, codigoConvenio,
                dataArrecadacao);

        if (Objects.isNull(tpar)) {
            tpar = createAndSaveTpar(codigoConvenio, codigoBanco, codigoAgencia, dataArrecadacao, bdar);

            createAndSaveLotesPagos(bdar, tpar);
        }

        return tpar;
    }

    /**
     * Cria e salva um TPAR conforme os parâmetros fornecidos.
     * Cria o relacionamento entre TPAR e BDAR através da entidade
     * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}.
     *
     * @param codigoConvenio  código do Convênio de Arrecadação com a agência e banco
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param dataArrecadacao Data da arrecadação.
     * @param bdar            contém a identificação do BDAR utilizado para referenciar o TPAR criado.
     * @return TPAR criado na base de dados
     */
    public LotesPagosArrec createAndSaveTpar(Long codigoConvenio, Integer codigoBanco, Integer codigoAgencia,
            LocalDateTime dataArrecadacao, LotesPagosArrec bdar) {
        LotesPagosArrec tpar;
        LotesPagosArrec lotesPagosArrec = tparCreator.createTpar(codigoConvenio, codigoBanco, codigoAgencia,
                dataArrecadacao);

        tpar = lotesPagosArrecService.save(lotesPagosArrec);
        createAndSaveLotesPagos(bdar, tpar);

        return tpar;
    }

    /**
     * Atualiza o estado de um TPAR ou BDAR conforme as opções disponíveis no
     * {@link br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum}.
     *
     * @param lotesPagosArrec TPAR ou BDAR que será atualizado
     * @param estado          novo estado do TPAR ou BDAR
     */
    public void updateEstado(LotesPagosArrec lotesPagosArrec, EstadoLoteEnum estado) {
        lotesPagosArrec.setEstadoLote(estado);

        lotesPagosArrecService.update(lotesPagosArrec);
    }

    /**
     * Verifica através dos parâmetros fornecidos, se um BDAR já existe na Base de Dados. Se existir retorna o BDAR
     * encotrado, se não, cria um novo BDAR.
     *
     * @param codigoConvenio  código do Convênio de Arrecadação com a agência e banco
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param dataArrecadacao Data da arrecadação.
     * @return {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} referente ao BDAR conforme
     *      os parâmetros fornecidos
     */
    public LotesPagosArrec getBdar(Long codigoConvenio, Integer codigoBanco, Integer codigoAgencia, LocalDateTime
            dataArrecadacao) {
        LotesPagosArrec bdar = lotesPagosArrecService.findBdar(codigoBanco, codigoAgencia, codigoConvenio,
                dataArrecadacao);

        if (Objects.isNull(bdar)) {
            LotesPagosArrec lotesPagosArrec = bdarCreator.createBdar(codigoConvenio, codigoBanco, codigoAgencia,
                    dataArrecadacao);

            bdar = lotesPagosArrecService.save(lotesPagosArrec);
        }

        return bdar;
    }

    private void createAndSaveLotesPagos(LotesPagosArrec bdar, LotesPagosArrec tpar) {
        Long bdarId = bdar.getId();
        Long tparId = tpar.getId();
        LotesPagos lotesPagos = new LotesPagos(bdarId, tparId);

        lotesPagosService.save(lotesPagos);
    }
}
