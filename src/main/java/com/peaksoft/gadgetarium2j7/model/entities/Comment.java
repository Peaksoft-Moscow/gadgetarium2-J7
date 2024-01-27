package com.peaksoft.gadgetarium2j7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String comment;
    private MultipartFile img;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH,CascadeType.REMOVE},mappedBy ="comment" )
    private Product product;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH,CascadeType.REMOVE},mappedBy ="comment" )
    private User user;

}
