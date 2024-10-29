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
            new Park("testName", "testLocation", 2),
            new Park("Nitesh Park", "Somewhere in the local area?", 1000000)
    );

    @GetMapping("/park")
    public ModelAndView parkPage() {
        ModelAndView modelAndView = new ModelAndView("/park");
        Park selectedPark = parks.get(1);

        modelAndView.addObject("park", selectedPark);
        return modelAndView;
    }

    @GetMapping("/{parkName}")
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


//    @RequestMapping(value="/parks/{parkName}")
//    public int parkRating(@PathVariable String parkName) {
//        for (Park park : parks) {
//            if (park.getName().equals(parkName)) {
//                return park.getRating();
//            }
//        }
//        return 0;
//    }

    @GetMapping("/parkList")
    public ModelAndView parkList() {
        ModelAndView modelAndView = new ModelAndView("parkList");
        modelAndView.addObject("parks", parks);
        return modelAndView;
    }

}
