package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_types database table.
 * 
 */
@Entity
@Table(name="product_types")
@NamedQuery(name="ProductType.findAll", query="SELECT p FROM ProductType p")
public class ProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ptid;

	@Column(name="P_TYPE")
	private String pType;

	private byte sellable;

	public ProductType() {
	}

	public int getPtid() {
		return this.ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}

	public String getPType() {
		return this.pType;
	}

	public void setPType(String pType) {
		this.pType = pType;
	}

	public byte getSellable() {
		return this.sellable;
	}

	public void setSellable(byte sellable) {
		this.sellable = sellable;
	}

}