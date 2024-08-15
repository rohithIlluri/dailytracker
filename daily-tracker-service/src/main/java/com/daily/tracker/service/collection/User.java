package com.daily.tracker.service.collection;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String role;
    private String status;
    private String createdUser;
    private String createdDate;


}
