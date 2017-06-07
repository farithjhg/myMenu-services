package com.wolfsoft.co.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the recipe_week database table.
 * 
 */
@Entity
@NamedQuery(name = "RecipeWeek.findAll", query = "select o from RecipeWeek o") 
@Table(name = "recipe_week")
public class RecipeWeek implements Serializable{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rw_id")
	private Integer rwId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="rec_date")
	private Date recDate;

	@Column(name="rec_with")
	private String recWith;

	@Temporal(TemporalType.DATE)
	@Column(name="rec_date_cre")
	private Date recDateCre;

	@Column(name="rec_id")
	private Integer recId;

	@ManyToOne(optional = false)
	@JoinColumn(name="rec_id", referencedColumnName ="id_rec", insertable=false, updatable=false )
	private Recipe recipe;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeWeek")
	private List<Notification> notificationList;

  	
  	public RecipeWeek() {
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

	public Date getRecDate() {  
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getRecWith() {  
		return recWith;
	}

	public void setRecWith(String recWith) {
		this.recWith = recWith;
	}

	public Date getRecDateCre() {  
		return recDateCre;
	}

	public void setRecDateCre(Date recDateCre) {
		this.recDateCre = recDateCre;
	}

	public List<Notification> getNotificationList() {  
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public Recipe getRecipe() {  
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (rwId != null ? rwId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeWeek)) {
            return false;
        }
        RecipeWeek other = (RecipeWeek) object;
        if ((this.rwId == null && other.rwId != null) || (this.rwId != null && !this.rwId.equals(other.rwId))) {
            return false;
        }
        return true;
    }
    
}