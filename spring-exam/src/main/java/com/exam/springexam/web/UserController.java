package com.exam.springexam.web;

import com.exam.springexam.model.binding.UserLoginBindingModel;
import com.exam.springexam.model.binding.UserRegisterBindingModel;
import com.exam.springexam.model.service.UserServiceModel;
import com.exam.springexam.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
        }

        return "register";
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }

        return "login";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
            modelAndView.setViewName("redirect:register");
            return modelAndView;
        }

        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        modelAndView.setViewName("redirect:login");
        return modelAndView;

    }
    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession httpSession){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);
            modelAndView.setViewName("redirect:login");
            return modelAndView;
        }

        UserServiceModel userServiceModel = this.userService.findByUsername(userLoginBindingModel.getUsername());
        if (userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())){
            redirectAttributes.addFlashAttribute("notFound",true);
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            modelAndView.setViewName("redirect:login");
            return modelAndView;
        }

        httpSession.setAttribute("user",userServiceModel);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession, ModelAndView modelAndView){
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


}
