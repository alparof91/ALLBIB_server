package com.testcompany.dao;

import com.testcompany.entity.Book;
import com.testcompany.entity.GivenBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class GivenBookDao extends GenericDao<GivenBook> {

    private final EntityManagerFactory factory;

    public GivenBookDao(EntityManagerFactory factory) {
        super(GivenBook.class);
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

    public List<GivenBook> findByBook(Book inputBook) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GivenBook> q = cb.createQuery(GivenBook.class);

        Root<GivenBook> c = q.from(GivenBook.class);
        ParameterExpression<Integer> paramName = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("book").get("idBook"), paramName));
        TypedQuery<GivenBook> query = entityManager.createQuery(q);
        query.setParameter(paramName, inputBook.getIdBook());

        return query.getResultList();
    }

}
