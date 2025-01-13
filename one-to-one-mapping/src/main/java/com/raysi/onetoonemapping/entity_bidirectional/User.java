package com.raysi.onetoonemapping.entity_bidirectional;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    // One-to-one relationship configuration:
    // - cascade = ALL: all operations cascade to Profile
    // - orphanRemoval: removes Profile when User is deleted
    // - fetch = EAGER: Profile is loaded immediately with User
    // JoinColumn specifies the foreign key column in User table
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private Profile profile;

    @Override
    public String toString() {
        return "User id : " + this.userId +
                " DOB : " + this.dob +
                " Profile id : " + this.profile.toString();
    }
}
