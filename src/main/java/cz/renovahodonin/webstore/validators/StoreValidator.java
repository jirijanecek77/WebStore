package cz.renovahodonin.webstore.validators;

import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StoreValidator implements Validator
{
    private StoreRepository storeRepository;

    public StoreValidator(StoreRepository storeRepository)
    {
        this.storeRepository = storeRepository;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return Store.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        Store store = (Store) o;

        if (storeRepository.findAll().stream()
                .anyMatch(s -> !s.getId().equals(store.getId()) && s.getName().equals(store.getName())))
        {
            errors.rejectValue("name", "Duplicate.store.name");
        }
    }
}
