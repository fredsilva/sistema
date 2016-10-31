package br.gov.to.sefaz.arr.dare.service.impl;

import br.gov.to.sefaz.arr.dare.service.DareDetalheService;
import br.gov.to.sefaz.arr.dare.service.DareService;
import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.DareDetalhe;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.repository.DareRepository;
import br.gov.to.sefaz.arr.processamento.service.converter.DareEConverter;
import br.gov.to.sefaz.arr.processamento.service.domain.DareE;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.exception.UnhandledException;
import br.gov.to.sefaz.par.gestao.business.service.FeriadoService;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.util.barcode.BarCodeHandler;
import br.gov.to.sefaz.util.barcode.domain.OrigemGeracaoBarraEnum;
import br.gov.to.sefaz.util.barcode.domain.OrigemSistemaEnum;
import br.gov.to.sefaz.util.mail.MailSender;
import br.gov.to.sefaz.util.pdf.PdfRender;
import br.gov.to.sefaz.util.properties.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/07/2016 19:17:00
 */
@Service
public class DareServiceImpl extends DefaultCrudService<Dare, Long> implements DareService {

    public static final String SEND_DARE_EMAIL_CONTEXT = "SEND_DARE_EMAIL_CONTEXT";
    /**
     * Usuário que Emitiu o DARE. Quando for emitido pelo Portal ficará como Usuário FIXO "INTERNET".
     */
    private static final String USUARIO_PORTAL = "INTERNET";
    private static final char ZERO_PAD_CHAR = '0';
    private static final int NOSSO_NUMERO_PAD_SIZE = 8;
    private static final int MONTH_PAD_SIZE = 2;

    private final DareEConverter dareEConverter;
    private final DareDetalheService dareDetalheService;
    private final FeriadoService feriadoService;
    private final MailSender mailSender;

    @Autowired
    public DareServiceImpl(DareRepository repository, DareEConverter dareEConverter,
            MailSender mailSender, DareDetalheService dareDetalheService, FeriadoService feriadoService) {
        super(repository);
        this.dareEConverter = dareEConverter;
        this.mailSender = mailSender;
        this.dareDetalheService = dareDetalheService;
        this.feriadoService = feriadoService;
    }

    /**
     * Verifica se o itens da coleção de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} tem origem somente
     * nos débitos de {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#COBRANCA_TRANSITO} e/ou
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#AUTO_INFRACAO_NLD}.
     *
     * @param dareDetalhes coleção de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} a ser verificada.
     * @return se o itens da coleção de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} tem origem somente
     *     nos débitos de {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#COBRANCA_TRANSITO} e/ou
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#AUTO_INFRACAO_NLD}.
     */
    private static boolean hasOnlyOrigemTransito(Collection<DareDetalhe> dareDetalhes) {
        return dareDetalhes.stream().noneMatch(dareDetalhe ->
                !(OrigemDebitoEnum.COBRANCA_TRANSITO.equals(dareDetalhe.getOrigemDebito())
                        || OrigemDebitoEnum.AUTO_INFRACAO_NLD.equals(dareDetalhe.getOrigemDebito())));
    }

    /**
     * Gera um pdf contendo as informações de pagamento de um dare-e.
     *
     * @param dare informações do dare-e
     * @return bytes do pdf
     */
    private byte[] generateDareEPdf(Dare dare) {
        URL report = DareServiceImpl.class.getResource("/report/dare-e.html");
        DareE dareE = dareEConverter.convert(dare);

        return PdfRender.createBytes(report, dareE);
    }

    @Override
    public void sendDareEmail(@ValidationSuite(context = SEND_DARE_EMAIL_CONTEXT) DareEmail dareEmail) {
        try {
            String path = getPrintPath(dareEmail);
            String message = dareEmail.getMensagem() + "\n \n " + path;
            String[] mailTo = dareEmail.getDestinatarios().stream().toArray(String[]::new);

            mailSender.sendMail(dareEmail.getAssunto(), message, false, mailTo);

        } catch (MessagingException e) {
            throw new UnhandledException("Erro ao enviar e-mail DARE.", e);
        }
    }

    @Override
    public byte[] createDareEPdf(Long nossoNumero) {
        Dare dare = getRepository().findDareByNossoNumero(nossoNumero);
        return generateDareEPdf(dare);
    }

    private String getPrintPath(@ValidationSuite(context = SEND_DARE_EMAIL_CONTEXT) DareEmail dareEmail) {
        return getDarePath(dareEmail.getNossoNumero());
    }

    @Override
    public String getDarePath(Long nossoNumero) {
        return "https://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
                + ":" + AppProperties.getAppProperty("host.port").orElse("8443")
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                + "/public/imprime-dare/" + nossoNumero;
    }

    @Override
    public Dare save(@ValidationSuite(context = ValidationContext.SAVE) Dare entity) {
        Collection<DareDetalhe> dareDetalhes = entity.getDareDetalheCollection();
        buildDareData(entity, dareDetalhes);
        Dare dare = super.save(entity);
        dareDetalhes.forEach(dd -> dd.setIdNossoNumeroDare(dare.getIdNossoNumeroDare()));
        dareDetalhes = dareDetalheService.save(dareDetalhes);
        dare.setDareDetalheCollection(dareDetalhes);
        return dare;
    }

    @Override
    protected DareRepository getRepository() {
        return (DareRepository) super.getRepository();
    }

    private void buildDareData(Dare entity, Collection<DareDetalhe> dareDetalhes) {
        String nossoNumero = buildProximoNossoNumero();
        LocalDate dataVencimento = getDataVencimento(dareDetalhes, entity.getIdMunicipioEmissao());
        String barCode = BarCodeHandler.buildBarCode(nossoNumero, entity.getValorTotalDare(), dataVencimento,
                OrigemSistemaEnum.DARE_E, OrigemGeracaoBarraEnum.DARE);

        entity.setIdNossoNumeroDare(Long.valueOf(nossoNumero));
        entity.setBarraDare(barCode);
        entity.setDataVencimento(dataVencimento);
        entity.setUsuarioEmissor(getUsuarioEmissor());
        entity.setDareDetalheCollection(null);
    }

    /**
     * Monta o próximo nosso número.
     *
     * @return nosso número;
     */
    private String buildProximoNossoNumero() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String year = LocalDate.now().format(formatter);
        String sequence = getRepository().getNextValSeq().toString();

        return year + StringUtils.leftPad(sequence, NOSSO_NUMERO_PAD_SIZE, ZERO_PAD_CHAR);
    }

    /**
     * Obtém a data de vencimento do Dare.
     *
     * @param dareDetalhes coleção de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} a partir da qual
     *                     será obtida a data de vencimento.
     * @return data de vencimento do Dare.
     */
    private LocalDate getDataVencimento(Collection<DareDetalhe> dareDetalhes, Integer idMunicipio) {
        LocalDate dataVencimento = LocalDate.now();

        if (dareDetalhes.size() == 1) {
            LocalDate dataVencimentoCC = dareDetalhes.iterator().next().getDataVencimentoContaCorrente();

            if (LocalDate.now().isBefore(dataVencimentoCC)) {
                dataVencimento = dataVencimentoCC;
            }
        } else if (dareDetalhes.size() > 1 && hasOnlyOrigemTransito(dareDetalhes) && isPeriodoAnoAtual(dareDetalhes)) {
            dataVencimento = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        }

        return feriadoService.getDataUtil(dataVencimento, idMunicipio);
    }

    /**
     * Verifica se o período referência (mês/ano) dos itens da lista de
     * {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} correspondem ao período atual.
     *
     * @param dareDetalhes lista de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} a ser verificada.
     * @return se corresponde ao período de referência atual.
     */
    private boolean isPeriodoAnoAtual(Collection<DareDetalhe> dareDetalhes) {
        Integer periodoAnoAtual = getPeriodoAnoAtual();

        return !dareDetalhes.stream().anyMatch(debito ->
                debito.getPeriodoReferencia().intValue() != periodoAnoAtual.intValue());
    }

    private Integer getPeriodoAnoAtual() {
        String mesAtual = StringUtils.leftPad(String.valueOf(LocalDate.now().getMonthValue()),
                MONTH_PAD_SIZE, ZERO_PAD_CHAR);
        String anoAtual = String.valueOf(LocalDate.now().getYear());
        return Integer.valueOf(anoAtual + mesAtual);
    }

    /**
     * Usuário que Emitiu o DARE. Quando for pelo sistema será o CPF ou CNPJ da pessoa que está logada no Sistema e
     * quando for emitido pelo Portal ficará como Usuário FIXO "INTERNET".
     *
     * @return usuário emissor do Dare.
     */
    private String getUsuarioEmissor() {
        if (AuthenticatedUserHandler.isAuthenticated()) {
            return AuthenticatedUserHandler.getCpf();
        }

        return USUARIO_PORTAL;
    }

}
