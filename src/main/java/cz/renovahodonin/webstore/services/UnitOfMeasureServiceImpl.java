package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.UnitOfMeasure;
import cz.renovahodonin.webstore.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService
{
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasure> getView()
    {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(e -> e.getName().toUpperCase()))
                .collect(Collectors.toList());
    }
}
