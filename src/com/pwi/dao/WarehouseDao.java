package com.pwi.dao;

import java.util.List;

import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;


/**
 * This class provides interface for WarehouseDao.
 *  
 * @author Sarfraz Khan
 */
public interface WarehouseDao extends BaseDao{

	Warehouse getWarehouseById(Integer wid) throws PwiException;
	
	Warehouse getWarehouseByName(String name) throws PwiException;
	
	List<Warehouse> listWarehouses() throws PwiException;
	
}
