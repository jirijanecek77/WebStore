package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService
{
	private final StoreRepository storeRepository;

	public StoreServiceImpl(StoreRepository storeRepository)
	{
		this.storeRepository = storeRepository;
	}

	@Override
	@Transactional
	public List<Store> getView()
	{
		List<Store> stores = new ArrayList<>();
		storeRepository.findAll().iterator().forEachRemaining(stores::add);
		return stores.stream().sorted(Comparator.comparing(Store::getName)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Store findById(Long id)
	{
		return storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Sklad s ID " + id + " nebyl nalezen!"));
	}

	@Override
	@Transactional
	public void deleteStore(Long id)
	{
		storeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Store saveStore(Store store)
	{
		return storeRepository.save(store);
	}
}
