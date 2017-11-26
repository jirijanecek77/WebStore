package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class StoreServiceImpl implements StoreService
{
	private final StoreRepository storeRepository;

	StoreServiceImpl(StoreRepository storeRepository)
	{
		this.storeRepository = storeRepository;
	}

	@Override
	@Transactional
	public List<Store> getView()
	{
		return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(e -> e.getName().toUpperCase()))
                .collect(Collectors.toList());
	}

	@Override
	public Store findById(Long id)
	{
		return storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Sklad s ID " + id + " nebyl nalezen!"));
	}

	@Override
	@Transactional
	public void delete(Long id)
	{
		storeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Store save(Store store)
	{
		return storeRepository.save(store);
	}
}
