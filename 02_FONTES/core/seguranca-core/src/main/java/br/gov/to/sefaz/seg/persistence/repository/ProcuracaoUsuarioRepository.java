package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectJunctionBuilder;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class ProcuracaoUsuarioRepository extends BaseRepository<ProcuracaoUsuario, Long> {

    /**
     * Encontra uma procuração baseado no CPF do procurador e do procurado.
     *
     * @param cpfOrigem cpf do procurado
     * @param procuradorCpf cpf do procurador
     * @return procuração
     */
    public ProcuracaoUsuario findByCpf(String cpfOrigem, String procuradorCpf) {
        return findOne("pu", select -> selectByCpfProcurado(select, procuradorCpf)
                .and().equal("pu.cpfOrigem", cpfOrigem));
    }

    /**
     * Encontra uma procuração baseado no CPF do procurador e cnpj do procurado.
     *
     * @param cnpjOrigem cnpj do procurado
     * @param procuradorCpf cpf do procurador
     * @return procuração
     */
    public ProcuracaoUsuario findByCnpj(String cnpjOrigem, String procuradorCpf) {
        return findOne("pu", select -> selectByCpfProcurado(select, procuradorCpf)
                .and().equal("pu.cnpjOrigem", cnpjOrigem));
    }

    private HqlSelectJunctionBuilder selectByCpfProcurado(HqlSelectBuilder select, String procuradorCpf) {
        return select.leftJoinFetch("pu.procuracaoOpcoes")
                .where().equal("pu.cpfProcurado", procuradorCpf);
    }

    /**
     * Busca todas as Procurações de um {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}.
     *
     * @param cpfUsuario cpf do usuário.
     * @return Lista de Procurados.
     */
    public Set<ProcuracaoUsuario> findAllByCpfProcurado(String cpfUsuario) {
        return find("po", select -> select
                .leftJoinFetch("po.cnpjOrigemProcuracao", "cnpj")
                .leftJoinFetch("po.cpfOrigemProcuracao", "cpf")
                .leftJoinFetch("po.procuracaoOpcoes","proc")
                .where().equal("po.cpfProcurado", cpfUsuario))
                .stream()
                .collect(Collectors.toSet());
    }
}
