package com.testcompany.dao;

import com.testcompany.entity.Books;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class BooksDao extends GenericDao<Books> {

    private final EntityManagerFactory factory;

    public BooksDao(EntityManagerFactory factory) {
        super(Books.class);
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

    public List<Books> findByTitle(String title) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Books> q = cb.createQuery(Books.class);

        Root<Books> c = q.from(Books.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("title"), paramName));
        TypedQuery<Books> query = entityManager.createQuery(q);
        query.setParameter(paramName, title);

        return query.getResultList();
    }

    public List<Books> findByAuthor (String author) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Books> q = cb.createQuery(Books.class);

        Root<Books> c = q.from(Books.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("author"), paramName));
        TypedQuery<Books> query = entityManager.createQuery(q);
        query.setParameter(paramName, author);

        return query.getResultList();
    }

    public List<Books> findByPublisher (String publisher) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Books> q = cb.createQuery(Books.class);

        Root<Books> c = q.from(Books.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("publisher"), paramName));
        TypedQuery<Books> query = entityManager.createQuery(q);
        query.setParameter(paramName, publisher);

        return query.getResultList();
    }
}
