package com.wolfsoft.co.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQuery(name = "Notification.findAll", query = "select o from Notification o") 
@Table(name = "notification")
public class Notification implements Serializable{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="_id")
	private Integer id;
	
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="date_cre")
	private Date dateCre;

	@Column(name="rw_id")
	private Integer rwId;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name="rw_id", referencedColumnName ="rw_id", insertable=false, updatable=false )
	private RecipeWeek recipeWeek;
  	
	private int done;
	
  	public Notification() {
  	}
  	
	public Integer getId() {  
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {  
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCre() {  
		return dateCre;
	}

	public void setDateCre(Date dateCre) {
		this.dateCre = dateCre;
	}

	public Integer getRwId() {
		return rwId;
	}

	public void setRwId(Integer rwId) {
		this.rwId = rwId;
	}

	public RecipeWeek getRecipeWeek() {  
		return recipeWeek;
	}

	public void setRecipeWeek(RecipeWeek recipeWeek) {
		this.recipeWeek = recipeWeek;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}