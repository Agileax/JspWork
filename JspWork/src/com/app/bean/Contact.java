package com.app.bean;

import com.pokerwu.orm.annotation.Table;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
@Table("contact")
public class Contact {
	private String id;
	private String name;
	private String contents;
	private String start;
	private String end;
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
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
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
