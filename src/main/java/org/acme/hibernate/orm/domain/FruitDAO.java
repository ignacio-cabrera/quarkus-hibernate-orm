package org.acme.hibernate.orm.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.hibernate.orm.domain.dto.ent.Fruit;
import org.acme.hibernate.orm.domain.port.FruitStorageService;

import java.util.List;

@ApplicationScoped
public class FruitDAO implements FruitStorageService {

    @Inject
    EntityManager entityManager;

//    @Override
//    public List<Fruit> getAll() {
//        return List.of();
//    }
//
//    @Override
//    public Fruit getById(Integer id) {
//        return null;
//    }

    @Override
    public Fruit create(String name) {
        Fruit newFruit = new Fruit(name);
        entityManager.persist(newFruit);
        return newFruit;
    }

//    @Override
//    public Fruit update(Integer id, String name) {
//        return null;
//    }
//
//    @Override
//    public void delete(Integer id) {
//
//    }
}
