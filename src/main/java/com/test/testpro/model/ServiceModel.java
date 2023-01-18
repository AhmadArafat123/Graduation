package com.test.testpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor

public class ServiceModel {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String userName;
    @NonNull
    private String serviceName;
    @NonNull
    private String description;
    @NonNull
    private boolean poke;
    @NonNull
    private double price;
    @NonNull
    private float longtid;
    @NonNull
    private float lati;
    @NonNull
    private String quality;
    @NonNull
    private String time;
    @NonNull
    private boolean available;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "provide_id")
    private Provider provider;
}

