package com.sample.news.util;

import com.sample.news.entity.ArticleEntity;
import com.sample.news.entity.AuthorEntity;
import com.sample.news.exception.ArticleException;
import com.sample.news.model.Article;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sharaf on 4/23/18.
 */
public final class Transformer {

    public static List<Article> transfomEntities(List<ArticleEntity> entityList) {
        List<Article> articleList = new ArrayList<>();
        if (entityList != null & entityList.size() != 0)
            entityList.stream().forEach((entity) -> {
                    articleList.add(transfomEntity(entity));
        });
        return articleList;

    }

    public static Article transfomOptionalEntity(Optional<ArticleEntity> entityOptional) {
        if(entityOptional.isPresent())
            return  transfomEntity(entityOptional.get());
         else
             return null;
    }

    public static Article transfomEntity(ArticleEntity entity) {
        Article model = new Article();
        if (entity != null) {

            model.setHeader(entity.getHeader());
            model.setPublishDate(DateUtil.convertDateToFormattedString(entity.getPublishDate()));
            model.setShortDescription(entity.getShortDescription());
            model.setText(entity.getText());
            model.setAuthor(new ArrayList<>());
            if (entity.getAuthor() != null)
                entity.getAuthor().stream().forEach((authorEntity)->{
                    model.getAuthor().add(authorEntity.getName());
                });
            Link link= ControllerLinkBuilder.linkTo(Article.class).slash("articles").slash(model.getHeader()).withSelfRel();
            model.add(link);

        }
        return model;
    }

    public static ArticleEntity tranformModel(Article model) throws ArticleException {
        ArticleEntity entity = new ArticleEntity();
        if (model != null) {

            entity.setHeader(model.getHeader());
            entity.setShortDescription(model.getShortDescription());
            entity.setText(model.getText());
            entity.setPublishDate(DateUtil.convertStringToFormattedDate(model.getPublishDate()));
            entity.setAuthor(new ArrayList<>());
            if (model.getAuthor() != null)
                model.getAuthor().stream().forEach((author)->{
                    entity.getAuthor().add((new AuthorEntity(author)));
                });

        }
        return entity;

    }

}
