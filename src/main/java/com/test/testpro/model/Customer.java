package com.test.testpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(unique=true)
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String city;
    @NonNull
    private String phoneNum;
    @NonNull
    private String password;



    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private List<ServiceModel> serviceModels;
}

