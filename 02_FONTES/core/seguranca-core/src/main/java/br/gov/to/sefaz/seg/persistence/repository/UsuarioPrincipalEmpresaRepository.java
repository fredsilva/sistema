package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;

/**
 * Repositório de acesso à base dados da entidade
 * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 31/08/2016 15:57:00
 */
@Repository
public class UsuarioPrincipalEmpresaRepository extends BaseRepository<UsuarioPrincipalEmpresa, Long> {

    /**
     * Verifica se o CNPJ informado em tela existe na base de dados.
     *
     * @param cnpjEmpresa cnpj da empresa.
     * @return verdadeiro ou falso.
     */
    public Boolean cnpjEmpresaExists(String cnpjEmpresa) {
        return existsNative("sefaz_cci.ta_pessoa_juridica", "pj", select -> select.where()
                .equal("pj.NUM_BASE_CNPJ", cnpjEmpresa));
    }

    /**
     * Verifica se o CPF do usuário logado é o representante legal da empresa de CNPJ informado.
     *
     * @param cnpjRaiz cnpj informado em tela.
     * @param cpf      CPF do usuário logado.
     * @return verdadeiro ou falso.
     */
    public boolean isLegalRepresentative(String cnpjRaiz, String cpf) {
        return existsNative("sefaz_cci.ta_representante_legal", "trl", select -> select
                .where()
                .equal("trl.num_base_cnpj", cnpjRaiz)
                .and().equal("trl.num_cpf_representante", cpf));
    }

    /**
     * Verifica se o mandato do representante legal ainda é vigente.
     *
     * @param cpf cpf do usuário logado.
     * @return verdadeiro ou falso.
     */
    public boolean isMandatoStillValid(String cpf) {
        return existsNative("sefaz_cci.ta_representante_legal", "trl", select -> select
                .where()
                .isNull("trl.data_final_mandato")
                .or().greaterThan("trl.data_final_mandato", GregorianCalendar.getInstance().getTime())
                .and().equal("trl.num_cpf_representante", cpf));
    }

    /**
     * Busca o nome da empresa informada através do CNPJ da mesma.
     *
     * @param cnpjRaiz cnpj da empresa.
     * @return nome da empresa.
     */
    public String findCompanyName(String cnpjRaiz) {
        return findOneColumnNative("sefaz_cci.ta_pessoa_juridica", "pj", "pj.nome_razao_social", select -> select
                .where().equal("pj.num_base_cnpj", cnpjRaiz));
    }
}
