package org.example.greenspace.controllers;

import ch.qos.logback.core.model.Model;
import org.example.greenspace.Park;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller

public class AdminController {

    private List<Park> approvedParks = new ArrayList<>();
    private List<Park> pendingParks = new ArrayList<>();

    // Add test data
    public AdminController() {
        pendingParks.add(new Park("Nitesh Park",
                "Roath",
                "The Nitesh park is a serene escape nestled in rolling hills and ancient oak groves." +
                        " Wander through vibrant flower gardens, enjoy a picnic by the sparkling lake," +
                        " or explore the wooded trails." +
                        " The nature playground and weekend workshops invite all ages to reconnect" +
                        " with nature and embrace outdoor adventure.",
                "07:30 - 17:00",
                new String[] {"Playground", " Cafe", " Picnic Area"},
                new String[] {
                        "/Images/roath-park-lake-1.jpg",
                        "/Images/roath-park-lake-2.jpg",
                        "/Images/roath-park-lake-3.jpg",
                        "/Images/roath-park-lake-4.jpg",
                        "/Images/roath-park-lake-5.jpg"},
                false,
                true,
                true,
                "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d9932.638309147138!2d-3.1854631938558042!3d51." +
                        "510288303798006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1c8b72d4d029%3A0xf5ae70dbffaa1808!" +
                        "2sRoath%20Park%20Lake!5e0!3m2!1sen!2suk!4v1730232983639!5m2!1sen!2suk"));
        pendingParks.add(new Park("Ian Park",
                "Cathays",
                "Ian Park offers a refreshing retreat in the heart of the city, blending open meadows with" +
                        " pockets of dense woodlands. Stroll along winding paths framed by seasonal wildflowers," +
                        " or relax by the picturesque pond that mirrors the surrounding landscape." +
                        " For families, the park boasts a well-equipped adventure playground and picnic" +
                        " spots shaded by century-old trees. Nature enthusiasts can enjoy birdwatching" +
                        " from hidden alcoves or join one of the many weekend nature walks and workshops." +
                        " Whether you're seeking tranquility or a spot for outdoor activities, Ian Park is a" +
                        " haven for visitors of all ages.",
                "07:00 - 16:30",
                new String[] {"Garden", " Cafe", " Lake"},
                new String[] {
                        "/Images/CathaysPark1.jpg",
                        "/Images/CathaysPark2.jpg",
                        "/Images/CathaysPark3.jpg",
                        "/Images/CathaysPark4.jpg",
                        "/Images/CathaysPark5.jpg"},
                true,
                true,
                true,
                "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4968.894358682748!2d-3.1803703!3d51.48666" +
                        "09!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1cbb6c66ff61%3A0x2b7d359fb56821bd!2sCath" +
                        "ays%20Park%2C%20Cardiff!5e0!3m2!1sen!2suk!4v1730232845332!5m2!1sen!2suk"));
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        //model.addAttribute("approvedParks", approvedParks);
        //model.addAttribute("pendingParks", pendingParks);
        return "adminDashboard"; // Thymeleaf view name
    }

    @PostMapping("/approvePark/{parkName}")
    public String approvePark(String parkName) {
        for (Park park : pendingParks) {
            if (park.getName().equals(parkName)) {
                //park.setApproved(true);
                approvedParks.add(park);
                pendingParks.remove(park);
                break;
            }
        }
        return "redirect:/Admin/dashboard";
    }

    @PostMapping("/editPark/{parkName}")
    public String editPark(String parkName,
                           @RequestParam String newName,
                           @RequestParam String newLocation,
                           @RequestParam int newRating) {
        for (Park park : approvedParks) {
            if (park.getName().equals(parkName)) {
                park.setName(newName);
                park.setLocation(newLocation);
                park.setRating(newRating);
                break;
            }
        }
        return "redirect:/Admin/dashboard";
    }
}
