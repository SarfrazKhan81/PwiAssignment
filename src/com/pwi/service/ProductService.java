package com.pwi.service;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.ProductVO;


/**
 * This class provides interface for Product Service.
 *  
 * @author Sarfraz Khan
 */
public interface ProductService {

	/**
	 * 
	 * @return
	 * @throws PwiException
	 * @author Muhammad Attique
	 */
	List<ProductVO> getProducts() throws PwiException;
	
	ProductVO addProduct(ProductVO productVO) throws PwiException;
	
	ProductVO getProductById(Integer pid) throws PwiException;
	
	ProductVO getProductByName(String pname) throws PwiException;
	
	void delProductById(Integer pid) throws PwiException;

	void updateProduct(ProductVO productVO) throws PwiException;
}
