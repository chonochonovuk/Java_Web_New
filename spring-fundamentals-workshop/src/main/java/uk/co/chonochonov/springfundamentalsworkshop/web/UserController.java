package uk.co.chonochonov.springfundamentalsworkshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.UserAddBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.UserLoginBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.view.UserDetailsViewModel;
import uk.co.chonochonov.springfundamentalsworkshop.service.UserService;

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

    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute(
            "userLoginBindingModel")UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult,
                              ModelAndView modelAndView,
                              HttpSession httpSession, RedirectAttributes redirectAttributes){
        modelAndView.addObject(userLoginBindingModel);
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute(
            "userLoginBindingModel")UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView,
                                     HttpSession httpSession,
                                     RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            modelAndView.setViewName("redirect:/users/login");
        }else {
            UserServiceModel usm = this.userService.findByUsername(userLoginBindingModel.getUsername());
            if (usm == null || !usm.getPassword().equals(userLoginBindingModel.getPassword())){
               redirectAttributes.addFlashAttribute("notFound",true);
               redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
                modelAndView.setViewName("redirect:/users/login");

            }else {
                httpSession.setAttribute("user",usm);
                httpSession.setAttribute("id",usm.getId());
                httpSession.setAttribute("role",usm.getRole());
                modelAndView.setViewName("redirect:/");
            }

        }

        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userAddBindingModel")
                                       UserAddBindingModel userAddBindingModel,
                           BindingResult bindingResult,
                           ModelAndView modelAndView,
                           RedirectAttributes redirectAttributes){
        modelAndView.addObject(userAddBindingModel);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userAddBindingModel")
                                        UserAddBindingModel userAddBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel",userAddBindingModel);
            modelAndView.setViewName("redirect:/users/register");
        }else {
            UserServiceModel userServiceModel = this.userService.registerUser(
                    this.modelMapper.map(userAddBindingModel,UserServiceModel.class)
            );
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession, ModelAndView modelAndView){
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") String id, Model model, HttpSession httpSession){
        model.addAttribute("user",this.modelMapper.map(this.userService.findById(id), UserDetailsViewModel.class));
        return "profile";
    }
}

