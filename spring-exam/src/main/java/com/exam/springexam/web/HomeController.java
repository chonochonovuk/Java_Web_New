package com.exam.springexam.web;

import com.exam.springexam.model.entity.CategoryName;
import com.exam.springexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") != null) {

            model.addAttribute("products", this.productService.findAllProductsByCategory());

                return "home";
        }
        return "index";
    }
}
