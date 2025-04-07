package com.allbib.dao;

import com.allbib.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDao extends GenericDao<Book> {

    private final EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory) {
        super(Book.class);
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

    public List<Book> findByTitle(String title) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> q = cb.createQuery(Book.class);

        Root<Book> c = q.from(Book.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("title"), paramName));
        TypedQuery<Book> query = entityManager.createQuery(q);
        query.setParameter(paramName, title);

        return query.getResultList();
    }

    public List<Book> findByAuthor (String author) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> q = cb.createQuery(Book.class);

        Root<Book> c = q.from(Book.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("author"), paramName));
        TypedQuery<Book> query = entityManager.createQuery(q);
        query.setParameter(paramName, author);

        return query.getResultList();
    }

    public List<Book> findByPublisher (String publisher) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> q = cb.createQuery(Book.class);

        Root<Book> c = q.from(Book.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("publisher"), paramName));
        TypedQuery<Book> query = entityManager.createQuery(q);
        query.setParameter(paramName, publisher);

        return query.getResultList();
    }
}
