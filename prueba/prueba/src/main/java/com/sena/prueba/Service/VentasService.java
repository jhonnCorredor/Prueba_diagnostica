package com.sena.prueba.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.sena.prueba.Dto.VentasDetalleDto;
import com.sena.prueba.Entity.DescripcionVentas;
import com.sena.prueba.Entity.Ventas;
import com.sena.prueba.IRepository.IBaseRepository;
import com.sena.prueba.IRepository.IDescripcionVentasRepository;
import com.sena.prueba.IRepository.IVentasRepository;
import com.sena.prueba.IService.IVentasService;

@Service
public class VentasService extends ABaseService<Ventas> implements IVentasService{

	@Autowired
	private IVentasRepository repository;
	@Autowired
	@Lazy
	private IDescripcionVentasRepository descripcionRepository;
	
	@Override
	protected IBaseRepository<Ventas, Long> getRepository() {
		return repository;
	}

	@Override
	public Long numVenta() {
		return repository.numVenta();
	}
	
	@Override
	public Ventas saveDetailsVenta(VentasDetalleDto Dto) throws Exception{
		try {
        	Ventas entity = Dto.getVentas();
            Ventas save = getRepository().save(entity);
            
            if( !Dto.getDescripcion().isEmpty() && Dto.getDescripcion() != null) {
            	for(DescripcionVentas descripcion : Dto.getDescripcion()) {
            		descripcion.setVentas_id_venta(save);
            		descripcionRepository.save(descripcion);
            	}
            }
            return save;
        } catch (Exception e) {
            throw new Exception("Error al guardar la entidad: " + e.getMessage());
        }
	}
}
