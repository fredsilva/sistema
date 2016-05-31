package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasFaixaValor;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerenciamento de dados da entidade {@link PedidoAreasFaixaValor}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 25/05/2016 16:35:00
 */
@Repository
public interface PedidoAreasFaixaValorRepository extends BaseRepository<PedidoAreasFaixaValor, Integer> {

    @Override
    @Modifying
    @Query("DELETE PedidoAreasFaixaValor WHERE idPedidoArea = :id")
    void delete(@Param("id") Integer id);
}
