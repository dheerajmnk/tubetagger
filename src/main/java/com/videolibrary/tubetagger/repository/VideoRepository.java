package com.videolibrary.tubetagger.repository;

import com.videolibrary.tubetagger.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    List<Video> findByChannelChannelId(Integer channelId);

    @Query("SELECT v FROM Video v JOIN v.categories c WHERE c.categoryId = :categoryId")
    List<Video> findByCategoryId(@Param("categoryId") Integer categoryId);
}
