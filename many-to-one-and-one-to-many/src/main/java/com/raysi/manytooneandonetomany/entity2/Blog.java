package com.raysi.manytooneandonetomany.entity2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    private String heading;
    private String content;
    @CreationTimestamp
    private LocalDateTime creationTime;
    @UpdateTimestamp
    private LocalDateTime modificationTime;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "\nID: " + this.getBlogId() +
                "\nHeading: " + this.getHeading() +
                "\nContent: " + this.getContent() +
                "\nCreation Time\n: " + this.getCreationTime() +
                "\nUpdate Time\n: " + this.getModificationTime();
    }
}
