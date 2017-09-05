package com.pwi.dao.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pwi.dao.BaseDao;
import com.pwi.exception.PwiException;


/**
 * This class provides implementation for ProductSizeDao.
 *  
 * @author Sarfraz Khan
 */
public class BaseDaoImpl implements BaseDao {
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void delete(Object object) throws PwiException {
		Session  session  = null;
		try {
			session  = sessionFactory.getCurrentSession();	
			session.delete(object);
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void update(Object object) throws PwiException {
		Session  session  = null;
		try {
			session  = sessionFactory.getCurrentSession();	
			session.merge(object);
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		
	}
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public void add(Object object) throws PwiException {
		Session  session  = null;
		try {
			session  = sessionFactory.getCurrentSession();	
			session.persist(object);
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		
	}

}
