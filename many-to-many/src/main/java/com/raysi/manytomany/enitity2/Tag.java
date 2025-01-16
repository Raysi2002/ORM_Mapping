package com.raysi.manytomany.enitity2;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long tagId;
    private String tagName;

    public void getTags(){
        System.out.println(
                "Tag ID: " + this.tagId +
                        "   Tag Name: " + this.tagName
        );
    }
}
