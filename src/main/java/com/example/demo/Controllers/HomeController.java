package com.example.demo.Controllers;

import com.example.demo.Repositories.HackRepo;
import com.example.demo.Repositories.SponsorRepo;
import com.example.demo.models.Hackathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    HackRepo hackRepo;

    @Autowired
    SponsorRepo sponsorRepo;

    @RequestMapping("/")
    public String hackListing(Model model){
        model.addAttribute("hackathon", hackRepo.findAll());
        return "hackList";
    }
    @GetMapping("/addHack")
    public String showRegistrationPage(Model model){
        model.addAttribute("hackathon", new Hackathon());
        return"addHackathon";
    }
    @PostMapping("/addHack")
    public String processRegistrationPage(@Valid
                                          @ModelAttribute("hackathon") Hackathon hackathon, BindingResult result,
                                          Model model){
        model.addAttribute("hackathon",hackathon);
        if (result.hasErrors()){
            return "addHackathon";
        }
        else{
            hackRepo.save(hackathon);
        }
        return "redirect:/";
    }


}
