package com.test.testpro.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @Field(type = FieldType.Text)
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String city;
    @NonNull
    private String reputation;

}

