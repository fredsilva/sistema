package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.Feriado;
import br.gov.to.sefaz.par.gestao.persistence.entity.FeriadoPK;
import br.gov.to.sefaz.par.gestao.persistence.enums.TipoFeriadoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.par.gestao.persistence.entity.Feriado}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 20/09/2016 17:20:21
 */
@Repository
public class FeriadoRepository extends BaseRepository<Feriado, FeriadoPK> {

    /**
     * Se o ano do feriado for 0(zero), é comemorado anualmente.
     */
    private static final int FERIADO_ANUAL = 0;

    /**
     * Verifica se uma data é um feriado.
     *
     * @param data             data a ser verificada.
     * @param codMunicipioIbge código ibge do município.
     * @return se é feriado.
     */
    public boolean isFeriadoMunicipalOuNacional(LocalDate data, Integer codMunicipioIbge) {
        return exists(select -> select
                .where().equal("diaFeriado", data.getDayOfMonth())
                .and().equal("mesFeriado", data.getMonthValue())
                .and().condition(whereBuilder -> whereBuilder
                        .equal("anoFeriado", data.getYear())
                        .or().equal("anoFeriado", FERIADO_ANUAL))
                .and().condition(whereBuilder -> whereBuilder
                        .equal("tipoFeriado", TipoFeriadoEnum.NACIONAL)
                        .or().equal("tipoFeriado", TipoFeriadoEnum.MUNICIPAL)
                        .and().equal("codigoIbge", codMunicipioIbge)));
    }

}
