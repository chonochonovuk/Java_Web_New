package uk.co.chonochonov.springfundamentalsworkshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.RoleAddBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.service.RoleService;
import uk.co.chonochonov.springfundamentalsworkshop.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("roleAddBindingModel") RoleAddBindingModel roleAddBindingModel,
                            BindingResult bindingResult,
                            ModelAndView modelAndView,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes){
        modelAndView.addObject("usernames", this.userService.findAllUsernames());
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRole(@ModelAttribute("roleAddBindingModel") RoleAddBindingModel roleAddBindingModel,
                                ModelAndView modelAndView){
                    modelAndView.setViewName("redirect:/");

                    this.userService.userAddRole(roleAddBindingModel.getUsername(),roleAddBindingModel.getRole());
                    return modelAndView;
    }
}
