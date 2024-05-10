package com.sena.prueba.IService;
import java.util.List;
import java.util.Optional;

import com.sena.prueba.Entity.ABaseEntity;

public interface IBaseService<T extends ABaseEntity> {
	
	List<T> all() throws Exception;
	
	Optional<T> findById(Long id) throws Exception;
	
	T save(T entity) throws Exception;
	
	void update(Long id, T entity) throws Exception;
	
	void delete(Long id) throws Exception;

}
