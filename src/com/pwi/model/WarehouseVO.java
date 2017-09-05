package com.pwi.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the warehouses database table.
 * 
 */
public class WarehouseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int wid;
	private String locationName;
	private String warehouseName;
	private String warehouseType;
	private List<Inventory> inventories;

	public WarehouseVO() {
	}


	public int getWid() {
		return this.wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getWarehouseType() {
		return this.warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		
		return inventory;
	}

}