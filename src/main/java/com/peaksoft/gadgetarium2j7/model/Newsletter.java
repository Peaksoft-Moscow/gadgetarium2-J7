package com.peaksoft.gadgetarium2j7.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "newsletter")
@Getter
@Setter
@NoArgsConstructor
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSender;
    @Column(length = 256)
    private String descriptionSender;
    private String startletter;
    private String endletter;
}
