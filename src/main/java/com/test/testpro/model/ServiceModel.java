package com.test.testpro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

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
    private String name;
    @NonNull
    private String type;
    @NonNull
    private double minPrice;
    @NonNull
    private double maxPrice;
    @NonNull
    private Time time;
    @NonNull
    private String location;
    @NonNull
    private double price;
    @NonNull
    private String quality;
}

