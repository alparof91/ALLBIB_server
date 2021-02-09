package com.testcompany.dao;

import com.testcompany.entity.Admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminDao extends GenericDao<Admin> {

    private final EntityManagerFactory factory;

    public AdminDao(EntityManagerFactory factory) {
        super(Admin.class);
        this.factory = factory;
    }

    @Override
    public EntityManager getEntityManager() {
        try {
            return factory.createEntityManager();
        } catch (Exception ex) {
            System.out.println("The entity manager cannot be created!");
            return null;
        }
    }

    public List<Admin> findByFirstName(String firstName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> q = cb.createQuery(Admin.class);

        Root<Admin> c = q.from(Admin.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("firstName"), paramName));
        TypedQuery<Admin> query = entityManager.createQuery(q);
        query.setParameter(paramName, firstName);

        return query.getResultList();
    }

    public List<Admin> findBySecondName (String secondName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> q = cb.createQuery(Admin.class);

        Root<Admin> c = q.from(Admin.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("secondName"), paramName));
        TypedQuery<Admin> query = entityManager.createQuery(q);
        query.setParameter(paramName, secondName);

        return query.getResultList();
    }

    public List<Admin> findByPhone (String phone) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> q = cb.createQuery(Admin.class);

        Root<Admin> c = q.from(Admin.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("phone"), paramName));
        TypedQuery<Admin> query = entityManager.createQuery(q);
        query.setParameter(paramName, phone);

        return query.getResultList();
    }
}
