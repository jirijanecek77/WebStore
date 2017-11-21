package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreItemServiceImpl implements StoreItemService
{
	StoreRepository storeRepository;

	public StoreItemServiceImpl(StoreRepository storeRepository)
	{
		this.storeRepository = storeRepository;
	}


}
