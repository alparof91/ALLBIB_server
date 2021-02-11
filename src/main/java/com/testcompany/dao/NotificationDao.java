package com.testcompany.dao;

import com.testcompany.entity.Book;
import com.testcompany.entity.Notification;
import com.testcompany.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class NotificationDao extends GenericDao<Notification> {

    private final EntityManagerFactory factory;

    public NotificationDao(EntityManagerFactory factory) {
        super(Notification.class);
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

    public List<Notification> findByBook(Book inputBook) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Notification> q = cb.createQuery(Notification.class);

        Root<Notification> c = q.from(Notification.class);
        ParameterExpression<Integer> paramName = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("book").get("idBook"), paramName));
        TypedQuery<Notification> query = entityManager.createQuery(q);
        query.setParameter(paramName, inputBook.getIdBook());

        return query.getResultList();
    }

    public List<Notification> findByUsername(String username) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Notification> q = cb.createQuery(Notification.class);

        Root<Notification> c = q.from(Notification.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("username"), paramName));
        TypedQuery<Notification> query = entityManager.createQuery(q);
        query.setParameter(paramName, username);

        return query.getResultList();
    }
}
