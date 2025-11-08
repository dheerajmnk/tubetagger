package com.videolibrary.tubetagger.controller;

import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/add-video")
    public String showAddVideoForm(Model model) {
        if (!model.containsAttribute("video")) {
            model.addAttribute("video", new Video());
        }
        return "add-video";
    }

    @PostMapping("/videos/save")
    public String saveVideo(@Valid @ModelAttribute("video") Video video, Errors errors, RedirectAttributes redirectAttributes) {
        if(errors.hasErrors()){
            redirectAttributes.addFlashAttribute("errorMessage", "Please fill all required fields correctly!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.video", errors);
            redirectAttributes.addFlashAttribute("video", video);
            return "redirect:/add-video";
        }
        videoService.saveVideoDetails(video);
        redirectAttributes.addFlashAttribute("successMessage", "Video added successfully!");
        return "redirect:/add-video";
    }

}
