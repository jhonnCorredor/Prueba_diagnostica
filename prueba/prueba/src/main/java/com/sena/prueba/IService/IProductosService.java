package com.sena.prueba.IService;
import java.util.List;

import com.sena.prueba.Dto.IProductoDto;
import com.sena.prueba.Entity.Productos;

public interface IProductosService extends IBaseService<Productos>{

	Long numProducto();
	
	List<IProductoDto> StockBajo();
}
