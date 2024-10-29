package controllers;
import controllers.Park;
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

    private List<Park> parks = new ArrayList<>(List.of(
            new Park("testName", "testLocation", 2, false),
            new Park("Nitesh Park", "Somewhere in the local area?", 1000000, false)
    ));

    @GetMapping("/park")
    public ModelAndView parkPage() {
        ModelAndView modelAndView = new ModelAndView("/park");
        Park selectedPark = parks.get(1);

        modelAndView.addObject("park", selectedPark);
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
        park.setApproved(true); // approves park being added
        parks.add(park); // adds new park to the list
        System.out.println("New Park added: " + park.getName() + "- Approved: " + park.isApproved());
        return new ModelAndView("redirect:/submitted");
    }

    @RequestMapping(value = "/parks/{parkName}")
    public int parkRating(@PathVariable String parkName) {
        for (Park park : parks) {
            if (park.getName().equals(parkName)) {
                return park.getRating();
            }
        }
        return 0;
    }

    @GetMapping("/parkList")
    public ModelAndView parkList() {
        ModelAndView modelAndView = new ModelAndView("parkList");
        modelAndView.addObject("parks", parks);
        return modelAndView;
    }

    @PostMapping("/approvePark/{parkName}")
    public ModelAndView approvePark(@PathVariable String parkName) {
        for (Park park : parks) {
            if (park.getName().equals(parkName)) {
                park.setApproved(true); // Set the park as approved
                break;
            }
        }
        return new ModelAndView("redirect:/parkList"); // Redirect back to the list after approval
    }

    @GetMapping("/disapproveAllParks")
    public void disapproveAllParks() {
        for (Park park : parks) {
            park.setApproved(false); // disapprove each park
        }
    }

}
