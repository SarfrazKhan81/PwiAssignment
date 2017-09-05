/**<pre> 
 *==========================================================================
 *
 * Copyright: 
 *
 *==========================================================================
 *
 *    FILE: BpdUserDao.java
 *    CREATOR: Sarfraz Khan
 *    DEPT: GBS PAK
 *    DATE: 19/07/2013
 *
 *-PURPOSE-----------------------------------------------------------------
 * 
 *--------------------------------------------------------------------------
 *
 *
 *-CHANGE LOG--------------------------------------------------------------
 * 19/07/2013 Sarfraz Khan Initial coding.         
 *==========================================================================
 * </pre> */

package com.pwi.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pwi.controller.RestService;
import com.pwi.dao.InventoryDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;


/**
 * This class provides implementation for BpdUserDao.
 *  
 * @author Sarfraz Khan
 */
@Repository
public class InventoryDaoImpl extends BaseDaoImpl implements InventoryDao {
	
	private static Logger log = Logger.getLogger(RestService.class);
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Inventory getInventoryById(Integer iid) throws PwiException {
		Inventory Inventory = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Inventory i where i.iid = :iid");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("iid", iid);
			Inventory = (Inventory) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return Inventory;
	}

	@Override
	@Transactional
	public Inventory getInventoryByPSW(int pid, int psid, int wid) {
		log.debug ("Pid="+pid);
		Inventory Inventory = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Inventory i where i.pid = :pid and i.psid = :psid and i.wid = :wid");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("pid", pid);
			query.setParameter("psid", psid);
			query.setParameter("wid", wid);
			
			Inventory = (Inventory) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return Inventory;
	}
	
	@Override
	@Transactional
	public List<Inventory> getInventoriesByPS(int pid, int psid) {
		log.debug ("Pid="+pid);
		List<Inventory> inventories = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Inventory i where i.pid = :pid and i.psid = :psid");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("pid", pid);
			query.setParameter("psid", psid);
			
			
			inventories =  (List <Inventory>) query.list();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return inventories;
	}

	@Override
	@Transactional
	public List<Inventory> listInventories() {
		// TODO Auto-generated method stub
		return null;
	}
	
		
}
