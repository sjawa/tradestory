package de.app.tradestory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.app.tradestory.elasticsearch.Content;
import de.app.tradestory.elasticsearch.ContentRepository;

@Controller
@RequestMapping("/")
public class SearchController {
	
	@Autowired
	private ContentRepository repository;
	
    @GetMapping
    public String show() {
    	return "index";
    }
	
	
    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute String query) {
    	Map model = new HashMap<String, Object>();
    	
    	List<Content> results = new ArrayList<Content>();
     	int found = 0;
		for (Content content : repository.find(query)) {
			System.out.println(content);
			
			results.add(content);
		}
		
		model.put("results", results);
		
		return new ModelAndView("index", model);
    }
}