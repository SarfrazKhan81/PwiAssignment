package com.pwi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pwi.exception.PwiException;
import com.pwi.model.Inventory;
import com.pwi.service.InventoryService;

@RestController
public class InventroyController {
	private static Logger log = Logger.getLogger(InventroyController.class);
	
	@Autowired
	InventoryService inventoryService;
	 
	 @RequestMapping(value = "/getInventoryServ/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<Inventory> getInventory(@RequestBody Inventory inventory) {
		 log.debug("@RestController /getInventoryServ ........Pid="+inventory.getPid()+"Psid="+inventory.getPsid()+"Wid="+inventory.getWid());
		 HttpStatus status = HttpStatus.OK;
		 Inventory dbinventory = null;
		 try {
			 dbinventory =  inventoryService.getInventoriesByPSW(inventory.getPid(),inventory.getPsid(),inventory.getWid());
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("getInventories => "+status);
		 return new ResponseEntity<Inventory> (dbinventory, status);
	   }

	 @RequestMapping(value = "/getAllInventoryServ/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<List <Inventory>> getAllInventory(@RequestBody Inventory inventory) {
		 log.debug("@RestController /getAllInventoryServ ........Pid="+inventory.getPid()+"Psid="+inventory.getPsid());
		 HttpStatus status = HttpStatus.OK;
		 List <Inventory> inventories = null;
		 try {
			 inventories =  inventoryService.getInventoriesByPS(inventory.getPid(),inventory.getPsid());
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("getInventories => "+status);
		 return new ResponseEntity<List <Inventory>> (inventories, status);
	   }

	 
	 @RequestMapping(value = "/setInventoryServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<String> setInventory(@RequestBody Inventory inventory) {
		 log.debug("@RestController /setInventoryServ ........");
		 HttpStatus status = HttpStatus.OK;
		 Inventory inventory1 = null;
		 try {
			 inventory1 = inventoryService.addInventory(inventory);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.EXPECTATION_FAILED;
		 }
		 log.debug("inventoryServ => "+status);
		 return new ResponseEntity<String> ( status.toString(), status);
	   }
	

	 @RequestMapping(value = "/setAllInventoryServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<Void> setAllInventory(@RequestBody Inventory inventory) {
		 log.debug("@RestController /setAllInventoryServ ........");
		 HttpStatus status = HttpStatus.OK;
		 int rowsEffected = 0;
		 try {
			 rowsEffected = inventoryService.setAllInventories(inventory);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.EXPECTATION_FAILED;
		 }
		 log.debug("inventoryServ => "+status);
		 log.debug("inventory Rows Effected => "+rowsEffected);
		 
		 
		 HttpHeaders headers = new HttpHeaders();
	    // headers.setLocation(ucBuilder.path("/inventoryServ/{wid}").buildAndExpand(inventory.getIid()).toUri());
		 return new ResponseEntity<Void> ( headers, status);
	   }
	 
}
