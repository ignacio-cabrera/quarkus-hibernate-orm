package org.acme.hibernate.orm.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.hibernate.orm.application.mapper.EntFruitMapper;
import org.acme.hibernate.orm.application.mapper.VOFruitMapper;
import org.acme.hibernate.orm.application.vo.VOFruit;
import org.acme.hibernate.orm.domain.dto.ent.Fruit;
import org.acme.hibernate.orm.port.FruitService;
import org.acme.hibernate.orm.port.FruitStorageService;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FruitBO implements FruitService {

    @Inject
    FruitStorageService fruitStorageService;

    @Override
    public List<VOFruit> getAll() {
        List<Fruit> fruits = fruitStorageService.getAll();
        return fruits.stream().map(VOFruitMapper::map).toList();
    }

    public Optional<VOFruit> getById(String strId){
        Fruit found = fruitStorageService.getById(convertId(strId));
        //return found == null ? Optional.empty() : Optional.of(VOFruitMapper.map(found));
        if (found == null){
            return Optional.empty();
        } else {
            VOFruit vo = VOFruitMapper.map(found);
            return Optional.of(vo);
        }
    }

    @Override
    public VOFruit create(VOFruit vo) {
        Fruit saved = fruitStorageService.create(EntFruitMapper.map(vo));
        return VOFruitMapper.map(saved);
    }

    @Override
    public VOFruit update(String strId, VOFruit vo) {
        int id = convertId(strId);
        Fruit found = fruitStorageService.getById(id);
        if(found == null)
            throw new RuntimeException("Failed to update - cannot find Fruit with ID: " + strId);
        found = EntFruitMapper.map(vo);
        found.setId(id);

        Fruit updated = fruitStorageService.update(found);
        return VOFruitMapper.map(updated);
    }

    @Override
    public void delete(String strId) {
        Fruit found = fruitStorageService.getById(convertId(strId));
        if(found == null)
            throw new RuntimeException("Failed to delete - cannot find Fruit with ID: " + strId);

        fruitStorageService.delete(found);
    }

    private int convertId(String id){
        return Integer.parseInt(id);
    }
}
