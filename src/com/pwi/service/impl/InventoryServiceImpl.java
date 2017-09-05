package com.pwi.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwi.dao.InventoryDao;

import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;
import com.pwi.service.InventoryService;



/**
 * This class provides implementation of InventoryService.
 *  
 * @author Sarfraz khan
 */
@Service
public class InventoryServiceImpl implements InventoryService {
	private static Logger log = Logger.getLogger(InventoryServiceImpl.class);
	
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public List<Inventory> getInventories() throws PwiException {
		log.debug("list all Warehouse...");
			 List<Inventory> inventory = inventoryDao.listInventories();
		return inventory;
	}

	@Override
	public Inventory getInventoriesByPSW(int pid, int psid, int wid) throws PwiException {
		log.debug("list all Inventories of a Warehouse/Office...");
			 Inventory inventory = inventoryDao.getInventoryByPSW(pid, psid, wid);
		return inventory;
	}
	
	@Override
	public List<Inventory> getInventoriesByPS(int pid, int psid) throws PwiException {
		log.debug("list all Inventories...");
			 List<Inventory> inventories = inventoryDao.getInventoriesByPS(pid, psid);
		return inventories;
	}
	
	@Override
	public Inventory getInventoryById(Integer wid) throws PwiException {
		Inventory inventory = new Inventory();
		Inventory dbInventory =null;
		try {
			dbInventory = inventoryDao.getInventoryById(wid);
			
		}catch (Exception e) {
			throw new PwiException(e.getMessage());
		}
		if(dbInventory != null) {
		inventory = dbInventory;
			
		}
		 return inventory;
	}

		
	@Override
	public void delInventoryById(Integer wid) throws PwiException {
		Inventory inventory = null;
		try {
			inventory = inventoryDao.getInventoryById(wid);
		}catch(PwiException pe) {
			log.error("Exception: "+pe.getMessage(), pe);
			throw pe;
		}
		
		inventoryDao.delete(inventory);
	}

	@Override
	public Inventory addInventory(Inventory inventory) throws PwiException {
		Inventory dbInventory = null;
		log.debug("Add new Inventory for Product: "+inventory.getPid()+", ProductSizeID: "+inventory.getPsid()+", Warehouse: "+ inventory.getWid());
		try {
			dbInventory = inventoryDao.getInventoryByPSW(inventory.getPid(),inventory.getPsid(),inventory.getWid());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(dbInventory != null) {
			inventory.setIid(dbInventory.getIid());
			inventoryDao.update(inventory);
			//throw new PwiException("Inventory already exist.");
		}
		else
			{
			inventoryDao.add(inventory);
			}
		return inventory;
	}
	
	@Override
	public int setAllInventories(Inventory inventory) throws PwiException {
		int rowseffected = 0;
		List <Inventory> dbInventories = null;
		log.debug("Add new Inventory for Product: "+inventory.getPid()+", ProductSizeID: "+inventory.getPsid());
		try {
			dbInventories = inventoryDao.getInventoriesByPS(inventory.getPid(),inventory.getPsid());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(dbInventories != null && dbInventories.size()>0) {
			log.debug("updating inventories : "+dbInventories.size());
			for(Inventory dbInvent : dbInventories) {
				
				dbInvent.setAvlqty(inventory.getAvlqty());
				dbInvent.setInstock(inventory.getInstock());
				dbInvent.setIntransit(inventory.getIntransit());
				
				dbInvent.setMoq(inventory.getMoq());
				dbInvent.setQpb(inventory.getQpb());
				dbInvent.setRop(inventory.getRop());
				
				inventoryDao.update(dbInvent);
				rowseffected++;
				log.debug("updated inventory id: "+dbInvent.getIid());
			}
			
			//throw new PwiException("Inventory already exist.");
		}
		return rowseffected;
	}
	
	@Override
	public void updateInventory(Inventory inventory) throws PwiException {
		Inventory inventorydb = null;
		//ProductSize prodSize = null;
		log.debug("update product: "+inventory.getIid());
		try {
			inventorydb = inventoryDao.getInventoryById(inventory.getIid());
		}catch(PwiException pe) {
			log.debug("Exception: "+pe.getMessage());
		}
		if(inventorydb == null) {
			throw new PwiException("Inventory not exist.");
		}
		
		inventorydb.setInstock(inventory.getInstock());
		inventorydb.setIntransit(inventory.getIntransit());
		inventorydb.setAvlqty(inventory.getAvlqty());
		inventorydb.setMoq(inventory.getMoq());
		inventorydb.setQpb(inventory.getQpb());
		inventorydb.setRop(inventory.getRop());
		
		
		inventoryDao.update(inventorydb);

//		for(String size : productVO.getSizes()) {
//			prodSize = new ProductSize();
//			prodSize.setSize(size);
//			prodSize.setProduct(product);
//			productDao.add(prodSize);
//		}
	}

	
}
