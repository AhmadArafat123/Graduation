package com.test.testpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Search {
    @NonNull
    int quality;
    @NonNull
    double price;
    @NonNull
    String type;
    @NonNull
    Boolean available;
    @NonNull
    private float longtid;
    @NonNull
    private float lati;
}

