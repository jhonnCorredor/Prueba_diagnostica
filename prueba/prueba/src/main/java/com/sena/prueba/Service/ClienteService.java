package com.sena.prueba.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.prueba.Entity.Clientes;
import com.sena.prueba.IRepository.IBaseRepository;
import com.sena.prueba.IRepository.IClientesRepository;
import com.sena.prueba.IService.IClientesService;

@Service
public class ClienteService extends ABaseService<Clientes> implements IClientesService{

	@Autowired
	private IClientesRepository repository;
	
	@Override
	protected IBaseRepository<Clientes, Long> getRepository() {
		return repository;
	}

	@Override
	public Long numClientes() {
		return repository.numClientes();
	}
}
