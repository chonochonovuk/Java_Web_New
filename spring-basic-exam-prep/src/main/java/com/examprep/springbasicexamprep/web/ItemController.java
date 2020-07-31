package com.examprep.springbasicexamprep.web;

import com.examprep.springbasicexamprep.model.binding.ItemAddBindingModel;
import com.examprep.springbasicexamprep.model.service.ItemServiceModel;
import com.examprep.springbasicexamprep.service.CategoryService;
import com.examprep.springbasicexamprep.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, CategoryService categoryService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addItem(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("userServiceModel") == null){
            return "redirect:/";
        }
       if (!model.containsAttribute("itemAddBindingModel")){
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        return "add-item";
    }

    @PostMapping("/add")
    public ModelAndView addItemConfirm(@Valid @ModelAttribute("itemAddBindingModel")ItemAddBindingModel itemAddBindingModel,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView,
                                       RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("itemAddBindingModel",itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel",bindingResult);
            modelAndView.setViewName("redirect:add");
            return modelAndView;
        }
        ItemServiceModel itemServiceModel = this.itemService.addItem(this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class));
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView){
        modelAndView.addObject("item",this.itemService.findById(id));
        modelAndView.setViewName("details-item");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id, ModelAndView modelAndView){
        this.itemService.deleteItem(id);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
