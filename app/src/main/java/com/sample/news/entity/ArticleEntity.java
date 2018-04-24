package com.sample.news.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

/**
 * Created by sharaf on 4/23/18.
 */
@Getter
@Setter
@ToString
@Document(collection = "article")
public class ArticleEntity {

    @Id
    @TextIndexed(weight = 3)
    private String header;

    @TextIndexed(weight = 2)
    private String shortDescription;

    @TextIndexed
    private String text;

    private Date publishDate;

   // @DBRef
    private List<AuthorEntity> author;

}
