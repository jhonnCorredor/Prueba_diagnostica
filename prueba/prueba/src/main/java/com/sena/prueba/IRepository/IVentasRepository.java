package com.sena.prueba.IRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sena.prueba.Dto.IVentasDetalleDto;
import com.sena.prueba.Entity.Ventas;

@Repository
public interface IVentasRepository extends IBaseRepository<Ventas,Long>{

	@Query(value="SELECT\r\n"
			+ "COUNT(id) AS num_ventas \r\n"
			+ "FROM `ventas`",nativeQuery=true)
	Long numVenta();
	
	@Query(value="SELECT\r\n"
			+ "    v.*,\r\n"
			+ "    JSON_ARRAYAGG(\r\n"
			+ "        JSON_OBJECT(\r\n"
			+ "            'descripcion_id', dv.id,\r\n"
			+ "            'producto', dv.productos_id_producto,\r\n"
			+ "            'nombre_producto', p.nombre_producto,\r\n"
			+ "            'cantidad', dv.cantidad,\r\n"
			+ "            'descuento', dv.descuento,\r\n"
			+ "            'precio_unitario', dv.precio,\r\n"
			+ "            'sub_total', dv.sub_total\r\n"
			+ "        )\r\n"
			+ "    ) AS descripcion_venta\r\n"
			+ "FROM\r\n"
			+ "    ventas AS v\r\n"
			+ "INNER JOIN\r\n"
			+ "    descripcion_ventas AS dv ON dv.ventas_id_venta = v.id\r\n"
			+ "INNER JOIN\r\n"
			+ "    productos AS p ON p.id = dv.productos_id_producto\r\n"
			+ "WHERE\r\n"
			+ "    v.id = :id \r\n"
			+ "GROUP BY v.id;",nativeQuery=true)
	Optional<IVentasDetalleDto> findVenta(@Param("id") Long id);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM `descripcion_ventas` WHERE ventas_id_venta = :id ",nativeQuery=true)
	void eliminarDetalleFactura(@Param("id") Long id);
}
