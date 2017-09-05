package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int bid;
	private String brandName;

	public Brand() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBid() {
		return this.bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	@Column(name="B_NAME")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}