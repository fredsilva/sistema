package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Repositório de acesso à base dados da entidade {@link UsuarioSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface UsuarioSistemaRepository extends BaseRepository<UsuarioSistema, String> {


    /**
     * Altera o estado de bloqueado do ususurio, incluindo a data de bloqueio.
     *
     * @param bloqueado true se o ususario esta bloqueado
     * @param dataDesbloqueio até quando o ususario deve ficar bloqueado ou null caso esteja sendo desbloqueado
     * @param cpf cpf do usaurio
     */
    @Modifying
    @Query("UPDATE UsuarioSistema SET estaBloqueado = :bloqueado, dataDesbloqueio = :data WHERE cpfUsuario = :cpf")
    void updateEstaBloqueado(@Param("bloqueado") boolean bloqueado, @Param("data") LocalDateTime dataDesbloqueio,
            @Param("cpf") String cpf);
}
