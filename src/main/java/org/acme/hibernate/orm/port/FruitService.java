package org.acme.hibernate.orm.port;

import org.acme.hibernate.orm.application.vo.VOFruit;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    List<VOFruit> getAll();
    Optional<VOFruit> getById(String id);
    VOFruit create(VOFruit fruit);
    VOFruit update(String id, VOFruit fruit);
    void delete(String id);
}
