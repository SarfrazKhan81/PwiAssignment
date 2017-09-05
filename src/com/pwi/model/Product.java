package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pid;
	private String bName;
	private String productName;
	private String pType;
	private List<ProductSize> productSizes;

	public Product() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}


	@Column(name="B_NAME")
	public String getBName() {
		return this.bName;
	}

	public void setBName(String bName) {
		this.bName = bName;
	}


	@Column(name="P_NAME")
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	@Column(name="P_TYPE")
	public String getPType() {
		return this.pType;
	}

	public void setPType(String pType) {
		this.pType = pType;
	}


	//bi-directional many-to-one association to ProductSize
	@OneToMany(mappedBy="product")
	public List<ProductSize> getProductSizes() {
		return this.productSizes;
	}

	public void setProductSizes(List<ProductSize> productSizes) {
		this.productSizes = productSizes;
	}

	public ProductSize addProductSize(ProductSize productSize) {
		getProductSizes().add(productSize);
		productSize.setProduct(this);

		return productSize;
	}

	public ProductSize removeProductSize(ProductSize productSize) {
		getProductSizes().remove(productSize);
		productSize.setProduct(null);

		return productSize;
	}

}