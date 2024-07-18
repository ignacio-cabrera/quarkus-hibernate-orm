package org.acme.hibernate.orm.port;

import org.acme.hibernate.orm.application.vo.VOFruit;

import java.util.List;

public interface FruitService {
    List<VOFruit> getAll();
    VOFruit create(VOFruit fruit);
}
