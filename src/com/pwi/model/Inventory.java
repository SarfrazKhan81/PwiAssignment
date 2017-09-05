package com.pwi.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private int iid;
	private int avlqty;
	private int instock;
	private int intransit;
	private int moq;
	private int pid;
	private int qpb;
	private int rop;
	private int wid;
	private int psid;

	public Inventory() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIid() {
		return this.iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}


	public int getAvlqty() {
		return this.avlqty;
	}

	public void setAvlqty(int avlqty) {
		this.avlqty = avlqty;
	}


	public int getInstock() {
		return this.instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}


	public int getIntransit() {
		return this.intransit;
	}

	public void setIntransit(int intransit) {
		this.intransit = intransit;
	}


	public int getMoq() {
		return this.moq;
	}

	public void setMoq(int moq) {
		this.moq = moq;
	}


	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}


	public int getQpb() {
		return this.qpb;
	}

	public void setQpb(int qpb) {
		this.qpb = qpb;
	}


	public int getRop() {
		return this.rop;
	}

	public void setRop(int rop) {
		this.rop = rop;
	}

@Column(name="WID")
	public int getWid() {
		return wid;
	}


	public void setWid(int wid) {
		this.wid = wid;
	}


	public int getPsid() {
		return psid;
	}


	public void setPsid(int psid) {
		this.psid = psid;
	}


}