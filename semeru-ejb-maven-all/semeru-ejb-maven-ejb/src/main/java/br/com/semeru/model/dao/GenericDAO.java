package br.com.semeru.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.criterion.DetachedCriteria;

public class GenericDAO<T> implements InterfaceDAO<T>, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager entityManager;
    
    private Class<T> classe;

    public GenericDAO(Class<T> classe) {
        super();
        this.classe = classe;
    }

    @Override
    public void save(T entity) {
    	entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
    	entityManager.merge(entity);
    }

    @Override
    public void remove(T entity) {
    	entityManager.remove(entity);
    }

    @Override
    public void merge(T entity) {
    	entityManager.merge(entity);
    }

    @Override
    public T getEntity(Serializable id) {
        return entityManager.find(classe, id);
    }

//    @Override
//    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
//        T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
//        return entity;
//    }

    @Override
    public T getEntityByHQLQuery(String stringQuery) {
//        Query query = session.createQuery(stringQuery);
//        return (T) query.uniqueResult();
    	return null;
    }

//    @Override
//    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
//        return criteria.getExecutableCriteria(session).list();
//    }

    @Override
    public List<T> getEntities() {
//        List<T> enties = (List<T>) session.createCriteria(classe).list();
//        return enties;
    	return null;
    }

    @Override
    public List<T> getListByHQLQuery(String stringQuery) {
        Query query = entityManager.createNamedQuery(stringQuery);
        return query.getResultList();
//        CHECK-ME
    }
}