package com.sena.prueba.IService;
import com.sena.prueba.Dto.VentasDetalleDto;
import com.sena.prueba.Entity.Ventas;

public interface IVentasService extends IBaseService<Ventas>{

	Long numVenta();
	Ventas saveDetailsVenta(VentasDetalleDto Dto) throws Exception;
}
