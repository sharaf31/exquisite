package com.sample.news.service;

import com.sample.news.exception.ArticleException;
import com.sample.news.model.Article;
import com.sample.news.repository.ArticleRepository;
import com.sample.news.util.DateUtil;
import com.sample.news.util.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sharaf on 4/23/18.
 */

@Component
public class ArticleService {


    private Logger log = LoggerFactory.getLogger(ArticleService.class);


    private ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article saveArticle(Article article) throws ArticleException {
        return Transformer.transfomEntity(repository.save(Transformer.tranformModel(article)));
    }


    public List<Article> findByKeyWord(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        return Transformer.transfomEntities(repository.findAllBy(criteria));
    }

    public void deleteArticle(String header) {
        repository.deleteById(header);
    }


    public Article getOne(String header) {
        Article article = Transformer.transfomOptionalEntity(repository.findById(header));
        if (article != null)
            return article;
        else
            return null;
    }

    public List<Article> getAllArticleForAuthor(String author) {
        return Transformer.transfomEntities(repository.findByAuthor(author));
    }


    public List<Article> getAllArticles() {
        return Transformer.transfomEntities(repository.findAll());
    }

    public List<Article> getAllArticleOnPeriod(String begin, String end) throws ArticleException {
        return Transformer.transfomEntities(repository.findByPublishDateBetween(DateUtil.convertStringToFormattedDate(begin), DateUtil.convertStringToFormattedDate(end)));
    }

}
