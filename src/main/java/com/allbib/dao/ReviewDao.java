package com.allbib.dao;

import com.allbib.entity.Book;
import com.allbib.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReviewDao extends GenericDao<Review> {

    private final EntityManagerFactory factory;

    public ReviewDao(EntityManagerFactory factory) {
        super(Review.class);
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

    public List<Review> findByBook(Book inputBook) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> q = cb.createQuery(Review.class);

        Root<Review> c = q.from(Review.class);
        ParameterExpression<Integer> paramName = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("book").get("idBook"), paramName));
        TypedQuery<Review> query = entityManager.createQuery(q);
        query.setParameter(paramName, inputBook.getIdBook());

        return query.getResultList();
    }

}
