package de.app.tradestory.controller;

import de.app.tradestory.model.Category;
import de.app.tradestory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @GetMapping
    public String categories() {
        return "category/show";
    }

    @RequestMapping(
        value = {"/create"},
        method = {RequestMethod.POST})
    @Transactional
    public ModelAndView create(@RequestParam(value = "name") String name){
        ModelMap model = new ModelMap();

        Category category = new Category();
        category.setName(name);
        repository.save(category);

        Iterable<Category> categories = repository.findAll();
        model.addAttribute("categories", categories.iterator());
        return new ModelAndView("/category/show", model);
    }

}
