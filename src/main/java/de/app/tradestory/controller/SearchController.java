package de.app.tradestory.controller;

import de.app.tradestory.elasticsearch.Content;
import de.app.tradestory.elasticsearch.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class SearchController {
	
	@Autowired
	private ContentRepository repository;

	@GetMapping
	public String index(){
		return "index";
	}


    @GetMapping("/search")
    public ModelAndView searchForm(){
		Map model = new HashMap<String, Object>();
    	model.put("searchForm", new SearchForm());

		List<Content> results = new ArrayList<Content>();
		for (Content content : repository.findAll()) {
			results.add(content);
		}

		model.put("results", results);

		return new ModelAndView("search", model);
	}


    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute SearchForm form) {
		System.out.println("search for:" + form.getQuery());
		Map model = new HashMap<String, Object>();
    	
    	List<Content> results = new ArrayList<Content>();
		List<Content> contents;

		if(form.getQuery().isEmpty()){
			contents = repository.findAll();
		}
		else{
			contents = repository.find(form.getQuery());
		}

		for (Content content : contents) {
			results.add(content);
		}
		
		model.put("results", results);
		
		return new ModelAndView("search", model);
    }
}
