package com.videolibrary.tubetagger.controller;

import com.videolibrary.tubetagger.model.Channel;
import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.service.ChannelService;
import com.videolibrary.tubetagger.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;

    @PostMapping("/save")
    public String saveChannel(@ModelAttribute("channel") Channel channel, Errors errors, RedirectAttributes redirectAttributes, Model model) {
        try {
            channelService.saveChannel(channel);
            redirectAttributes.addFlashAttribute("successMessage", "Channel added successfully!");
            return "redirect:/add-video";
        }
        catch(IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.video", errors);
            redirectAttributes.addFlashAttribute("channel", channel);
            return "redirect:/add-video";
        }
    }

    @GetMapping
    public String channelsPage(@RequestParam(value = "channelId", required = false) Integer channelId,
                               Model model) {

        model.addAttribute("channel", new Channel());
        model.addAttribute("channels", channelService.getAllChannels());

        if (channelId != null) {
            List<Video> videos = videoService.getVideosByChannelId(channelId);
            model.addAttribute("videos", videos);
        }

        model.addAttribute("selectedChannelId", channelId);

        return "channels";
    }
}
