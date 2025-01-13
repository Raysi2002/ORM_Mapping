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

    // Bidirectional mapping: Profile is mapped by 'profile' field in User entity
    // @JsonIgnore prevents infinite recursion during JSON serialization
    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "profile id : " + this.profileId;
    }
}

