package cz.renovahodonin.webstore.validators;

import cz.renovahodonin.webstore.exceptions.NotFoundException;
import cz.renovahodonin.webstore.model.Store;
import cz.renovahodonin.webstore.model.StoreItem;
import cz.renovahodonin.webstore.repositories.StoreRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreItemValidator implements Validator
{

	private StoreRepository storeRepository;

	public StoreItemValidator(StoreRepository storeRepository)
	{
		this.storeRepository = storeRepository;
	}

	@Override
	public boolean supports(Class<?> aClass)
	{
		return StoreItem.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object o, Errors errors)
	{
		StoreItem storeItem = (StoreItem) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		Long id = storeItem.getStore().getId();
		Store store = storeRepository.findById(id).orElseThrow(() -> new NotFoundException("Sklad s ID " + id + " nebyl nalezen!"));

		if (store.getItems().stream()
				.anyMatch(item -> !item.getId().equals(storeItem.getId()) && item.getName().equals(storeItem.getName())))
		{
			errors.rejectValue("name", "Duplicate.storeItem.name");
		}
	}
}
