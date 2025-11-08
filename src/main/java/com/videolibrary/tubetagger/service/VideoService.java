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
        boolean isSaved = false;
        Video savedVideo = videoRepository.save(video);
      if(null != savedVideo && savedVideo.getVideoId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
}
