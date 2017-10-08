package com.dingy.dingyserver.hibernate_repositories.db.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import com.dingy.dingyserver.hibernate_repositories.db.HibernateUtil;
import com.dingy.dingyserver.hibernate_repositories.db.page.Page;
import com.dingy.dingyserver.hibernate_repositories.db.page.PageImpl;
import com.dingy.dingyserver.hibernate_repositories.db.page.Pageable;
import com.dingy.dingyserver.hibernate_repositories.db.sort.Sort;
import com.dingy.dingyserver.hibernate_repositories.db.specification.Specifiable;


/**
 * @see org.springframework.data.repository.CrudRepository
 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.domain.Sort.Direction.PageRequest
 */
public abstract class AbstractRepository<T, ID extends Serializable> {
	

	public abstract Class<T> getDomainClass();
	

	public Session openSession() {		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		return session;
	}
	
	
	public void delete(ID id) throws RuntimeException {
	
		T entity = findOne(id);
		
		if(entity == null)
			throw new RuntimeException("invalid ID");

		delete(entity);
	}
	
	
	public void delete(T entity) {
		
		if(entity == null)
			throw new RuntimeException("received null entity");
		
		Session session = openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (RuntimeException e) {
			System.out.println(e);
			tx.rollback();
		} finally {
			session.close();
		}

	}


	
	public void delete(Iterable<? extends T> entities) {
		
		if(entities == null)
			throw new RuntimeException("received null entities");

		Session session = openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			entities.forEach(entity -> session.delete(entity));
			tx.commit();
		} catch (RuntimeException e) {
			System.out.println(e);
			try {
				tx.rollback();
			} catch (HibernateException he) {
				System.out.println(he);
			}
		} finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public T findOne(ID id) {
		
		Class<T> classType = getDomainClass();
		T entity = null;
		
		Session session = openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			entity = (T) session.get(classType, id);
			tx.commit();
		} catch (RuntimeException e) {
			System.out.println(e);
			tx.rollback();
		} finally {
			session.close();
		}		
		
		return entity;
	}

	
	public boolean exists(ID id) {

		return findOne(id) != null;
	}
	
	
	@SuppressWarnings("unchecked")
	public T findOne(Specifiable spec) {
		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);		

		criteria = buildCriteria(criteria, spec, null, null);
		
		T entity;
		
		try {
			entity = (T) criteria.uniqueResult();
			return entity;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		
		List<T> entities;
		
		try {
			entities = criteria.list();
			return entities;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Sort sort) {	
		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		criteria = buildCriteria(criteria, null, null, sort);
		
		List<T> entities;
		
		try {
			entities = criteria.list();
			return entities;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Specifiable spec) {		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		criteria = buildCriteria(criteria, spec, null, null);
		
		List<T> entities;
		
		try {
			entities = criteria.list();
			return entities;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Specifiable spec, Sort sort) {
		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		criteria = buildCriteria(criteria, spec, null, sort);
		
		List<T> entities;
		
		try {
			entities = criteria.list();
			return entities;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	public Page<T> findAll(Pageable pageable) {
		
		return findAll(null, pageable);
	}
	
	
	@SuppressWarnings("unchecked")
	public Page<T> findAll(Specifiable spec, Pageable pageable) {
		
		if(pageable == null)
			throw new RuntimeException("received null pageable");
		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		
		criteria = buildCriteria(criteria, spec, pageable, null);
		
		try {
			long total = count(spec);
			List<T> content = criteria.list();
			
			Page<T> page = new PageImpl<T>(content, pageable, total);			
			return page;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	public long count() {
		
		return count(null);
	}
	
	
	public long count(Specifiable spec) {
		
		Session session = openSession();
		
		Class<T> classType = getDomainClass();		
		Criteria criteria = session.createCriteria(classType);
		criteria = buildCriteria(criteria, spec, null, null);
		criteria.setProjection(Projections.rowCount());
		
		Long rowCount;
		
		try {
			rowCount = (Long) criteria.uniqueResult();
			return rowCount;
		} catch (HibernateException he) {
			System.out.println(he);
		} finally {
			session.close();
		}
		
		return 0;
	}

	
	
	public <S extends T> S save(S entity) {
		
		if(entity == null)
			throw new RuntimeException("received null entity");
		
		Session session = openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			return entity;
		} catch (RuntimeException e) {
			System.out.println(e);
			tx.rollback();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	@Transactional
	public <S extends T> List<S> save(Iterable<S> entities) {
		
		if(entities == null)
			throw new RuntimeException("received null entities");
		
		List<S> result = new ArrayList<S>();

		Session session = openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();					
			entities.forEach(entity -> {
				session.saveOrUpdate(entity); 
				result.add(entity);
			});			
			tx.commit();
			return result;
		} catch (RuntimeException e) {
			System.out.println(e);		
			try {
				tx.rollback();
			} catch (HibernateException he) {
				System.out.println(he);
			}
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
	private Criteria buildCriteria(Criteria criteria, Specifiable spec, Pageable page, Sort sort) {
		
		if (spec != null) {
			spec.toCriteria(criteria);
		}
		
		if (page != null) {
			page.toCriteria(criteria);
		}
		
		if (sort != null) {
			sort.toCriteria(criteria);
		}
		
		return criteria;	
	}
	
}
