package cz.renovahodonin.webstore.repositories;

import cz.renovahodonin.webstore.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long>
{
}
