package com.videolibrary.tubetagger.controller;

import com.videolibrary.tubetagger.model.Category;
import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.service.CategoryService;
import com.videolibrary.tubetagger.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public String channelsPage(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                               Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());

        if (categoryId != null) {
            List<Video> videos = videoService.getVideosByCategoryId(categoryId);
            model.addAttribute("videos", videos);
        }

        model.addAttribute("selectedCategoryId", categoryId);

        return "categories";
    }
}
