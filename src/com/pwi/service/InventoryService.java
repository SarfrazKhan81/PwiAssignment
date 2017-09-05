package com.pwi.service;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;



/**
 * This class provides interface for InventoryService.
 *  
 * @author Sarfraz khan
 */
public interface InventoryService {

	/**
	 * 
	 * @return
	 * @throws PwiException
	 * @author Muhammad Attique
	 */
	List<Inventory> getInventories() throws PwiException;
	
	Inventory addInventory(Inventory inventory) throws PwiException;
	
	Inventory getInventoryById(Integer wid) throws PwiException;
	
	void delInventoryById(Integer pid) throws PwiException;

	void updateInventory(Inventory inventory) throws PwiException;

	int setAllInventories(Inventory inventory) throws PwiException;

	Inventory getInventoriesByPSW(int pid, int psid, int wid) throws PwiException;

	List<Inventory> getInventoriesByPS(int pid, int psid) throws PwiException;

}
