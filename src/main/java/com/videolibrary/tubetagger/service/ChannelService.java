package com.videolibrary.tubetagger.service;

import com.videolibrary.tubetagger.model.Channel;
import com.videolibrary.tubetagger.model.Video;
import com.videolibrary.tubetagger.repository.ChannelRepository;
import com.videolibrary.tubetagger.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private VideoRepository videoRepository;

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public List<Channel> searchChannels(String q) {
        if (q == null || q.isBlank()) {
            return channelRepository.findAll();
        }
        return channelRepository.findByNameIgnoreCaseContaining(q.trim());
    }

    public void saveChannel(Channel channel) {
        if (channelRepository.existsByNameIgnoreCase(channel.getName())) {
            throw new IllegalArgumentException("Channel already exists");
        }
        channelRepository.save(channel);
    }

    public Channel getChannelById(Integer id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel not found"));
    }

    public List<Video> getVideosForChannel(Integer channelId) {
        return videoRepository.findByChannelChannelId(channelId);
    }
}
