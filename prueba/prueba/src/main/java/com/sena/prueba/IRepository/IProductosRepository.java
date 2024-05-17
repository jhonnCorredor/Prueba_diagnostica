package com.sena.prueba.IRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.prueba.Dto.IProductoDto;
import com.sena.prueba.Entity.Productos;

@Repository
public interface IProductosRepository extends IBaseRepository<Productos,Long>{

	@Query(value="SELECT\r\n"
			+ "COUNT(id) AS num_producto \r\n"
			+ "FROM `productos`",nativeQuery=true)
	Long numProducto();
	
	@Query(value="SELECT \r\n"
			+ "nombre_producto,\r\n"
			+ "cantidad\r\n"
			+ "FROM `productos` \r\n"
			+ "ORDER BY cantidad\r\n"
			+ "LIMIT 5",nativeQuery=true)
	List<IProductoDto> StockBajo();
}
