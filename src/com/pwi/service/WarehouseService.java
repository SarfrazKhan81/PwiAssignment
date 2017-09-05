/**<pre> 
 *==========================================================================
 *
 * Copyright:
 *
 *==========================================================================
 *
 *    FILE: WarehouseService.java
 *    CREATOR: Sarfraz Khan
 *    DEPT: GBS PAK
 *    DATE: 05/09/2017
 *
 *-PURPOSE-----------------------------------------------------------------
 * 
 *--------------------------------------------------------------------------
 *
 *
 *-CHANGE LOG--------------------------------------------------------------
 * 05/09/2017 Sarfraz Khan Initial coding.         
 *==========================================================================
 * </pre> */

package com.pwi.service;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;
import com.pwi.model.WarehouseVO;


/**
 * This class provides interface for Product Service.
 *  
 * @author Sarfraz Khan
 */
public interface WarehouseService {

	/**
	 * 
	 * @return
	 * @throws PwiException
	 * @author Sarfraz Khan
	 */
	List<Warehouse> getWarehouses() throws PwiException;
	
	Warehouse addWarehouse(Warehouse warehouse) throws PwiException;
	
	WarehouseVO getWarehouseById(Integer wid) throws PwiException;
	
	void delWarehouseById(Integer pid) throws PwiException;

	void updateWarehouse(WarehouseVO warehouseVO) throws PwiException;

	Warehouse getWarehousedbById(Integer wid) throws PwiException;
}
