package com.pwi.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwi.dao.InventoryDao;
import com.pwi.dao.WarehouseDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;
import com.pwi.model.WarehouseVO;
import com.pwi.service.WarehouseService;


/**
 * This class provides implementation of WarehouseService.
 *  
 * @author Sarfraz khan
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
	private static Logger log = Logger.getLogger(WarehouseServiceImpl.class);
	
	@Autowired
	WarehouseDao warehouseDao;
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public List<Warehouse> getWarehouses() throws PwiException {
		log.debug("list all Warehouse...");
			 List<Warehouse> warehouse = warehouseDao.listWarehouses();
		return warehouse;
	}

	@Override
	public WarehouseVO getWarehouseById(Integer wid) throws PwiException {
		WarehouseVO warehouseVO = new WarehouseVO();
		Warehouse dbWarehouse =null;
		try {
			dbWarehouse = warehouseDao.getWarehouseById(wid);
		}catch (Exception e) {
			throw new PwiException(e.getMessage());
		}
		if(dbWarehouse != null) {
			warehouseVO.setWid(dbWarehouse.getWid());
			warehouseVO.setLocationName(dbWarehouse.getLocationName());
			warehouseVO.setWarehouseType(dbWarehouse.getWarehouseType());
			warehouseVO.setWarehouseName(dbWarehouse.getWarehouseName());
			
		 
			
		}
		 return warehouseVO;
	}

	@Override
	public Warehouse getWarehousedbById(Integer wid) throws PwiException {
		//WarehouseVO warehouseVO = new WarehouseVO();
		Warehouse dbWarehouse =null;
		try {
			dbWarehouse = warehouseDao.getWarehouseById(wid);
		}catch (Exception e) {
			throw new PwiException(e.getMessage());
		}
		
		 return dbWarehouse;
	}

	
	@Override
	public void delWarehouseById(Integer wid) throws PwiException {
		Warehouse warehouse = null;
		try {
			warehouse = warehouseDao.getWarehouseById(wid);
		}catch(PwiException pe) {
			log.error("Exception: "+pe.getMessage(), pe);
			throw pe;
		}
		
		warehouseDao.delete(warehouse);
	}

	@Override
	public Warehouse addWarehouse(Warehouse warehouse) throws PwiException {
		Warehouse warehouse1 = null;
		log.debug("Add new product: "+warehouse.getWarehouseName());
		try {
			warehouse1 = warehouseDao.getWarehouseByName(warehouse.getWarehouseName());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(warehouse1 != null) {
			throw new PwiException("Warehouse already exist.");
		}
		warehouseDao.add(warehouse);
		return warehouse;
	}
	@Override
	public void updateWarehouse(WarehouseVO warehouseVO) throws PwiException {
		Warehouse warehouse = null;
		//ProductSize prodSize = null;
		log.debug("update product: "+warehouseVO.getWid());
		try {
			warehouse = warehouseDao.getWarehouseById(warehouseVO.getWid());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(warehouse == null) {
			throw new PwiException("warehouse not exist.");
		}
		
		warehouse.setWarehouseName(warehouseVO.getWarehouseName());
		warehouse.setLocationName(warehouseVO.getLocationName());
		warehouse.setWarehouseType(warehouseVO.getWarehouseType());
		
		warehouseDao.update(warehouse);

//		for(String size : productVO.getSizes()) {
//			prodSize = new ProductSize();
//			prodSize.setSize(size);
//			prodSize.setProduct(product);
//			productDao.add(prodSize);
//		}
	}

	
}
