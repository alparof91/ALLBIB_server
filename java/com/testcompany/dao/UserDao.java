package com.testcompany.dao;

import com.testcompany.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao extends GenericDao<User> {

    private final EntityManagerFactory factory;

    public UserDao(EntityManagerFactory factory) {
        super(User.class);
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

    //for login
    public List<User> find(String username) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("username"), paramName));
        TypedQuery<User> query = entityManager.createQuery(q);
        query.setParameter(paramName, username);

        return query.getResultList();
    }

}
