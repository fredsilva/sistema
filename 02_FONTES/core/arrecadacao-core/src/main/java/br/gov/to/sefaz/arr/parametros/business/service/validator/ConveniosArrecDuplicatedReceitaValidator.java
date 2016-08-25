package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validação para duplicação de {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} na lista de
 * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#getReceitas()}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 12:04:00
 */
@Component
public class ConveniosArrecDuplicatedReceitaValidator implements ServiceValidator<ConveniosReceitas> {

    private final ReceitasRepository repository;

    @Autowired
    public ConveniosArrecDuplicatedReceitaValidator(
            ReceitasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return false;
    }

    @Override
    public Set<CustomViolation> validate(ConveniosReceitas target) {
        List<Receitas> allReceitasByIdConvenio = repository.findAllReceitasByIdConvenio(target.getIdConvenio());

        return validateDuplicatedReceita(allReceitasByIdConvenio, target.getIdReceita());
    }

    /**
     * Valida se uma lista de {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} já possui um registro
     * com a mesma {@link br.gov.to.sefaz.arr.persistence.entity.Receitas#idReceita}.
     *
     * @param receitas Lista de receita a ser validada
     * @param idReceita código da receita
     * @return lista de violações encontradas
     */
    public Set<CustomViolation> validateDuplicatedReceita(List<Receitas> receitas, Integer idReceita) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        receitas.stream()
                .filter(receita -> receita.getIdReceita().equals(idReceita))
                .findAny()
                .ifPresent(conveniosTarifas -> {
                    String tarifaCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                            "parametros.convenioArrec.receita.duplicada");
                    customViolations.add(new CustomViolation(tarifaCadastrado));
                });

        return customViolations;
    }
}
