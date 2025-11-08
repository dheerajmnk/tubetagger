package com.videolibrary.tubetagger.model;

import com.videolibrary.tubetagger.annotation.ValidYouTubeUrl;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;

    @ValidYouTubeUrl
    @Column(unique = true, nullable = false)
    private String url;

    @NotBlank(message = "Video Title cannot be blank")
    @Column(nullable = false)
    private String title;

}