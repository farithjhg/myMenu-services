package com.wolfsoft.co.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date recDate;
	private String dayName;
	private List<Detail> details;

	public Menu() {
	}

	public Menu(Date recDate, String dayName, List<Detail> list) {
		super();
		this.recDate = recDate;
		this.dayName = dayName;
		this.details = list;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

}