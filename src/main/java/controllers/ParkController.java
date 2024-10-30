<<<<<<< HEAD:src/main/java/controllers/ParkController.java
package controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
=======
package org.example.greenspace.controllers;

import jakarta.validation.Valid;
import org.example.greenspace.Park;
>>>>>>> 9e1acba05285ddd40e61ab877f553584153553ee:src/main/java/org/example/greenspace/controllers/ParkController.java
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD:src/main/java/controllers/ParkController.java
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class ParkController {

    private List<Park> approvedParks = new ArrayList<>();
    private List<Park> pendingParks = new ArrayList<>(List.of(
            new Park("Linkin Park", "Ends", 2, false),
            new Park("Nitesh Park", "Nether World", 5, false)
    ));

    // New parks are added as pending
=======
import java.util.List;

@RestController
public class ParkController {

    private List<Park> parks = List.of(
            new Park("Nitesh Park", "Roath", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d9932.638309147138!2d-3.1854631938558042!3d51.510288303798006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1c8b72d4d029%3A0xf5ae70dbffaa1808!2sRoath%20Park%20Lake!5e0!3m2!1sen!2suk!4v1730232983639!5m2!1sen!2suk"),
            new Park("Ian Park", "Cathays", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4968.894358682748!2d-3.1803703!3d51.4866609!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1cbb6c66ff61%3A0x2b7d359fb56821bd!2sCathays%20Park%2C%20Cardiff!5e0!3m2!1sen!2suk!4v1730232845332!5m2!1sen!2suk"),
            new Park("Louise Park", "Pontcanna", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2484.1572416440067!2d-3.2041086244852344!3d51.491981911830955!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1b60ae18db9d%3A0x15c18621c2abcea2!2sPontcanna%20Fields!5e0!3m2!1sen!2suk!4v1730232890536!5m2!1sen!2suk")
    );

    @GetMapping("/home")
    public ModelAndView homePage(Model model) {
        return new ModelAndView("/home");
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

>>>>>>> 9e1acba05285ddd40e61ab877f553584153553ee:src/main/java/org/example/greenspace/controllers/ParkController.java
    @PostMapping("/submitPark")
    public ModelAndView submitForm(@Valid @ModelAttribute("park") Park park,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
<<<<<<< HEAD:src/main/java/controllers/ParkController.java
            return new ModelAndView("addPark", model.asMap());
        }
        park.setApproved(false); // Park is pending approval
        pendingParks.add(park);
        System.out.println("New pending park added: " + park.getName());
        return new ModelAndView("redirect:/submitted");
    }

    // Approve a specific pending park
    @PostMapping("/approvePark/{parkName}")
    public ModelAndView approvePark(@PathVariable String parkName) {
        for (Park park : pendingParks) {
            if (park.getName().equals(parkName)) {
                park.setApproved(true);
                approvedParks.add(park);      // Move to approved list
                pendingParks.remove(park);    // Remove from pending list
                break;
            }
        }
        return new ModelAndView("redirect:/Admin/parkList");
    }

    // Endpoint to edit park details
    @PostMapping("/editPark/{parkName}")
    public ModelAndView editPark(@PathVariable String parkName,
                                 @RequestParam String newName,
                                 @RequestParam String newLocation,
                                 @RequestParam int newRating) {
        for (Park park : approvedParks) {
            if (park.getName().equals(parkName)) {
                park.setName(newName);
                park.setLocation(newLocation);
                park.setRating(newRating);
                System.out.println("Updated Park: " + parkName);
                break;
            }
        }
        return new ModelAndView("redirect:/Admin/parkList"); // Redirect to updated list
    }

    // View both lists
    @GetMapping("/parkList")
    public ModelAndView parkList() {
        ModelAndView modelAndView = new ModelAndView("parkList");
        modelAndView.addObject("approvedParks", approvedParks);
        modelAndView.addObject("pendingParks", pendingParks);
        return modelAndView;
    }

    // Admin can reset all parks to pending if needed
    @GetMapping("/disapproveAllParks")
    public void disapproveAllParks() {
        pendingParks.addAll(approvedParks);  // Move all to pending
        approvedParks.clear();
        pendingParks.forEach(park -> park.setApproved(false));
    }
=======
            ModelAndView modelAndView = new ModelAndView("parkForm", model.asMap());
            System.out.println("Error");
            return modelAndView;
        }
        System.out.println(park);
        return new ModelAndView("redirect:/submitted");
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
        ModelAndView modelAndView = new ModelAndView("redirect:/park/" + parkName);
        modelAndView.addObject("park", currentPark);
        return modelAndView;
    }

    @GetMapping("/explore")
    public ModelAndView explore() {
        return new ModelAndView("/explore");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("/register");
    }

>>>>>>> 9e1acba05285ddd40e61ab877f553584153553ee:src/main/java/org/example/greenspace/controllers/ParkController.java
}
