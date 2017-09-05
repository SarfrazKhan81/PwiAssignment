package com.pwi.test;

import org.testng.annotations.Test;

import com.pwi.controller.InventroyController;
import com.pwi.model.Inventory;
import com.pwi.service.InventoryService;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.BeforeTest;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {com.pwi.config.AppConfig.class})
public class InventroyControllerTest {

	@Mock
	InventoryService inventoryService;
	@Mock
	UriComponentsBuilder ucBuilder;
	
	@Spy
	Inventory inventory =  new Inventory();
	@Spy
	List <Inventory> items = new ArrayList<Inventory>();
	
	@InjectMocks
	InventroyController inventroyController;
	
  @BeforeTest
  public void beforeTest() {
	  MockitoAnnotations.initMocks(this);
	  inventory.setIid(12);
  }


  @Test
  public void setAllInventory() {
	  inventroyController.setAllInventory(inventory);
  }

  @Test
  public void setInventory() {
	  inventroyController.setInventory(inventory);
  }


  @Test
  public void getAllInventory() {
	  ResponseEntity<List <Inventory>> result = inventroyController.getAllInventory(inventory);
	  assertNotNull(result);
  }

  @Test
  public void getInventory() {
	  ResponseEntity<Inventory> result = inventroyController.getInventory(inventory);
	  assertNotNull(result);
  }
}
