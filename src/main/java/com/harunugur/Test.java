package com.harunugur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persistenceUnitName");
        EntityManager entityManager = emfactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query q = entityManager.createQuery("select m from Employee m");
        System.out.println("Size =>>> " + q.getResultList().size());
        for (int i = 0; i < 40; i++) {
            Employee employee = new Employee();
            employee.setName("Employee_" + i);
            entityManager.persist(employee);
        }

        //Create Department Entity
        Department department = new Department();
        department.setName("Development");
        entityManager.persist(department);

        Employee employee_1 = entityManager.find(Employee.class,1L);
        System.out.println(employee_1);

        if (employee_1 != null){
            employee_1.setName("Harun ugur");
            employee_1.setDepartment(department);
            entityManager.persist(department);
        }
        System.out.println(employee_1);

        if (employee_1 != null)
           // entityManager.remove(employee_1);

        System.out.println("***** After Delete ******");
        employee_1 = entityManager.find(Employee.class,121L);
        System.out.println(employee_1);

        Query qq = entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
        qq.setParameter("id",Long.valueOf(2));
        List resultList = qq.getResultList();
        if (resultList.size()>0){
            Employee employee_2 = (Employee) resultList.get(0);
            System.out.println("Employee 2 =>>> " + employee_2);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        emfactory.close();
    }
}
