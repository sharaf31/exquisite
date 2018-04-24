package com.sample.news.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sharaf on 4/23/18.
 */
@Getter
@Setter
@ToString
@Document(collection = "author")
public class AuthorEntity {


    @Id
    private String name;

    public AuthorEntity(String name) {
        this.name = name;
    }
}
