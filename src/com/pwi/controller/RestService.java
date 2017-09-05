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
import com.pwi.model.ProductVO;
import com.pwi.service.ProductService;

@RestController
public class RestService {
	private static Logger log = Logger.getLogger(RestService.class);
	
	@Autowired
	ProductService productService;
	
	 @RequestMapping(value = "/prodServ/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<ProductVO> getProduct(@PathVariable("pid") Integer pid) {
		 log.debug("@RestController /prodServ ........");
		 HttpStatus status = HttpStatus.OK;
		 ProductVO Product = null;
		 try {
			 Product = productService.getProductById(pid);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("prodServ => "+status);
		 return new ResponseEntity<ProductVO> (Product, status);
	   }
	 
	 @RequestMapping(value = "/prodSizes/{pname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public  ResponseEntity<ProductVO> getProduct(@PathVariable("pname") String pname) {
		 log.debug("@RestController /prodSizes ........");
		 HttpStatus status = HttpStatus.OK;
		 ProductVO Product = null;
		 try {
			 Product = productService.getProductByName(pname);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("prodServ => "+status);
		 return new ResponseEntity<ProductVO> (Product, status);
	   }
	 @RequestMapping(value = "/addProdServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<Void> addProduct(@RequestBody ProductVO productVO,    UriComponentsBuilder ucBuilder) {
		 log.debug("@RestController /addProdServ ........");
		 HttpStatus status = HttpStatus.OK;
		 ProductVO Product = null;
		 try {
			 Product = productService.addProduct(productVO);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.EXPECTATION_FAILED;
		 }
		 log.debug("prodServ => "+status);
		 HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/prodServ/{pid}").buildAndExpand(productVO.getPid()).toUri());
		 return new ResponseEntity<Void> ( headers, status);
	   }
	 
	 @RequestMapping(value = "/editProdServ/", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE )
	  public  ResponseEntity<String> editProduct(@RequestBody ProductVO productVO) {
		 log.debug("@RestController /editProdServ ........");
		 HttpStatus status = HttpStatus.OK;
		 try {
			 productService.updateProduct(productVO);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage());
			 status = HttpStatus.NOT_FOUND;
		 }
		 log.debug("editProdServ => "+status);
		 
		 return new ResponseEntity<String> ( status.toString(), status);
	   }
	 
	 @RequestMapping(value = "/delProdServ/{pid}", method = RequestMethod.DELETE)
	  public  ResponseEntity<String> delUser(@PathVariable("pid") Integer pid) {
		 log.debug("@RestController /delProdServ ........");
		 HttpStatus status = HttpStatus.OK;
		 try {
			 productService.delProductById(pid);
		 } catch (PwiException pe) {
			 log.debug("Exception: "+pe.getMessage(), pe);
			 status = HttpStatus.NOT_MODIFIED;
		 }
		 
		 log.debug("delProdServ => "+status);
		 return new ResponseEntity<String> ( status.toString(), status);
	   }
}
