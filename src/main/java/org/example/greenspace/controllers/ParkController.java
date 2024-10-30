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
            new Park("Nitesh Park",
                    "Roath",
                    "The Nitesh park is a serene escape nestled in rolling hills and ancient oak groves." +
                            " Wander through vibrant flower gardens, enjoy a picnic by the sparkling lake," +
                            " or explore the wooded trails." +
                            " The nature playground and weekend workshops invite all ages to reconnect" +
                            " with nature and embrace outdoor adventure.",
                    "07:30 - 17:00",
                    new String[] {"Playground", " Cafe", " Picnic Area"},
                    false,
                    true,
                    true,
                    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d9932.638309147138!2d-3.1854631938558042!3d51." +
                            "510288303798006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1c8b72d4d029%3A0xf5ae70dbffaa1808!" +
                            "2sRoath%20Park%20Lake!5e0!3m2!1sen!2suk!4v1730232983639!5m2!1sen!2suk"),
            new Park("Ian Park",
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
                    true,
                    true,
                    true,
                    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4968.894358682748!2d-3.1803703!3d51.48666" +
                            "09!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1cbb6c66ff61%3A0x2b7d359fb56821bd!2sCath" +
                            "ays%20Park%2C%20Cardiff!5e0!3m2!1sen!2suk!4v1730232845332!5m2!1sen!2suk"),
            new Park("Louise Park",
                    "Pontcanna",
                    "Louise Park is a vibrant green space known for its unique blend of urban charm and natural beauty." +
                            " With expansive lawns perfect for picnics and morning yoga, the park also features a tranquil" +
                            " riverside trail that winds through a canopy of flowering trees. A community garden showcases" +
                            " seasonal blooms and edible plants, inviting visitors to learn and connect with local gardeners." +
                            " The open-air gym and playground make it a popular spot for fitness enthusiasts and families alike." +
                            " Whether you're here to unwind, explore, or engage with the community, Louise Park offers a welcoming" +
                            " escape for all.",
                    "06:30 - 17:30",
                    new String[] {"Gym", " Garden", " Picnic Area"},
                    true,
                    false,
                    false,
                    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2484.1572416440067!2d-3.204108624485" +
                            "2344!3d51.491981911830955!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e1b60ae18db9d" +
                            "%3A0x15c18621c2abcea2!2sPontcanna%20Fields!5e0!3m2!1sen!2suk!4v1730232890536!5m2!1sen!2suk")
    );

    @GetMapping("/home")
    public ModelAndView homePage(Model model) {

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
            return new ModelAndView("redirect:/parkList");
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

}
