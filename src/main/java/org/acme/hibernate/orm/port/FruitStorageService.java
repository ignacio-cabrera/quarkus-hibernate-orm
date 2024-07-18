package org.acme.hibernate.orm.port;

import org.acme.hibernate.orm.domain.dto.ent.Fruit;

import java.util.List;

public interface FruitStorageService {
    List<Fruit> getAll();
//    Fruit getById(Integer id);
    Fruit create(Fruit fruit);
//    Fruit update(Integer id, String name);
//    void delete(Integer id);
}