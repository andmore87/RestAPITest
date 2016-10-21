package com.andmore.restapitest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.andmore.restapitest.model.Budget;
import com.andmore.restapitest.service.BudgetService;

@RestController
public class HomeController {
	
	
	@Autowired
    BudgetService budgetService;

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	  
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
	@PreAuthorize ("hasRole('ROLE_USER')")
    public ResponseEntity<List<Budget>> listAllUsers() {
    	List<Budget> users = new ArrayList<Budget>();
    	try {
    	  users = budgetService.getAllByTimeLine(new Date());
    	   List<Budget> usersTest = new ArrayList<Budget>();
           Budget bud = new Budget();
           bud.setAmount(2.3);
           bud.setStart_date(new Date());
           bud.setEnd_date(new Date());
           bud.setId(1);
           bud.setLimit(20L);
           usersTest.add(bud);
           usersTest.add(bud);
           if(users.isEmpty()){
               //return new ResponseEntity<List<Budget>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
               return new ResponseEntity<List<Budget>>(usersTest, HttpStatus.OK);
           }
           return new ResponseEntity<List<Budget>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Budget>>(users, HttpStatus.UNAUTHORIZED);
		}
    	
    	
     
    }
}
