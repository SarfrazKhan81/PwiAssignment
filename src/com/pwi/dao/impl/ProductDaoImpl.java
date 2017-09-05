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

import com.pwi.dao.ProductDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Product;


/**
 * This class provides implementation for BpdUserDao.
 *  
 * @author Sarfraz Khan
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Product getProductById(Integer pid) throws PwiException {
		Product product = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Product p LEFT JOIN FETCH p.productSizes ps LEFT JOIN FETCH ps.inventories where p.pid = :pid");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("pid", pid);
			product = (Product) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return product;
	}

	@Override
	@Transactional
	public List<Product> listProducts() throws PwiException {
		List<Product> products = null;
		Session  session  = null;
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Product ");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());			
			products = (List<Product>) query.list();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return products;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public Product getProductByName(String name) throws PwiException {
		Product product = null;
		Session  session  = null;
		try {
						
			StringBuilder queryString = new StringBuilder();
			queryString.append("from Product p LEFT JOIN FETCH p.productSizes ps LEFT JOIN FETCH ps.inventories where p.productName =  :name");
			session  = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery(queryString.toString());
			query.setParameter("name", name);
			product = (Product) query.uniqueResult();
		}catch(HibernateException he){
			throw new PwiException( he.getMessage(), he);
		}catch(Exception e){
			throw new PwiException(e.getMessage(), e);
		}
		return product;
	}
	
}
