package com.ecoverde.estateagency.web;

import com.ecoverde.estateagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/manage-users")
    @PreAuthorize("hasRole('ADMIN')")
    public String manageUsers(Model model){
        model.addAttribute("allUsers",this.userService.findAll());
        return "admin";
    }

    @PostMapping("/set-role-user/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String setRoleUser(@PathVariable String username, Model model){
        return "admin";
    }

    @PostMapping("/set-role-admin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String setRoleAdmin(@PathVariable String username, Model model){
        return "admin";
    }

    @PostMapping("/set-role-agent/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String setRoleAgent(@PathVariable String username, Model model){
        return "admin";
    }

    @PostMapping("/set-role-moderator/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String setRoleModerator(@PathVariable String username, Model model){
        return "admin";
    }

    @PostMapping("/set-role-owner/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String setRoleOwner(@PathVariable String username, Model model){
        return "admin";
    }
}
