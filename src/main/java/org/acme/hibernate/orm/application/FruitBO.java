package org.acme.hibernate.orm.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.hibernate.orm.application.mapper.EntFruitMapper;
import org.acme.hibernate.orm.application.mapper.VOFruitMapper;
import org.acme.hibernate.orm.application.vo.VOFruit;
import org.acme.hibernate.orm.domain.dto.ent.Fruit;
import org.acme.hibernate.orm.port.FruitService;
import org.acme.hibernate.orm.port.FruitStorageService;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FruitBO implements FruitService {

    @Inject
    FruitStorageService fruitStorageService;

    @Override
    public List<VOFruit> getAll() {
        List<Fruit> fruits = fruitStorageService.getAll();
        return fruits.stream().map(VOFruitMapper::map).toList();
    }

    @Override
    public VOFruit create(VOFruit vo) {
        Fruit saved = fruitStorageService.create(EntFruitMapper.map(vo));
        return VOFruitMapper.map(saved);
    }
}
