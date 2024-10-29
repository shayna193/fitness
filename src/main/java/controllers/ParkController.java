package controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @PostMapping("/submitPark")
    public ModelAndView submitForm(@Valid @ModelAttribute("park") Park park,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
}
