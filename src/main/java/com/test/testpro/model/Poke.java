package com.test.testpro.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Poke {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String serviceUserName;
    @NonNull
    private String nameOfCustomer;
    @NonNull
    private Long idOfServic;

    @NonNull
    private float longtid;
    @NonNull
    private float lati;
    @NonNull
    private String phoneNum;
    @NonNull
    private String time;

    @NonNull
    private String notes;

    @NonNull
    private String status;

}
