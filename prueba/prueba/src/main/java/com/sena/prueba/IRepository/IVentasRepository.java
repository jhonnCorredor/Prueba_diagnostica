package com.sena.prueba.IRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.prueba.Entity.Ventas;

@Repository
public interface IVentasRepository extends IBaseRepository<Ventas,Long>{

	@Query(value="SELECT\r\n"
			+ "COUNT(id) AS num_ventas \r\n"
			+ "FROM `ventas`",nativeQuery=true)
	Long numVenta();
}
