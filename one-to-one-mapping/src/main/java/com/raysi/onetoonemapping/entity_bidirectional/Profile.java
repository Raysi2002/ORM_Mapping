package com.raysi.onetoonemapping.entity_bidirectional;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "profile id : " + this.profileId;
    }
}

