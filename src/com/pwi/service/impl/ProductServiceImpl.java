package com.pwi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwi.dao.InventoryDao;
import com.pwi.dao.ProductDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;
import com.pwi.model.Product;
import com.pwi.model.ProductSize;
import com.pwi.model.ProductVO;
import com.pwi.service.ProductService;


/**
 * This class provides implementation of ProductService.
 *  
 * @author Sarfraz Khan
 */
@Service
public class ProductServiceImpl implements ProductService {
	private static Logger log = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public List<ProductVO> getProducts() throws PwiException {
		log.debug("list all products...");
		 List<ProductVO> products = new ArrayList<>();
		 List<Product> dbProducts = productDao.listProducts();
		 for(Product prod : dbProducts) {
			 List<String> prodSizeString = new ArrayList<>();
			 ProductVO productVO = new ProductVO();
			 productVO.setBrandName(prod.getBName());
			 productVO.setProductName(prod.getProductName());
			 productVO.setProductType(prod.getPType());
			 for (ProductSize prodSize : prod.getProductSizes() ){
				 prodSizeString.add(prodSize.getSize());
			 }
			 productVO.setSizes(prodSizeString);
			 products.add(productVO);
		 }
		// log.debug(products);
		return products;
	}

	@Override
	public ProductVO getProductById(Integer pid) throws PwiException {
		Product product;
		try {
			product = productDao.getProductById(pid);
		}catch (Exception e) {
			throw new PwiException(e.getMessage());
		}
		 ProductVO productVO = new ProductVO();
		 List<String> prodSizeString = new ArrayList<>();
		 productVO.setPid(product.getPid());
		 productVO.setBrandName(product.getBName());
		 productVO.setProductName(product.getProductName());
		 productVO.setProductType(product.getPType());
		 log.debug("product sizes: "+product.getProductSizes().size());
		 for (ProductSize prodSize : product.getProductSizes() ){
			 prodSizeString.add(prodSize.getSize());
		 }
		 productVO.setSizes(prodSizeString);
		return productVO;
	}

	@Override
	public void delProductById(Integer pid) throws PwiException {
		Product product = null;
		try {
			product = productDao.getProductById(pid);
		}catch(PwiException pe) {
			log.error("Exception: "+pe.getMessage(), pe);
			throw pe;
		}
		
		for (ProductSize prodSize : product.getProductSizes() ){
			for (Inventory prodInventory : prodSize.getInventories() ){
//				Inventory dbInventory = inventoryDao.getInventoryById(prodInventory.getIid());
				productDao.delete(prodInventory);
			 }
			productDao.delete(prodSize);
		 }
		productDao.delete(product);
	}

	@Override
	public ProductVO addProduct(ProductVO productVO) throws PwiException {
		Product product = null;
		ProductSize prodSize = null;
		log.debug("Add new product: "+productVO.getProductName());
		try {
			product = productDao.getProductByName(productVO.getProductName());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(product != null) {
			throw new PwiException("Product already exist.");
		}
		product = new Product();
		product.setProductName(productVO.getProductName());
		product.setBName(productVO.getBrandName());
		product.setPType(productVO.getProductType());
		
		productDao.add(product);
		productVO.setPid(product.getPid());
		for(String size : productVO.getSizes()) {
			prodSize = new ProductSize();
			prodSize.setSize(size);
			prodSize.setProduct(product);
			productDao.add(prodSize);
		}
		return productVO;
	}
	
	@Override
	public void updateProduct(ProductVO productVO) throws PwiException {
		Product product = null;
		ProductSize prodSize = null;
		log.debug("update product: "+productVO.getPid());
		try {
			product = productDao.getProductById(productVO.getPid());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(product == null) {
			throw new PwiException("Product not exist.");
		}
		
		product.setProductName(productVO.getProductName());
		product.setBName(productVO.getBrandName());
		product.setPType(productVO.getProductType());
		
		productDao.update(product);

//		for(String size : productVO.getSizes()) {
//			prodSize = new ProductSize();
//			prodSize.setSize(size);
//			prodSize.setProduct(product);
//			productDao.add(prodSize);
//		}
	}

	@Override
	public ProductVO getProductByName(String pname) throws PwiException {
		Product product;
		try {
			product = productDao.getProductByName(pname);
		}catch (Exception e) {
			throw new PwiException(e.getMessage());
		}
		 ProductVO productVO = new ProductVO();
		 List<String> prodSizeString = new ArrayList<>();
		 productVO.setPid(product.getPid());
		 productVO.setBrandName(product.getBName());
		 productVO.setProductName(product.getProductName());
		 productVO.setProductType(product.getPType());
		 log.debug("product sizes: "+product.getProductSizes().size());
		 for (ProductSize prodSize : product.getProductSizes() ){
			 prodSizeString.add(prodSize.getSize());
		 }
		 productVO.setSizes(prodSizeString);
		return productVO;
	}

	
}
