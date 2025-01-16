package com.raysi.manytomany.enitity2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_seq")
    @SequenceGenerator(
            name = "post_seq",
            sequenceName = "post_sequence",
            allocationSize = 116
    )
    private Long postId;
    private String postName;
    @CreationTimestamp
    private LocalDateTime postDate;
    private String postContent;
    @Builder.Default
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

    public void getData(){
        System.out.println(
                "Post ID: " + this.postId +
                        "Post Name: " + this.postName +
                        "Post Date: " + this.postDate +
                        "Post Content: " + this.postContent
        );
        System.out.println("Tags: ");
        tags.forEach(tag -> tag.getTags());
    }
}
