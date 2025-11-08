package com.videolibrary.tubetagger.service;

import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public boolean saveVideoDetails(Video video){

        // Canonicalize YouTube URL before saving
        String cleanUrl = canonicalizeYouTubeUrl(video.getUrl());
        video.setUrl(cleanUrl);

        boolean isSaved = false;
        Video savedVideo = videoRepository.save(video);
        if(null != savedVideo && savedVideo.getVideoId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public String canonicalizeYouTubeUrl(String url) {
        if (url == null || url.isBlank()) {
            return url;
        }
        // Trim spaces
        url = url.trim();

        // Normalize prefix → always https://www.
        if (!url.startsWith("https://")) {
            url = url.replaceFirst("^(http://)?(www\\.)?", "https://www.");
        }

        // Remove any query parameters after '?' in youtu.be format
        if(url.contains("youtu.be/")) {
            int queryIndex = url.indexOf('?');
            if (queryIndex != -1) {
                url = url.substring(0, queryIndex);
            }
        }
        // Remove any query parameters after '&' in youtube.com format
        if(url.contains("youtube.com/")) {
            int queryIndex = url.indexOf('&');
            if (queryIndex != -1) {
                url = url.substring(0, queryIndex);
            }
        }
        // Normalize youtu.be short links to youtube.com/watch?v=
        if (url.contains("youtu.be/")) {
            String videoId = url.substring(url.lastIndexOf("/") + 1);
            url = "https://www.youtube.com/watch?v=" + videoId;
        }

        return url;
    }

}
