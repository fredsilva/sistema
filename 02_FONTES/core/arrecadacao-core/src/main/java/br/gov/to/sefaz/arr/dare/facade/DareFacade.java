package br.gov.to.sefaz.arr.dare.facade;

import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Fachada para acesso a serviço referente a {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/08/2016 14:25:00
 */
public interface DareFacade extends CrudFacade<Dare, Long> {

    /**
     * Retorna uma lista com todas as {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
     *
     * @return todas as {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional} da base de dados
     */
    List<UnidadeOrganizacional> getAllInstituicoesWithTipoUnidadeSefazAndOrgaosExternos();

    /**
     * Retorna um Contribuinte conforme o {@link br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum} e sua
     * identificação {@link br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum} com o código respectivo.
     *
     * @param contribuinteFilter contém os atributos necessários para a busca.
     * @return Contribuinte encontrado na base de dados conforme os parâmetros fornecidos
     */
    Contribuintes searchContribuinteBy(DareContribuinteFilter contribuinteFilter);

    /**
     * Busca as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} pelo seu ID.
     *
     * @param idReceitas id da receita {@link br.gov.to.sefaz.arr.persistence.entity.Receitas#idReceita}
     * @return uma coleção com as receitas encontradas
     */
    Collection<Receitas> searchReceitasWithId(Set<Integer> idReceitas);

    /**
     * Busca as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} pelo id da Origem do Débito.
     *
     * @param origemDebitoId id da Origem do Débito {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#code}.
     * @return uma coleção com as receitas encontradas.
     */
    Collection<Receitas> searchReceitasWithOrigemDebitoId(Integer origemDebitoId);

    /**
     * Busca as {@link ReceitasTaxas} pelo {@link Receitas#idReceita}.
     *
     * @param idReceita identificação da receita.
     * @return Lista de {@link ReceitasTaxas}.
     */
    Collection<ReceitasTaxas> searchReceitasTaxasWithReceitaId(Integer idReceita);

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o documento.
     *
     * @param debitoParcialFilter contém os filtros para identificação de
     *                            {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente}.
     * @return a lista com os debitos encontrados.
     */
    List<DebitosContaCorrente> searchDebitosWithFilter(DebitoParcialFilter debitoParcialFilter);

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o
     * {@link br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter} e do
     * {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente#getTipoConta()} que deve ser
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#IPVA}.
     *
     * @param debitoIpvaFilter contém os filtros de ano para IPVA
     * @return lista com os debitos encontrados
     */
    List<DebitosContaCorrente> searchDebitoIpvaByAnoReferencia(DebitoIpvaFilter debitoIpvaFilter);

    /**
     * Busca o {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional#getCodigoTipoUnidade()} conforme o
     * id da Unidade Organizacional.
     *
     * @param idUnidade é a identificação da
     *                  {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional#identificacaoUnidOrganizac}
     * @return O caractere que representa o tipo de unidade e um certa Unidade Organizacional
     */
    Character getTipoUnidadeByUnidadeOrganizacional(Long idUnidade);

    /**
     * Envia e-mail de acordo com dados informados.
     *
     * @param dareEmail {@link DareEmail}.
     */
    void sendEmail(DareEmail dareEmail);

    /**
     * Método para criação de um PDF do DARE-E.
     *
     * @param nossoNumero {@link Dare#getIdNossoNumeroDare()}
     * @return PDF referente ao Nosso Número da URL recebida por e-mail.
     */
    byte[] createDareEPdf(Long nossoNumero);

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa} conforme o
     * {@link br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter}.
     *
     * @param debitoParcialFilter contém os filtros para identificação de
     *                            {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa}.
     * @return a lista com as notas avulsas encontradas.
     */
    List<NotaAvulsa> searchNotasAvulsasByDebitoParcialFilter(DebitoParcialFilter debitoParcialFilter);

    /**
     * Retorna o caminho para a página com o DARE-e Gerado conforme o nosso número.
     *
     * @param nossoNumero número de identificação do DARE-e
     * @return caminho para a página com o DARE-e para ser impresso
     */
    String getDarePath(Long nossoNumero);
}
