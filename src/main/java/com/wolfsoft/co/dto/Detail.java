package com.wolfsoft.co.dto;

import java.io.Serializable;

public class Detail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer rwId;
    private String type;
	private String menu;
	private String menuWith;
	private Integer idNot;
	private String notification;
	private Integer doneNote;
	private Integer recId;
	private Integer typeRec;
	
	public Detail() {
	}

	public Detail(String type, String menu, String menuWith, String notification, 
			Integer rwId, Integer recId, Integer typeRec, Integer idNot, Integer done) {
		super();
		this.rwId = rwId;
		this.type = type;
		this.menu = menu;
		this.menuWith = menuWith;
		this.recId = recId;
		this.typeRec = typeRec;
		this.idNot = idNot;
		this.doneNote = done;
		this.setNotification(notification);
	}	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuWith() {
		return menuWith;
	}

	public void setMenuWith(String menuWith) {
		this.menuWith = menuWith;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Integer getRwId() {
		return rwId;
	}

	public void setRwId(Integer rwId) {
		this.rwId = rwId;
	}

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public Integer getTypeRec() {
		return typeRec;
	}

	public void setTypeRec(Integer typeRec) {
		this.typeRec = typeRec;
	}

	public Integer getIdNot() {
		return idNot;
	}

	public void setIdNot(Integer idNot) {
		this.idNot = idNot;
	}

	public Integer getDoneNote() {
		return doneNote;
	}

	public void setDoneNote(Integer doneNote) {
		this.doneNote = doneNote;
	}
	
}