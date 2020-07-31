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
import uk.co.chonochonov.springfundamentalsworkshop.model.binding.ExerciseAddBindingModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.ExerciseServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.service.ExerciseService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model,
                      HttpSession httpSession){
        if (httpSession.getAttribute("user") == null){
            return "redirect:/";
        }
        if (!model.containsAttribute("exerciseAddBindingModel")){
            model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    public ModelAndView addExercise(@Valid @ModelAttribute("exerciseAddBindingModel") ExerciseAddBindingModel exerciseAddBindingModel,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView,
                                    HttpSession httpSession,
                                    RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel",exerciseAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel",bindingResult);
            modelAndView.setViewName("redirect:add");
        }else {
            ExerciseServiceModel exerciseServiceModel =
                    this.modelMapper.map(exerciseAddBindingModel,ExerciseServiceModel.class);
            if (exerciseServiceModel.getAddedOn().isBefore(LocalDateTime.now()) &&
                    exerciseAddBindingModel.getDueDate().isAfter(LocalDateTime.now())){
                this.exerciseService.addExercise(exerciseServiceModel);
                modelAndView.setViewName("redirect:/");
            }else {
                redirectAttributes.addFlashAttribute("exerciseAddBindingModel",exerciseAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel",bindingResult);
                modelAndView.setViewName("redirect:add");
            }
        }
        return modelAndView;
    }
}
