package com.pwi.dao;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.Product;


/**
 * This class provides interface for BpdUserDao.
 *  
 * @author Sarfraz Khan
 */
public interface ProductDao extends BaseDao{

	Product getProductById(Integer pid) throws PwiException;
	
	Product getProductByName(String name) throws PwiException;
	
	List<Product> listProducts() throws PwiException;
	
}
