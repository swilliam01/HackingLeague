package com.example.demo.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.models.CloudinaryConfig;
import com.example.demo.Repositories.HackRepo;
import com.example.demo.Repositories.SponsorRepo;
import com.example.demo.models.Hackathon;
import com.example.demo.models.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {


    @Autowired
    EmailService emailService;

    @Autowired
    HackRepo hackRepo;

    @Autowired
    SponsorRepo sponsorRepo;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String homepage(){
        return "base";
    }

    @RequestMapping("/list")
    public String hacklisting(Model model){
        model.addAttribute("hackathon", hackRepo.findAll());
        return "hackList";
    }

    @GetMapping("/addHack")
    public String showRegistrationPage(Model model){
        model.addAttribute("hackathon", new Hackathon());
        return"addHackathon";
    }
    @PostMapping("/processHack")
    public String processRegistrationPage(@Valid
                                          @ModelAttribute("hackathon") Hackathon hackathon, BindingResult result,
                                          @RequestParam("file") MultipartFile file){
        if (result.hasErrors()){
            return "addHackathon";
        }
       // hackathon.se(userService.getUser());
            hackRepo.save(hackathon);
        if (file.isEmpty()) {
            return "addHackathon";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            String headshotURL = uploadResult.get("url").toString();
//            String getUrl = cloudc.createUrl(headshotURL,100,100,"pad");
            hackathon.setHeadshot(headshotURL);
            hackRepo.save(hackathon);

        } catch (IOException e) {
            e.printStackTrace();
            return "hackList";
        }

        return "redirect:/";
    }

    @GetMapping("/addsponsor")
    public String showAddSponsorPage(Model model){
        model.addAttribute("sponsor", new Sponsor());
        return"sponsorForm";
    }

    @PostMapping("/addsponsor")
    public String processAddSponsorPage(@Valid
                                          @ModelAttribute("sponsor") Sponsor sponsor, BindingResult result,
                                          Model model){
        model.addAttribute("sponsor",sponsor);
        if (result.hasErrors()){
            return "sponsorForm";
        }
            sponsorRepo.save(sponsor);
            String getEmailTo = sponsor.getEmail();
            emailService.SendSimpleEmail(getEmailTo,"Thank you for providing your information. You will be contacted soon for further course of action.");
        return "success";
    }
}
