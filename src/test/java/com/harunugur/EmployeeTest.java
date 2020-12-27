package com.harunugur;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.Assert.assertTrue;

public class EmployeeTest {

    private static final String PERSISTENCE_UNIT_NAME = "persistenceUnitName";
    private EntityManagerFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        // read the existing entries
        Query q = em.createQuery("select e from Employee e");
        // Employee should be empty

        // do we have entries?
        boolean createNewEntries = (q.getResultList().size() == 0);

        // No, so lets create new entries
        if (createNewEntries) {
            assertTrue(q.getResultList().size() == 0);

            for (int i = 0; i < 40; i++) {
                Employee employee = new Employee();
                employee.setName("Employee_" + i);
                em.persist(employee);
            }
            em.getTransaction().commit();

        }
        em.close();
    }

    @Test
    public void checkAvailableEmployees(){

        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select e from Employee e");
        // We should have 40 Employee in the database
        assertTrue(q.getResultList().size() == 40);

        em.close();
        factory.close();
    }

}
