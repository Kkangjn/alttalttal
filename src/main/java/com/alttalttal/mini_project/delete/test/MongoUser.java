package com.alttalttal.mini_project.delete.test;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document(collation = "User")
@Getter
@NoArgsConstructor
@Setter
public class MongoUser {
    @Id
    private BigInteger id;
    private Long userId;
    private String name;
    private String password;
    private List<MongoList> mongoLists;
}
