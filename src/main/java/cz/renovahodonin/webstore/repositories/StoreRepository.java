package cz.renovahodonin.webstore.repositories;

import cz.renovahodonin.webstore.model.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Long>
{
}
