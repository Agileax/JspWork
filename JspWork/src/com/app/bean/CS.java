package com.app.bean;

import com.pokerwu.orm.annotation.Table;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
@Table("cs")
public class CS {
	private String id;
	private String clientName;
	private String clientOpinion;
	private String staff;
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
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return the clientOpinion
	 */
	public String getClientOpinion() {
		return clientOpinion;
	}
	/**
	 * @param clientOpinion the clientOpinion to set
	 */
	public void setClientOpinion(String clientOpinion) {
		this.clientOpinion = clientOpinion;
	}
	/**
	 * @return the staff
	 */
	public String getStaff() {
		return staff;
	}
	/**
	 * @param staff the staff to set
	 */
	public void setStaff(String staff) {
		this.staff = staff;
	}
	
}
