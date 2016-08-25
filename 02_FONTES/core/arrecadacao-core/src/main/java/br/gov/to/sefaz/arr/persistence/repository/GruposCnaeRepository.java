package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio responsavel pelas operações da entidade {@link br.gov.to.sefaz.arr.persistence.entity.GruposCnae}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/05/2016 14:20:00
 */
@Repository
public class GruposCnaeRepository extends BaseRepository<GruposCnae, GruposCnaePK> {

    /**
     * Verifica se existe Cnae referenciado neste grupo.
     *
     * @param cnaeFiscal identificação da Cnae Fiscal.
     * @return verdadeiro ou falso.
     */
    public boolean existsCnaeFiscal(String cnaeFiscal) {
        return exists(select -> select.where().equal("cnaeFiscal", cnaeFiscal));
    }

    /**
     * Remove o grupo pelo ID.
     *
     * @param idGrupoCnae identificação do grupo cnae.
     */
    public void deleteByGrupo(Integer idGrupoCnae) {
        delete(where -> where.where().equal("idGrupoCnae", idGrupoCnae));
    }
}
