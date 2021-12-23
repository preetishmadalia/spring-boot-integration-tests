package com.example.sb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user-collection")
public class User {
    @Id
    private Number userId;

    private String firstName;

    private String lastName;

    private String emailId;
}
