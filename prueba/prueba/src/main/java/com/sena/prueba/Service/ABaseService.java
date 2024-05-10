package com.sena.prueba.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;

import com.sena.prueba.Entity.ABaseEntity;
import com.sena.prueba.IRepository.IBaseRepository;
import com.sena.prueba.IService.IBaseService;

public abstract class ABaseService<T extends ABaseEntity> implements IBaseService<T>{

	protected abstract IBaseRepository<T, Long> getRepository();
	
	@Override
	public List<T> all() throws Exception{
		return getRepository().findAll();
	}
	
	@Override
	public Optional<T> findById(Long id) throws Exception{
		Optional<T> op = getRepository().findById(id);
		if(op.isEmpty()) {
			throw new Exception("Registro no encontrado");
		}
		return op;
	}
	
	@Override
	public T save(T entity) throws Exception{
		try {
			return getRepository().save(entity);
		}catch(Exception e) {
			throw new Exception("Error al guardar la entidad: "+ e.getMessage() );
		}
	}
	
	@Override
	public void update(Long id,T entity)throws Exception{
		try {
			Optional<T> op = findById(id);
			T entityUpdate = op.get();
			
			String[] ignorePropierties = {"id"};
			BeanUtils.copyProperties(entity, entityUpdate, ignorePropierties);
			getRepository().save(entityUpdate);
		}catch(Exception e) {
			throw new Exception("Error al actualizar: "+ e.getMessage() );
		}
	}
	
	@Override
	public void delete(Long id)throws Exception{
		Optional<T> op = findById(id);
		T entidad= op.get();
		
		getRepository().delete(entidad);
	}
}
