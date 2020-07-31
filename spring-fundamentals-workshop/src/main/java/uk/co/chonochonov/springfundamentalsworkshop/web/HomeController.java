package uk.co.chonochonov.springfundamentalsworkshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.chonochonov.springfundamentalsworkshop.service.CommentService;
import uk.co.chonochonov.springfundamentalsworkshop.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UserService userService;
    private final CommentService commentService;

    public HomeController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String index(HttpSession httpSession, Model model){

        if (httpSession.getAttribute("user") == null){
            return "index";
        }

        model.addAttribute("usersCount",this.userService.userCount());
        model.addAttribute("avgScore",this.commentService.avgScore());        return "home";
    }
}