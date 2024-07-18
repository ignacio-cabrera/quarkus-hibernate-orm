package org.acme.hibernate.orm.domain.port;

import org.acme.hibernate.orm.domain.dto.ent.Fruit;

import java.util.List;

public interface FruitStorageService {
//    List<Fruit> getAll();
//    Fruit getById(Integer id);
    Fruit create(String name);
//    Fruit update(Integer id, String name);
//    void delete(Integer id);
}
