package com.allbib.dao;

import com.allbib.entity.Readers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReaderDao extends GenericDao<Readers> {

    private final EntityManagerFactory factory;

    public ReaderDao(EntityManagerFactory factory) {
        super(Readers.class);
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

    public List<Readers> findByFirstName(String firstName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Readers> q = cb.createQuery(Readers.class);

        Root<Readers> c = q.from(Readers.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("firstName"), paramName));
        TypedQuery<Readers> query = entityManager.createQuery(q);
        query.setParameter(paramName, firstName);

        return query.getResultList();
    }

    public List<Readers> findBySecondName (String secondName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Readers> q = cb.createQuery(Readers.class);

        Root<Readers> c = q.from(Readers.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("secondName"), paramName));
        TypedQuery<Readers> query = entityManager.createQuery(q);
        query.setParameter(paramName, secondName);

        return query.getResultList();
    }

    public List<Readers> findByPhone (String phone) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Readers> q = cb.createQuery(Readers.class);

        Root<Readers> c = q.from(Readers.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("phone"), paramName));
        TypedQuery<Readers> query = entityManager.createQuery(q);
        query.setParameter(paramName, phone);

        return query.getResultList();
    }
}
