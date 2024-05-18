package com.sena.prueba.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.prueba.Dto.ApiResponseDto;
import com.sena.prueba.Dto.IVentasDetalleDto;
import com.sena.prueba.Entity.Ventas;
import com.sena.prueba.IService.IVentasService;
import com.sena.prueba.Dto.VentasDetalleDto;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/api/ventas")
public class VentasController extends ABaseController<Ventas,IVentasService>{

	public VentasController(IVentasService service) {
		super(service, "Ventas");
	}

	@GetMapping("/num")
    public ResponseEntity<ApiResponseDto<Long>> numVentas() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Long>("Datos obtenidos", service.numVenta(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Long>(e.getMessage(), null, false));
        }
    }
	
	@PostMapping("/ventasDescripcion")
    public ResponseEntity<ApiResponseDto<Ventas>> save(@RequestBody VentasDetalleDto entity) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Ventas>("Datos guardados", service.saveDetailsVenta(entity), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Ventas>(e.getMessage(), null, false));
        }
    }
	
	@GetMapping("/ventaDetalle/{id}")
    public ResponseEntity<ApiResponseDto<Optional<IVentasDetalleDto>>> ventaFind(@PathVariable Long id) {
        try {
            Optional<IVentasDetalleDto> entity = service.findVenta(id);
            return ResponseEntity.ok(new ApiResponseDto<Optional<IVentasDetalleDto>>("Registro encontrado", entity, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Optional<IVentasDetalleDto>>(e.getMessage(), null, false));
        }
    }
	
	@PutMapping("/ventaDetalle/{id}")
    public ResponseEntity<ApiResponseDto<Ventas>> updateVentaDetail(@PathVariable Long id, @RequestBody VentasDetalleDto entity) {
        try {
            service.updateDetailsVenta(entity, id);
            return ResponseEntity.ok(new ApiResponseDto<Ventas>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Ventas>(e.getMessage(), null, false));
        }
    }
}
