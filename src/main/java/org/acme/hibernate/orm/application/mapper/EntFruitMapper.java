package org.acme.hibernate.orm.application.mapper;

import org.acme.hibernate.orm.application.vo.VOFruit;
import org.acme.hibernate.orm.domain.dto.ent.Fruit;

import java.time.LocalDateTime;

public class EntFruitMapper {
    public static Fruit map(VOFruit voFruit){
        Fruit fruit = new Fruit();
        fruit.setName(voFruit.getName());
        return fruit;
    }
}
