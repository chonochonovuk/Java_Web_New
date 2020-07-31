package uk.co.chonochonov.springfundamentalsworkshop.web;

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
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.CommentAddBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.HomeworkAddBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.CommentServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.ExerciseServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.HomeworkServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.service.CommentService;
import uk.co.chonochonov.springfundamentalsworkshop.service.ExerciseService;
import uk.co.chonochonov.springfundamentalsworkshop.service.HomeworkService;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService, CommentService commentService, ModelMapper modelMapper) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null){
            return "redirect:/";
        }
        if (!model.containsAttribute("homeworkAddBindingModel")){
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate",false);
        }

        model.addAttribute("allExerciseNames",this.exerciseService.findAllExerciseNames());
        return "homework-add";
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute("homeworkAddBindingModel")HomeworkAddBindingModel homeworkAddBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView,
                                   HttpSession httpSession,
                                   RedirectAttributes redirectAttributes){

        ExerciseServiceModel exerciseServiceModel = this.exerciseService.findByName(homeworkAddBindingModel.getExercise());
         boolean isLate = exerciseServiceModel.getDueDate().isBefore(LocalDateTime.now());


        if (bindingResult.hasErrors()|| isLate){
            redirectAttributes.addFlashAttribute("isLate",isLate);
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel",homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel",bindingResult);
            modelAndView.setViewName("redirect:add");
        }else {
            HomeworkServiceModel homeworkServiceModel = this.modelMapper.map(homeworkAddBindingModel, HomeworkServiceModel.class);
            homeworkServiceModel.setAddedOn(LocalDateTime.now());
            UserServiceModel usm = (UserServiceModel) httpSession.getAttribute("user");
            homeworkServiceModel.setAuthor(usm);
            homeworkServiceModel.setExerciseServiceModel(exerciseServiceModel);
            this.homeworkService.addHomework(homeworkServiceModel);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/check")
    public String check(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null){
            return "redirect:/";
        }

        if (!model.containsAttribute("commentAddBindingModel")){
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }
        model.addAttribute("homeworkToCheck",this.homeworkService.findOneToCheck());
        return "homework-check";
    }

    @PostMapping("/check")
    public String checkConfirm(@Valid @ModelAttribute("commentAddBindingModel")CommentAddBindingModel commentAddBindingModel,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession httpSession,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentAddBindingModel",commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel",bindingResult);
           return  "redirect:check";
        }
        CommentServiceModel commentServiceModel = this.modelMapper.map(commentAddBindingModel,CommentServiceModel.class);
        HomeworkServiceModel homeworkServiceModel = this.homeworkService.findHomework(commentServiceModel.getHomework());
        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");
        commentServiceModel.setAuthor(userServiceModel);
        commentServiceModel.setHomework(homeworkServiceModel);
        this.commentService.addComment(commentServiceModel);
        return "redirect:/";
    }

}
