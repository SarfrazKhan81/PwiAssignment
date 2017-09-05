package com.pwi.dao.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.pwi.dao.WarehouseDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;


/**
 * This class provides implementation for BpdUserDao.
 *  
 * @author Sarfraz Khan
 */
@Repository
public class WarehouseDaoImpl extends BaseDaoImpl implements WarehouseDao {
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Warehouse getWarehouseById(Integer wid) throws PwiException {
		Warehouse warehouse = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Warehouse w LEFT JOIN FETCH w.inventories where w.wid = :wid");
			//queryString.append("from Warehouse w where w.wid = :wid");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("wid", wid);
			warehouse = (Warehouse) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return warehouse;
	}

	@Override
	@Transactional
	public List<Warehouse> listWarehouses() throws PwiException {
		List<Warehouse> warehouses = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Warehouse ");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());			
			warehouses = (List<Warehouse>) query.list();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return warehouses;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public Warehouse getWarehouseByName(String name) throws PwiException {
		Warehouse warehouse = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Warehouse w WHERE w.warehouseName = :name");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("name", name);
			warehouse = (Warehouse) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return warehouse;
	}
	
}
