package com.app.bean;

import com.pokerwu.orm.annotation.Table;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
@Table("staff")
public class Staff {
	private String id;
	private String name;
	private String sex;
	private int age;
	private String education;
	private String department;
	private long date;
	private String duty;
	private double wage;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * @return the wage
	 */
	public double getWage() {
		return wage;
	}
	/**
	 * @param wage the wage to set
	 */
	public void setWage(double wage) {
		this.wage = wage;
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
	
}
