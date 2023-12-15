package org.example.Social.App.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer commentID;

    private String commentData;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    User commenter;

}
