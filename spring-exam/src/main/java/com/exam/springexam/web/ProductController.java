package com.exam.springexam.web;

import com.exam.springexam.model.binding.ProductAddBindingModel;
import com.exam.springexam.model.service.CategoryServiceModel;
import com.exam.springexam.model.service.ProductServiceModel;
import com.exam.springexam.service.CategoryService;
import com.exam.springexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null){
            return "redirect:/";
        }
        if (!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView,
                                   RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel",productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",bindingResult);
            modelAndView.setViewName("redirect:add");
            return modelAndView;
        }
        CategoryServiceModel categoryServiceModel = this.categoryService.findByCategoryName(productAddBindingModel.getCategory());
        ProductServiceModel productServiceModel = this.modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        productServiceModel.setCategoryServiceModel(categoryServiceModel);
        this.productService.addProduct(productServiceModel);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
