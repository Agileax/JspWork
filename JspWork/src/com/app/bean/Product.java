package com.app.bean;

import com.pokerwu.orm.annotation.Table;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
@Table("product")
public class Product {
	private String id;
	private String name;
	private String modelNum;
	private String number;
	private String price;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the model
	 */
	public String getModelNum() {
		return modelNum;
	}
	/**
	 * @param model the model to set
	 */
	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
}
