package com.sena.prueba.Service;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sena.prueba.Dto.IVentasDetalleDto;
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

	@Override
	public Optional<IVentasDetalleDto> findVenta(Long id) throws Exception {
		Optional<IVentasDetalleDto> op = repository.findVenta(id);
		if(op.isEmpty()) {
			throw new Exception("Registro no encontrado");
		}
		return op;
	}

	@Override
	public void updateDetailsVenta(VentasDetalleDto Dto, Long id) throws Exception {
		try {
			Optional<Ventas> op = findById(id);
			Ventas entityUpdate = op.get();
			Ventas entity = Dto.getVentas();
			
			String[] ignorePropierties = {"id"};
			BeanUtils.copyProperties(entity, entityUpdate, ignorePropierties);
			Ventas update = getRepository().save(entityUpdate);
			
			repository.eliminarDetalleFactura(update.getId());
			
			if( !Dto.getDescripcion().isEmpty() && Dto.getDescripcion() != null) {
            	for(DescripcionVentas descripcion : Dto.getDescripcion()) {
            		descripcion.setVentas_id_venta(update);
            		descripcionRepository.save(descripcion);
            	}
            }
		}catch(Exception e) {
			throw new Exception("Error al actualizar: "+ e.getMessage() );
		}
	}
	
	
}
