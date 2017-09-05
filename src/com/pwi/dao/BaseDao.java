/**<pre> 
 *==========================================================================
 *
 * Copyright: 
 *
 *==========================================================================
 *
 *    FILE: BaseDao.java
 *    CREATOR: Sarfraz
 *    DEPT: GBS PAK
 *    DATE: 30/08/2017
 *
 *-PURPOSE-----------------------------------------------------------------
 * 
 *--------------------------------------------------------------------------
 *
 *
 *-CHANGE LOG--------------------------------------------------------------
 * 30/08/2017 Sarfraz Khan Initial coding.         
 *==========================================================================
 * </pre> */

package com.pwi.dao;

import com.pwi.exception.PwiException;


/**
 * This class provides interface for BpdUserDao.
 *  
 * @author Sarfraz Khan
 */
public interface BaseDao {

	
	void add(Object object) throws PwiException;
	
	void delete(Object object) throws PwiException;
	
	void update(Object object) throws PwiException;

}
