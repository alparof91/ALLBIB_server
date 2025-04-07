package com.allbib.dao;

import com.allbib.entity.Book;
import com.allbib.entity.BookLog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookLogDao extends GenericDao<BookLog> {

    private final EntityManagerFactory factory;

    public BookLogDao(EntityManagerFactory factory) {
        super(BookLog.class);
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

    public List<BookLog> findByBook(Book inputBook) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookLog> q = cb.createQuery(BookLog.class);

        Root<BookLog> c = q.from(BookLog.class);
        ParameterExpression<Integer> paramName = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("book").get("idBook"), paramName));
        TypedQuery<BookLog> query = entityManager.createQuery(q);
        query.setParameter(paramName, inputBook.getIdBook());

        return query.getResultList();
    }

}
