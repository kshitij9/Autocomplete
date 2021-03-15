package com.security.search.controller;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.security.search.exception.ResourceNotFoundException;
import com.security.search.model.Security;
import com.security.search.repository.SecurityRepository;

import javax.persistence.TypedQuery;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
@RestController
@RequestMapping("/api/v1")
public class SecurityController {
	   private static SessionFactory factory; 

	Session session;
	  @Autowired
	  private SecurityRepository securityRepository;

	  /**
	   * Get all users list.
	   *
	   * @return the list
	   */
	  @RequestMapping(value = "/securities", method = RequestMethod.GET, produces = "application/json")
	  @ResponseBody	  
	  public List<String> searchSecurities(@RequestParam("value") String value) {
	      List<Security> securityData = securityRepository.findAll();
	      List<String> results = new ArrayList<String>();
			for (Security sec : securityData) {
				if (containsIgnoreCase(sec.getSecurityName(), value) || containsIgnoreCase(sec.getIsin(), value) ) {
					results.add(sec.getSecurityName());
				}
			} 
	    return results;
	  }
	  
	  public static boolean containsIgnoreCase(String src, String what) {
		    final int length = what.length();
		    if (length == 0)
		        return true; // Empty string is contained

		    final char firstLo = Character.toLowerCase(what.charAt(0));
		    final char firstUp = Character.toUpperCase(what.charAt(0));

		    for (int i = src.length() - length; i >= 0; i--) {
		        // Quick check before calling the more expensive regionMatches() method:
		        final char ch = src.charAt(i);
		        if (ch != firstLo && ch != firstUp)
		            continue;

		        if (src.regionMatches(true, i, what, 0, length))
		            return true;
		    }

		    return false;
		}
}