package com.test.testpro.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "provider")
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
    @NonNull
    private String reputation;

}

