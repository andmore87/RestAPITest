package com.andmore.restapitest.repository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.Transactional;

import com.andmore.restapitest.model.TransferObject;

 

/**
 * Abstract base repository class. All repositories implementations inherits from this class.
 * @author Andres. 
 */
@SuppressWarnings("unchecked")
@Transactional(readOnly = false)
@ImportResource("classpath:restAPITest.xml")
public abstract class GenericRepositoryImpl<T extends TransferObject>  implements GenericRepository<T> {
	
	@PersistenceContext(unitName="restAPITestPU")
	@Qualifier(value = "entityManagerFactory")
	
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(readOnly = true)
	public T findByPK(Class<T> typeClass, Serializable id) {
		return entityManager.find(typeClass, id);
	}

	@Transactional(readOnly = true)
	public List<T> getAll(Class<T> typeClass) {
		return getByQuery("select t from " + typeClass.getSimpleName() + " t");
	}
	
	public List<T> getAllByQuery(String query, Map<String, Object> params) {
		Query qQuery = createQuery(query, params);
		return qQuery.getResultList();
	}	
	
	private Query createQuery(String query, Map<String, Object> params) {
		Query qQuery = entityManager.createQuery(query);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				qQuery.setParameter(key, value);
			}
		}
		return qQuery;
	}
	
	@Transactional(readOnly = true)
	protected List<T> getByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public void saveOrUpdate(TransferObject entity) {
		if (entity.getId() != null){
			T attachedEntity = (T) entityManager.merge(entity);
			entityManager.persist(attachedEntity);				
		} else {
			entityManager.persist(entity);	
		}
		entityManager.flush();		
	}
	
	@Override
	public void delete(TransferObject entity) {
		T attachedEntity = this.findByPK((Class<T>) entity.getClass(), entity.getId());
		if (attachedEntity != null){
			entityManager.remove(attachedEntity);
		}
	}
}
