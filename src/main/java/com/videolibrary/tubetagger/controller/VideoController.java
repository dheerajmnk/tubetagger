package com.videolibrary.tubetagger.controller;

import com.videolibrary.tubetagger.model.Channel;
import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.service.ChannelService;
import com.videolibrary.tubetagger.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChannelService channelService;

    @GetMapping({"", "/", "/videos"})
    public String viewVideos(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos);
        return "video-list";
    }

    @GetMapping("/add-video")
    public String showAddVideoForm(Model model) {
        if (!model.containsAttribute("video")) {
            model.addAttribute("video", new Video());
        }
        model.addAttribute("channels", channelService.getAllChannels());
        model.addAttribute("channel", new Channel());
        return "add-video";
    }

    @PostMapping("/videos/save")
    public String saveVideo(@Valid @ModelAttribute("video") Video video, Errors errors, RedirectAttributes redirectAttributes, Model model) {
        if(errors.hasErrors()){
            redirectAttributes.addFlashAttribute("errorMessage", "Please fill all required fields correctly!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.video", errors);
            redirectAttributes.addFlashAttribute("video", video);
            return "redirect:/add-video";
        }
        try {
            videoService.saveVideoDetails(video);
            redirectAttributes.addFlashAttribute("successMessage", "Video added successfully!");
            return "redirect:/add-video";
        }
        catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "This video already exists in the database.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.video", errors);
            redirectAttributes.addFlashAttribute("video", video);
            return "redirect:/add-video";
        }
        catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.video", errors);
            redirectAttributes.addFlashAttribute("video", video);
            return "redirect:/add-video";
        }
    }

}
