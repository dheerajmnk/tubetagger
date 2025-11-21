package com.videolibrary.tubetagger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "channel", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Integer channelId;

    @NotBlank(message = "Channel name cannot be blank")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "channel")
    private List<Video> videos;

}
