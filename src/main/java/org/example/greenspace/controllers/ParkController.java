package org.example.greenspace.controllers;

import jakarta.validation.Valid;
import org.example.greenspace.Park;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ParkController {

    private List<Park> parks = List.of(
            new Park("testName", "testLocation", 999999)
    );

    @GetMapping("/park")
    public ModelAndView parkPage() {
        ModelAndView modelAndView = new ModelAndView("/park.html");
        return modelAndView;
    }

    @PostMapping("/submitPark")
    public ModelAndView submitForm(@Valid @ModelAttribute("park") Park park,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("parkForm", model.asMap());
            System.out.println("Error");
            return modelAndView;
        }
        System.out.println(park);
        ModelAndView modelAndView = new ModelAndView("redirect:/submitted");
        return modelAndView;
    }
}
