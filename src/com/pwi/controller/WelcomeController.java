package com.pwi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pwi.service.ProductService;

@Controller
public class WelcomeController {

	private static Logger log = Logger.getLogger(WelcomeController.class);

	@Autowired
	ProductService productService;
	 
	 @RequestMapping(value = {"/welcome.htm","/welcome.do"},  method = RequestMethod.GET)
	    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		 	log.debug("/welcome.htm ");
	        model.addAttribute("greeting", "Hi, "+name);
	        model.addAttribute("products", productService.getProducts());
	        return "welcome";
	    }
}
