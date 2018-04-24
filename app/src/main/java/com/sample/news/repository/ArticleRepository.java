package com.sample.news.repository;

import com.sample.news.entity.ArticleEntity;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sharaf on 4/23/18.
 */
@Repository
public interface ArticleRepository extends MongoRepository<ArticleEntity,String> {

    public List<ArticleEntity> findAllBy(TextCriteria text);

    @Query("{'author.name': ?0}")
    public List<ArticleEntity> findByAuthor(String text);

    public List<ArticleEntity> findByPublishDateBetween(Date start, Date end);

}
