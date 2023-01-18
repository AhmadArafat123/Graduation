package com.test.testpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Provider {
    @Id
    @GeneratedValue
    private Long id;


    @NonNull
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String city;
    /////////////////
    @NonNull
    private boolean availability;
    @NonNull
    private String typeOfService;
    @NonNull
    private String quality;
    ///////////////////////////////
    @NonNull
    private String phoneNum;
    @NonNull
    private String password;
    @NonNull
    private String qualifications;
    @NonNull
    private boolean approved;


    @JsonIgnore
    @OneToMany(mappedBy="provider")
    private List<ServiceModel> serviceModels;
}

