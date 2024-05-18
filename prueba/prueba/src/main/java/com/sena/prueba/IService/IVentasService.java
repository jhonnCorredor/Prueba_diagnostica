package com.sena.prueba.IService;
import java.util.Optional;

import com.sena.prueba.Dto.IVentasDetalleDto;
import com.sena.prueba.Dto.VentasDetalleDto;
import com.sena.prueba.Entity.Ventas;

public interface IVentasService extends IBaseService<Ventas>{

	Long numVenta();
	Ventas saveDetailsVenta(VentasDetalleDto Dto) throws Exception;
	Optional<IVentasDetalleDto> findVenta(Long id)throws Exception;
	void updateDetailsVenta(VentasDetalleDto Dto,Long id)throws Exception;
}
