package com.test.testpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "customer")
public class Rate {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String providerUserName;
    @NonNull
    private String customerUserName;
    @NonNull
    private String quality;


}

