package com.pwi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;
import com.pwi.model.WarehouseVO;
import com.pwi.service.WarehouseService;

@RestController
public class WarehouseController {
	private static Logger log = Logger.getLogger(WarehouseController.class);
	
	@Autowired
	WarehouseService warehouseService;
	
	 @RequestMapping(value = "/warehouseServ/{wid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<WarehouseVO> getWarehouse(@PathVariable("wid") Integer wid) {
		 log.debug("@RestController /warehouseServ ........"+wid);
		 HttpStatus status = HttpStatus.OK;
		 WarehouseVO warehouse = null;
		 try {
			 warehouse =  warehouseService.getWarehouseById(wid);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("warehouseServ => "+status);
		 return new ResponseEntity<WarehouseVO> (warehouse, status);
	   }
	 @RequestMapping(value = "/warehouseServdb/{wid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<Warehouse> getWarehouseDB(@PathVariable("wid") Integer wid) {
		 log.debug("@RestController /warehouseServDB ........"+wid);
		 HttpStatus status = HttpStatus.OK;
		 Warehouse warehouse = null;
		 try {
			 warehouse =  warehouseService.getWarehousedbById(wid);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("warehouseServ => "+status);
		 return new ResponseEntity<Warehouse> (warehouse, status);
	   }
	 
	 @RequestMapping(value = "/addwarehouseServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<Void> addWarehousequestBody(@RequestBody Warehouse warehouse,    UriComponentsBuilder ucBuilder) {
		 log.debug("@RestController /addWarehouseServ ........");
		 HttpStatus status = HttpStatus.OK;
		 Warehouse warehouse1 = null;
		 try {
			 warehouse1 = warehouseService.addWarehouse(warehouse);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.EXPECTATION_FAILED;
		 }
		 log.debug("warehouseServ => "+status);
		 HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/warehouseServ/{wid}").buildAndExpand(warehouse.getWid()).toUri());
		 return new ResponseEntity<Void> ( headers, status);
	   }
	
	 @RequestMapping(value = "/editwarehouseServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<String> editwarehouse(@RequestBody WarehouseVO warehouseVO) {
		 log.debug("@RestController /editwarehouseServ ........");
		 HttpStatus status = HttpStatus.OK;
		 try {
			 warehouseService.updateWarehouse(warehouseVO);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("editwarehouseServ => "+status);
		 
		 return new ResponseEntity<String> ( status.toString(), status);
	   }
	 
	 
	 @RequestMapping(value = "/delWarehouseServ/{wid}", method = RequestMethod.DELETE)
	  public  ResponseEntity<String> delUser(@PathVariable("wid") Integer wid) {
		 log.debug("@RestController /delWarehouseServ ........");
		 HttpStatus status = HttpStatus.OK;
		 try {
			warehouseService.delWarehouseById(wid);
		 } catch(PwiException pe) {
			 log.debug("Exception: "+pe.getMessage(), pe);
			 status = HttpStatus.NOT_MODIFIED;
		 }
		 
		 log.debug("delWarehouseServ => "+status);
		 return new ResponseEntity<String> ( status.toString(), status);
	   }
}
