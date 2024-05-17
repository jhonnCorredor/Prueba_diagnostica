package com.sena.prueba.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.prueba.Dto.ApiResponseDto;
import com.sena.prueba.Dto.IProductoDto;
import com.sena.prueba.Entity.Productos;
import com.sena.prueba.IService.IProductosService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/api/productos")
public class ProductosController extends ABaseController<Productos,IProductosService>{

	public ProductosController(IProductosService service) {
		super(service, "Productos");
	}

	@GetMapping("/num")
    public ResponseEntity<ApiResponseDto<Long>> numProducto() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Long>("Datos obtenidos", service.numProducto(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Long>(e.getMessage(), null, false));
        }
    }
	
	@GetMapping("/stock")
    public ResponseEntity<ApiResponseDto<List<IProductoDto>>> Stock() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<List<IProductoDto>>("Datos obtenidos", service.StockBajo(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IProductoDto>>(e.getMessage(), null, false));
        }
    }
}
