package com.videolibrary.tubetagger.repository;

import com.videolibrary.tubetagger.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    List<Channel> findByNameIgnoreCaseContaining(String name);

    boolean existsByNameIgnoreCase(String name);

}
