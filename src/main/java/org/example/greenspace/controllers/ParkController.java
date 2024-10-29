package org.example.greenspace.controllers;

import jakarta.validation.Valid;
import org.example.greenspace.Park;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ParkController {

    private List<Park> parks = List.of(
            new Park("Nitesh Park", "Somewhere in the local area?")
    );

    @GetMapping("/park")
    public ModelAndView parkPage() {
        ModelAndView modelAndView = new ModelAndView("/park");
        Park selectedPark = parks.get(1);

        modelAndView.addObject("park", selectedPark);
        return modelAndView;
    }

    @GetMapping("/park/{parkName}")
    public ModelAndView parkPage(@PathVariable String parkName) {
        for (Park park : parks) {
            if (park.getName().equals(parkName)) {
                ModelAndView modelAndView = new ModelAndView("park");
                modelAndView.addObject("park", park);
                return modelAndView;
            }
        }
        return new ModelAndView("Park not found");
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


    @GetMapping("/parkList")
    public ModelAndView parkList() {
        ModelAndView modelAndView = new ModelAndView("parkList");
        modelAndView.addObject("parks", parks);
        return modelAndView;
    }

    @PostMapping("/submitReview")
    public ModelAndView submitReview(@RequestParam int rating, @RequestParam String parkName) {

        Park currentPark = null;
        for (Park park : parks) {
            if (park.getName().equals(parkName)) {
                park.addRating(rating);
                currentPark = park;
            }
        }
        if (currentPark == null) {
            System.out.println("Error: Park not found");
            return new ModelAndView("redirect:/parkList"); // Redirects to home page if park
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/park");
        modelAndView.addObject("park", currentPark);
        return modelAndView;
    }

}
