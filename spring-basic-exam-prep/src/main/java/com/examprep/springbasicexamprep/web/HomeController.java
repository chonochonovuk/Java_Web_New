package com.examprep.springbasicexamprep.web;

import com.examprep.springbasicexamprep.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){

        if (httpSession.getAttribute("userServiceModel") == null){
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("items",this.itemService.findAllItems());
            modelAndView.setViewName("home");
        }


        return modelAndView;
    }
}
