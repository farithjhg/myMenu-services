package com.wolfsoft.co.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the recipe database table.
 * 
 */
@Entity
@NamedQuery(name = "Recipe.findAll", query = "select o from Recipe o") 
@Table(name = "recipe")
public class Recipe implements Serializable{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="id_rec")
	private Integer idRec;
	
	@Column(name="name_rec")
	private String nameRec;

	@Column(name="url_rec")
	private String urlRec;

	@Column(name="type_rec")
	private int typeRec;

	private int weekend;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private List<RecipeWeek> recipeWeekList;

  	
  	public Recipe() {
  	}
  	
	public Integer getIdRec() {  
		return idRec;
	}

	public void setIdRec(Integer idRec) {
		this.idRec = idRec;
	}
	public String getNameRec() {  
		return nameRec;
	}

	public void setNameRec(String nameRec) {
		this.nameRec = nameRec;
	}

	public String getUrlRec() {  
		return urlRec;
	}

	public void setUrlRec(String urlRec) {
		this.urlRec = urlRec;
	}

	public int getTypeRec() {  
		return typeRec;
	}

	public void setTypeRec(int typeRec) {
		this.typeRec = typeRec;
	}

	public int getWeekend() {  
		return weekend;
	}

	public void setWeekend(int weekend) {
		this.weekend = weekend;
	}

	public List<RecipeWeek> getRecipeWeekList() {  
		return recipeWeekList;
	}

	public void setRecipeWeekList(List<RecipeWeek> recipeWeekList) {
		this.recipeWeekList = recipeWeekList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idRec != null ? idRec.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.idRec == null && other.idRec != null) || (this.idRec != null && !this.idRec.equals(other.idRec))) {
            return false;
        }
        return true;
    }
    
}