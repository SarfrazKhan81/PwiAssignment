package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the warehouses database table.
 * 
 */
@Entity
@Table(name="warehouses")
@NamedQuery(name="Warehouse.findAll", query="SELECT w FROM Warehouse w")
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
	private int wid;
	private String locationName;
	private String warehouseName;
	private String warehouseType;
	private List<Inventory> inventories;

	public Warehouse() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getWid() {
		return this.wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}


	@Column(name="L_NAME")
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@Column(name="W_NAME")
	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	@Column(name="W_TYPE")
	public String getWarehouseType() {
		return this.warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="WID")
	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		inventory.setWid(this.wid);
		getInventories().add(inventory);
		
		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		//inventory.setWarehous(null);

		return inventory;
	}

	
	
	
}