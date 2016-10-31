package br.gov.to.sefaz.par.gestao.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.par.gestao.business.service.FeriadoService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Feriado;
import br.gov.to.sefaz.par.gestao.persistence.entity.FeriadoPK;
import br.gov.to.sefaz.par.gestao.persistence.repository.FeriadoRepository;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Serviços para manipulação de {@link br.gov.to.sefaz.par.gestao.persistence.entity.Feriado}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/09/2016 16:33:00
 */
@Service
public class FeriadoServiceImpl extends DefaultCrudService<Feriado, FeriadoPK> implements FeriadoService {

    @Autowired
    public FeriadoServiceImpl(BaseRepository<Feriado, FeriadoPK> repository) {
        super(repository);
    }

    /**
     * Retorna a próxima data útil a partir da data passada, caso a mesma não seja.
     *
     * @param data data a ser verificada
     * @return data útil.
     */
    public LocalDate getDataUtil(LocalDate data, Integer codIbgeMunicipio) {
        if (data.getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            data.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        return getNaoFeriado(data, codIbgeMunicipio);
    }

    /**
     * Obtém a próxima data útil do município a partir da data passada, caso a mesma não seja.
     *
     * @param data data a ser verificada.
     * @param codIbgeMunicipio código ibge do município.
     * @return data não feriado.
     */
    private LocalDate getNaoFeriado(LocalDate data, Integer codIbgeMunicipio) {
        if (getRepository().isFeriadoMunicipalOuNacional(data, codIbgeMunicipio)) {
            data = data.plusDays(1);
            getDataUtil(data, codIbgeMunicipio);
        }

        return data;
    }

    @Override
    protected FeriadoRepository getRepository() {
        return (FeriadoRepository) super.getRepository();
    }

}
