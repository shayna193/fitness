package org.example.greenspace.controllers;

import jakarta.validation.Valid;
import org.example.greenspace.Comment;
import org.example.greenspace.Park;
import org.example.greenspace.services.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ParkController {

    private ParkService parkService;
    @Autowired
    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @GetMapping("/home")
    public ModelAndView homePage(Model model) {
        List<Park> parks = parkService.getParks();
        Park highestRated = parks.stream().max((p1, p2) -> Double.compare(p1.getRating(), p2.getRating())).orElse(parks.get(0));

        Park similarPark1 = parks.stream().filter(park -> !park.equals(highestRated)).findFirst().orElse(null);
        Park similarPark2 = parks.stream().filter(park -> !park.equals(highestRated) && !park.equals(similarPark1)).findFirst().orElse(null);

        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("featuredPark", highestRated);
        modelAndView.addObject("similarPark1", similarPark1);
        modelAndView.addObject("similarPark2", similarPark2);
        return modelAndView;
    }

    @GetMapping("/park/{parkName}")
    public ModelAndView parkPage(@PathVariable String parkName) {
        List<Park> parks = parkService.getParks();
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
        return new ModelAndView("redirect:/submitted");
    }



    @PostMapping("/submitReview")
    public ModelAndView submitReview(@RequestParam String username, @RequestParam String comment, @RequestParam int rating, @RequestParam String parkName) {

        List<Park> parks = parkService.getParks();
        Park currentPark = null;
        for (Park park : parks) {
            if (park.getName().equals(parkName)) {
                park.addRating(rating);
                Comment comment1 = new Comment(username, comment, rating);
                System.out.println(comment1);
                park.addComment(comment1);
                currentPark = park;
            }
        }
        if (currentPark == null) {
            System.out.println("Error: Park not found");
            return new ModelAndView("redirect:/parkList");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/park/" + parkName);
        modelAndView.addObject("park", currentPark);
        return modelAndView;
    }

    @GetMapping("/explore")
    public ModelAndView explore() {

        ModelAndView modelAndView = new ModelAndView("/explore");
        modelAndView.addObject("parks", parkService.getParks());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("/register");
    }

}
