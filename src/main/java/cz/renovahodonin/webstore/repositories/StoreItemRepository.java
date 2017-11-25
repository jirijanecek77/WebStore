package cz.renovahodonin.webstore.repositories;

import cz.renovahodonin.webstore.model.StoreItem;
import org.springframework.data.repository.CrudRepository;

public interface StoreItemRepository extends CrudRepository<StoreItem, Long>
{
}
