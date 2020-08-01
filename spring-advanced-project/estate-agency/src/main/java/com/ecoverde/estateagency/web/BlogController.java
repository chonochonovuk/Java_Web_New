package com.ecoverde.estateagency.web;

import com.ecoverde.estateagency.model.service.BlogServiceModel;
import com.ecoverde.estateagency.model.view.BlogViewModel;
import com.ecoverde.estateagency.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogController(BlogService blogService, ModelMapper modelMapper) {
        this.blogService = blogService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String blog(Model model){
        Set<BlogServiceModel> blogs = this.blogService.findAll();
        model.addAttribute("allBlogs",blogs);
        return "blog";
    }
    @GetMapping("/details/{title}")
    public String blogDetails(@PathVariable String title, Model model){
        model.addAttribute("blogDetails",this.blogService.findByTitle(title));
        return "blog-single";
    }

}
