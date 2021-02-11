package com.testcompany.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao <T> {
    private final Class<T> entityClass;

    //constructor
    public GenericDao(Class<T> eClass){
        this.entityClass = eClass;
    }

    public abstract EntityManager getEntityManager();

    public void create(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void remove(T entity, int entityId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove((T) em.find(this.entityClass, entityId));
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public T find(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(this.entityClass, id);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return (List<T>) em.createQuery(cq).getResultList();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }
}

