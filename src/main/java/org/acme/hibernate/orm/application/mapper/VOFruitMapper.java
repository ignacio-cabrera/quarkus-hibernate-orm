package org.acme.hibernate.orm.application.mapper;

import org.acme.hibernate.orm.application.vo.VOFruit;
import org.acme.hibernate.orm.domain.dto.ent.Fruit;

import java.time.LocalDateTime;

public class VOFruitMapper {
    public static VOFruit map(Fruit fruit) {
        VOFruit voFruit = new VOFruit();
        voFruit.setId("" + fruit.getId());
        voFruit.setName(fruit.getName());
        voFruit.setDescription("This is a " + fruit.getName());
        voFruit.setCreationDate(LocalDateTime.now());
        return voFruit;
    }
}
