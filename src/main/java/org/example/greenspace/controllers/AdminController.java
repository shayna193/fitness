package org.example.greenspace.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    // Static data for pending and approved parks
    private List<Park> approvedParks = new ArrayList<>();
    private List<Park> pendingParks = new ArrayList<>();

    // Initialize with some test data
    public AdminController() {
        pendingParks.add(new Park("Linkin Park", "Ends", 2, false));
        pendingParks.add(new Park("Nitesh Park", "Nether World", 5, false));
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



    // Admin-specific methods go here
}
