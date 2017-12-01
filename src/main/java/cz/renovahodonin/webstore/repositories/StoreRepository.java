package cz.renovahodonin.webstore.repositories;

import cz.renovahodonin.webstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>
{
}
