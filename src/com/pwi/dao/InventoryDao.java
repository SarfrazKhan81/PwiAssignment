/**<pre> 
 *==========================================================================
 *
 * Copyright:
 *
 *==========================================================================
 *
 *    FILE: InventoryDao.java
 *    CREATOR: Sarfraz Khan
 *    DEPT: GBS PAK
 *    DATE: 30/08/2017
 *
 *-PURPOSE-----------------------------------------------------------------
 * 
 *--------------------------------------------------------------------------
 *
 *
 *-CHANGE LOG--------------------------------------------------------------
 * 30/08/2017 Sarfraz KhanInitial coding.         
 *==========================================================================
 * </pre> */

package com.pwi.dao;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;


/**
 * This class provides interface for InventoryDao.
 *  
 * @author Sarfraz Khan
 */
public interface InventoryDao extends BaseDao{

	Inventory getInventoryById(Integer pid) throws PwiException;

	Inventory getInventoryByPSW(int pid, int psid, int wid);

	List<Inventory> listInventories();

	List<Inventory> getInventoriesByPS(int pid, int psid);
		
}
