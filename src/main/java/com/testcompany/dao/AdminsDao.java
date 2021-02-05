package com.testcompany.dao;

import com.testcompany.entity.Admins;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminsDao extends GenericDao<Admins> {

    private final EntityManagerFactory factory;

    public AdminsDao(EntityManagerFactory factory) {
        super(Admins.class);
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

    public List<Admins> findByFirstName(String firstName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admins> q = cb.createQuery(Admins.class);

        Root<Admins> c = q.from(Admins.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("firstName"), paramName));
        TypedQuery<Admins> query = entityManager.createQuery(q);
        query.setParameter(paramName, firstName);

        return query.getResultList();
    }

    public List<Admins> findBySecondName (String secondName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admins> q = cb.createQuery(Admins.class);

        Root<Admins> c = q.from(Admins.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("secondName"), paramName));
        TypedQuery<Admins> query = entityManager.createQuery(q);
        query.setParameter(paramName, secondName);

        return query.getResultList();
    }

    public List<Admins> findByPhone (String phone) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admins> q = cb.createQuery(Admins.class);

        Root<Admins> c = q.from(Admins.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("phone"), paramName));
        TypedQuery<Admins> query = entityManager.createQuery(q);
        query.setParameter(paramName, phone);

        return query.getResultList();
    }
}
