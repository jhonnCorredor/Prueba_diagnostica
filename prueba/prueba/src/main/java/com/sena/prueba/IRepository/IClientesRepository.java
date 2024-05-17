package com.sena.prueba.IRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.prueba.Entity.Clientes;

@Repository
public interface IClientesRepository extends IBaseRepository<Clientes,Long>{

	@Query(value="SELECT\r\n"
			+ "COUNT(id) AS num_clientes \r\n"
			+ "FROM `clientes`",nativeQuery=true)
	Long numClientes();
}
