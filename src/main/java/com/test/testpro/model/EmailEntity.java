package com.test.testpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity {
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}

