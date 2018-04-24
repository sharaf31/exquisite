package com.sample.news.web;

import com.sample.news.exception.ArticleException;
import com.sample.news.model.Article;
import com.sample.news.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sharaf on 4/23/18.
 */
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    private Logger log = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/articles")
    public ResponseEntity<String> createArticle(@RequestBody Article article) {
        try {
            articleService.saveArticle(article);
            return new ResponseEntity("Success", HttpStatus.CREATED);
        } catch (ArticleException e) {
            return new ResponseEntity("Error occured: "+e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/articles/keyword/{keyword}")
    public ResponseEntity<List<Article>> findByKeyWord(@PathVariable(value = "keyword") String keyword) {
        List<Article> resultsList = articleService.findByKeyWord(keyword);
        if (!resultsList.isEmpty())
            return new ResponseEntity(resultsList, HttpStatus.OK);
        else
            return new ResponseEntity(resultsList, HttpStatus.NO_CONTENT);

    }


    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findAll() {
        List<Article> resultsList = articleService.getAllArticles();
        if (!resultsList.isEmpty())
            return new ResponseEntity(resultsList, HttpStatus.OK);
        else
            return new ResponseEntity(resultsList, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/articles/author/{author}")
    public ResponseEntity<List<Article>> findByAuthor(@PathVariable(value = "author") String author) {
        List<Article> resultsList = articleService.getAllArticleForAuthor(author);
        if (!resultsList.isEmpty())
            return new ResponseEntity(resultsList, HttpStatus.OK);
        else
            return new ResponseEntity(resultsList, HttpStatus.NO_CONTENT);

    }


    @GetMapping("/articles/{header}")
    public ResponseEntity<Article> findone(@PathVariable(value = "header") String header) {
        Article article = articleService.getOne(header);
        if (article != null)
            return new ResponseEntity(article, HttpStatus.OK);
        else
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/articles/{header}")
    public ResponseEntity<String> deleteOne(@PathVariable(value = "header") final String header) {
        articleService.deleteArticle(header);
        return new ResponseEntity("Deleted", HttpStatus.NO_CONTENT);
    }


    @GetMapping("/articles/period")
    public ResponseEntity<List<Article>> findArticleOnGivenPeriod(@RequestParam("begin") String begin, @RequestParam("end") String end) {
        try {
            List<Article> resultsList = articleService.getAllArticleOnPeriod(begin, end);
            if (!resultsList.isEmpty())
                return new ResponseEntity(resultsList, HttpStatus.OK);
            else
                return new ResponseEntity(resultsList, HttpStatus.NO_CONTENT);

        } catch (ArticleException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
