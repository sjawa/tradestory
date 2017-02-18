package de.app.tradestory.controller;

import de.app.tradestory.elasticsearch.Content;
import de.app.tradestory.elasticsearch.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(
			@Valid @RequestBody SearchForm searchForm, Errors errors) {

		AjaxResponseBody result = new AjaxResponseBody();

		//If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {

			result.setMsg(errors.getAllErrors()
					.stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(",")));

			return ResponseEntity.badRequest().body(result);

		}

		List<Content> contents = repository.find(searchForm.getQuery());
		if (contents.isEmpty()) {
			result.setMsg("no content found!");
		} else {
			result.setMsg("success");
		}
		result.setResult(contents);

		return ResponseEntity.ok(result);

	}



}
