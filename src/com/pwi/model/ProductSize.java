package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the product_sizes database table.
 * 
 */
@Entity
@Table(name="product_sizes")
@NamedQuery(name="ProductSize.findAll", query="SELECT p FROM ProductSize p")
public class ProductSize implements Serializable {
	private static final long serialVersionUID = 1L;
	private int psid;
	private String size;
	private Product product;
	private Set<Inventory> inventories;

	public ProductSize() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getPsid() {
		return this.psid;
	}

	public void setPsid(int psid) {
		this.psid = psid;
	}


	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}


	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PID")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	//bi-directional many-to-one association to Inventory
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="PID")
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setPsid(this.psid);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);

		return inventory;
	}

}