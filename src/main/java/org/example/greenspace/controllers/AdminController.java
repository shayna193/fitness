package org.example.greenspace.controllers;

import org.example.greenspace.services.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import org.example.greenspace.Park;

@RestController
public class AdminController {
    private ParkService parkService;
    private final List<Park> approvedParks = new ArrayList<>();
    private final List<Park> pendingParks = new ArrayList<>();

    @Autowired
    public AdminController(ParkService parkService) {
        this.parkService = parkService;
        List<Park> parks = parkService.getParks();
        pendingParks.addAll(parks);
    }

    @PostMapping("/approvePark/{parkName}")
    public ModelAndView approvePark(@PathVariable String parkName) {
        for (Park park : pendingParks) {
            if (park.getName().equals(parkName)) {
                park.setApproved(true);
                approvedParks.add(park);     // Add to approved list
                pendingParks.remove(park);   // Remove from pending list
                break;
            }
        }
        return new ModelAndView("redirect:/Admin/parkList");
    }
    @GetMapping("/parkList")
    public ModelAndView parkList() {
        ModelAndView modelAndView = new ModelAndView("parkList");
        modelAndView.addObject("approvedParks", approvedParks);
        modelAndView.addObject("pendingParks", pendingParks);
        return modelAndView;
    }


    @GetMapping("/adminPage")
    public ModelAndView adminPage() {
        return new ModelAndView("/adminPage");
    }

    @GetMapping("/addPark")
    public ModelAndView addPark() {
        return new ModelAndView("/addPark");
    }

    // Admin-specific methods go here
}
