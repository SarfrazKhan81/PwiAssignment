package com.pwi.test;

import org.testng.annotations.Test;

import com.pwi.dao.InventoryDao;
import com.pwi.dao.WarehouseDao;
import com.pwi.exception.PwiException;
import com.pwi.model.Warehouse;
import com.pwi.model.WarehouseVO;
import com.pwi.service.impl.WarehouseServiceImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeTest;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {com.pwi.config.AppConfig.class})
public class WarehouseServiceImplTest {
	
	@Mock
	WarehouseDao warehouseDao;
	@Mock
	InventoryDao inventoryDao;
	@Spy
	WarehouseVO warehouseVO = new WarehouseVO();
	@Spy
	Warehouse warehouse = new Warehouse();
	
	@InjectMocks
	WarehouseServiceImpl warehouseServiceImpl;
	
  @BeforeTest
  public void beforeTest() {
	  MockitoAnnotations.initMocks(this);
	  warehouseVO.setWid(7);
	  warehouse.setWarehouseName("Legendry");
  }


  @Test
  public void addWarehouse() {
	  Warehouse added = warehouseServiceImpl.addWarehouse(warehouse);
	  assertNotNull(added);
  }

  @Test
  public void delWarehouseById() {
	  warehouseServiceImpl.delWarehouseById(7);
  }

  @Test
  public void getWarehouseById() {
	  warehouseVO = warehouseServiceImpl.getWarehouseById(7);
	  assertNotNull(warehouseVO);
  }

  @Test
  public void updateWarehouse() {
	  try {
		  warehouseServiceImpl.updateWarehouse(warehouseVO);
	  }catch(PwiException p) {
		  if(!"warehouse not exist.".equalsIgnoreCase(p.getMessage())) {
			  fail("Edit test failed.");
		  }
	  }
  }
}
