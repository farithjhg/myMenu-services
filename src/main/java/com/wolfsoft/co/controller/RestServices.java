package com.wolfsoft.co.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wolfsoft.co.domain.Notification;
import com.wolfsoft.co.domain.Recipe;
import com.wolfsoft.co.domain.RecipeWeek;
import com.wolfsoft.co.dto.Detail;
import com.wolfsoft.co.dto.Menu;
import com.wolfsoft.co.repository.NotificationRepository;
import com.wolfsoft.co.repository.RecipeRepository;
import com.wolfsoft.co.repository.RecipeWeekRepository;
import com.wolfsoft.co.util.CustomErrorType;

@RestController
@RequestMapping("/rest")
public class RestServices {
	private static final Logger log = LoggerFactory.getLogger(RestServices.class);
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeWeekRepository recipeWeekRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	/**
	 * Get All recipes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recipes/all")
	public List<Recipe> recipes() throws Exception{
		return (List<Recipe>) recipeRepository.findAll(sortByFieldAsc("nameRec"));
	}

	/**
	 * Sort Recipes by IdRec ASC
	 * @return SORT
	 */
    private Sort sortByFieldAsc(String fieldName) {
        return new Sort(Sort.Direction.ASC, fieldName);
    }

    /**
     * Get Recipes by Type
     * @param type
     * @return List<Recipe>
     * @throws Exception
     */
	@RequestMapping("/recipes/getByType/{type}")
	public List<Recipe> recipesByType(@PathVariable("type") Integer type) throws Exception{
		log.info("Getting Recipes by Type: {}", type);
		List<Recipe> recipesByTypeRec = recipeRepository.getRecipeByTypeRec(type, sortByFieldAsc("nameRec"));
		log.info("Got {} Recipes", recipesByTypeRec.size());
		return recipesByTypeRec;
				
	}

	/**
	 * Add a new Recipe
	 * @param recipe
	 * @return ResponseEntity<Recipe>
	 */
	@RequestMapping(value = "/recipes/add", method = RequestMethod.POST)
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		log.info("Creating Recipe : {}", recipe);
		if(recipe.getIdRec() != null && recipeRepository.exists(recipe.getIdRec())){
			log.error("Unable to create. A Recipe with name {} already exist", recipe.getNameRec());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Recipe with name " + 
            		recipe.getNameRec() + " already exist."), HttpStatus.CONFLICT);			
		}
		Recipe entity = recipeRepository.save(recipe);
		
		return new ResponseEntity<Recipe>(entity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/recipes/update", method = RequestMethod.PUT)
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        log.info("Updating Enity with id {}", recipe.getIdRec());
        
        Recipe entity = recipeRepository.findOne(recipe.getIdRec());
 
        if (entity == null) {
            log.error("Unable to update. Entity with id {} not found.", recipe.getIdRec());
            return new ResponseEntity(new CustomErrorType("Unable to upate. Entity with id " + recipe.getIdRec()+ " not found."),
                    HttpStatus.NOT_FOUND);
        }
    	
    	entity = recipeRepository.save(recipe);
    	
    	return new ResponseEntity<Recipe>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipes/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") Integer id) {
    	log.info("Fetching Entity with id {}", id);
    	 
    	Recipe entity = recipeRepository.findOne(id);
        if (entity == null) {
            log.error("Unable to Find. Entity with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to Find. Entity with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

    	return new ResponseEntity<Recipe>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipes/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Integer id) {
    	log.info("Fetching & Deleting Entity with id {}", id);
    	 
    	Recipe entity = recipeRepository.findOne(id);
        if (entity == null) {
            log.error("Unable to delete. Entity with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Entity with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
    	recipeRepository.delete(id);
    	
    	return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/notes/add", method = RequestMethod.POST)
    public ResponseEntity<Notification> addNotes(@RequestBody Notification note) {
		//log.info("Creating Recipe : {}", JSONObject.getString(note));
		if(note.getId() != null && recipeRepository.exists(note.getId())){
			log.error("Unable to create. A Note with Id {} already exist", note.getId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Note with Id " + 
            		note.getId() + " already exist."), HttpStatus.CONFLICT);			
		}
		Notification entity = notificationRepository.save(note);
		
		return new ResponseEntity<Notification>(entity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/notes/update", method = RequestMethod.PUT)
    public ResponseEntity<Notification> updateNotes(@RequestBody Notification note) {
        log.info("Updating Enity with id {}", note.getId());
        
        Notification entity = notificationRepository.findOne(note.getId());
 
        if (entity == null) {
            log.error("Unable to update. Entity with id {} not found.", note.getId());
            return new ResponseEntity(new CustomErrorType("Unable to upate. Entity with id " + note.getId()+ " not found."),
                    HttpStatus.NOT_FOUND);
        }
    	
    	entity = notificationRepository.save(note);
    	
    	return new ResponseEntity<Notification>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/notes/findAll", method = RequestMethod.GET)
    public List<Notification> getAllNotes() {
    	log.info("Fetching Entities !");
    	List<Notification> notes = (List<Notification>) notificationRepository.findAll();
    	log.info("Entities Fetched : "+notes.size());
    	return notes;
    }

    @RequestMapping(value = "/notes/findByRwId/{id}", method = RequestMethod.GET)
    public List<Notification> getNotesByDaylyMenu(@PathVariable("id") Integer id) {
    	log.info("Fetching Entities !");
    	List<Notification> notes = (List<Notification>) notificationRepository.getNotificationsByRwId(id);
    	log.info("Entities Fetched : "+notes.size());
    	return notes;
    }

    @RequestMapping(value = "/notes/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Notification> getNoteById(@PathVariable("id") Integer id) {
    	log.info("Fetching Entity with id {}", id);
    	 
    	Notification entity = notificationRepository.findOne(id);
        if (entity == null) {
            log.error("Unable to Find. Entity with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to Find. Entity with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

    	return new ResponseEntity<Notification>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/notes/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotification(@PathVariable("id") Integer id) {
    	log.info("Fetching & Deleting Entity with id {}", id);
    	 
    	Notification entity = notificationRepository.findOne(id);
        if (entity == null) {
            log.error("Unable to delete. Entity with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Entity with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        notificationRepository.delete(id);
    	
    	return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping("/weeklymenu/actual")
	public List<Menu> weeklymenu() throws Exception{
		log.info("weeklymenu::Init");
		List<Menu> menuList = new ArrayList<Menu>();
		List<Object[]> weeklyMenu = null;
		try {
			weeklyMenu = recipeWeekRepository.getWeeklyMenu();
			log.info("weeklymenu::got data from DB: "+weeklyMenu.size());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		if(weeklyMenu!=null && weeklyMenu.size() > 0){
			Date day = (Date)weeklyMenu.get(0)[0];
			String dayName = (String)weeklyMenu.get(0)[1];
			List<Detail> list = new ArrayList<Detail>();
			//Boolean (Integer)row[8]
			for (Object[] row : weeklyMenu) {
				
				if(!removeTime(day).equals(removeTime((Date)row[0]))){
					menuList.add(new Menu(day, dayName, list));
					day = (Date)row[0];					
					dayName = (String)row[1];
					list = new ArrayList<Detail>();
				}
				
				String value2 = (String)row[2];
				String value3 = (String)row[3];
				String value4 = (String)row[4];
				String value5 = (String)row[5];
				String value6 = row[6]+"";
				String value7 = row[7]+"";
				String value8 = row[8]+"";
				String value9 = row[9]+"";
				String value10 = row[10]+"";
				
				list.add(new Detail(value2, value3, value4, value5,
						new Integer(value6), new Integer(value7), new Integer(value8), 
						new Integer(value9), new Integer(value10)));
			}
			menuList.add(new Menu(day, dayName, list));
			log.info("weeklymenu::weeklyMenuList != null");
		}
		log.info("weeklymenu::End::"+menuList.size());
		return menuList;
	}
	
	@RequestMapping(value = "/weeklymenu/all", method = RequestMethod.GET)
    public List<RecipeWeek> getAllMeals() {
		return (List<RecipeWeek>)recipeWeekRepository.findAll(sortByIdAsc());
    }
    
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "rwId");
    }

	@RequestMapping(value = "/weeklymenu/add", method = RequestMethod.POST)
    public ResponseEntity<RecipeWeek> addMeal(@RequestBody RecipeWeek recipe) {
		//log.info("Creating Recipe : {}", new JSONObject(recipe));
		if(recipe.getRwId() != null && recipeWeekRepository.exists(recipe.getRwId())){
			log.error("Unable to create. A Dayly Recipe with Id {} already exist", recipe.getRwId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Dayly Recipe with id " + 
            		recipe.getRwId() + " already exist."), HttpStatus.CONFLICT);			
		}else if(recipe.getRecId() == null){
			log.error("Unable to Find. A Recipe as Null");
            return new ResponseEntity(new CustomErrorType("Unable to Find. A Recipe as Null"), HttpStatus.BAD_REQUEST);			
		}
		
		recipe.setRecDateCre(new Date());
		RecipeWeek entity = recipeWeekRepository.save(recipe);
		
		return new ResponseEntity<RecipeWeek>(entity, HttpStatus.OK);

    }

    @RequestMapping(value = "/weeklymenu/update", method = RequestMethod.PUT)
    public ResponseEntity<RecipeWeek> updateMeal(@RequestBody RecipeWeek recipe) {
        log.info("Updating Enity with id {}", recipe.getRwId());
        
        RecipeWeek entity = recipeWeekRepository.findOne(recipe.getRwId());
 
        if (entity == null) {
            log.error("Unable to update. Entity with id {} not found.", recipe.getRwId());
            return new ResponseEntity(new CustomErrorType("Unable to upate. Entity with id " + recipe.getRwId() + " not found."),
                    HttpStatus.NOT_FOUND);
        }
    	
        entity = recipeWeekRepository.save(recipe);
        log.info("HttpStatus.OK");
    	return new ResponseEntity<RecipeWeek>(entity, HttpStatus.OK);
    	
    }

    @RequestMapping(value = "/weeklymenu/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RecipeWeek> deleteMeal(@PathVariable("id") Integer id) {
    	log.info("Fetching & Deleting Entity with id {}", id);
   	 
    	RecipeWeek entity = recipeWeekRepository.findOne(id);
        if (entity == null) {
            log.error("Unable to delete. Entity with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Entity with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
    	recipeWeekRepository.delete(id);
    	
    	return new ResponseEntity<RecipeWeek>(HttpStatus.NO_CONTENT);
    }
	
	
	private Date removeTime(Date date) {    
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    cal.set(Calendar.HOUR_OF_DAY, 0);  
	    cal.set(Calendar.MINUTE, 0);  
	    cal.set(Calendar.SECOND, 0);  
	    cal.set(Calendar.MILLISECOND, 0);  
	    return cal.getTime(); 
	}	
    
}