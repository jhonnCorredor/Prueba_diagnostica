package com.sena.prueba.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.prueba.Dto.ApiResponseDto;
import com.sena.prueba.Entity.Clientes;
import com.sena.prueba.IService.IClientesService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/api/cliente")
public class ClienteController extends ABaseController<Clientes,IClientesService>{

	public ClienteController(IClientesService service) {
		super(service, "Clientes");
	}

	
	@GetMapping("/num")
    public ResponseEntity<ApiResponseDto<Long>> numCliente() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Long>("Datos obtenidos", service.numClientes(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Long>(e.getMessage(), null, false));
        }
    }
}
