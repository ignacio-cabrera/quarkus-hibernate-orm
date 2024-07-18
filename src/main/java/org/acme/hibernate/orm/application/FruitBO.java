package org.acme.hibernate.orm.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class FruitBO {
    @Inject
    EntityManager entityManager;


}
