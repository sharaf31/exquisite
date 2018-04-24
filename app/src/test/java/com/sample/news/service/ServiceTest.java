package com.sample.news.service;

import com.sample.news.entity.ArticleEntity;
import com.sample.news.entity.AuthorEntity;
import com.sample.news.exception.ArticleException;
import com.sample.news.model.Article;
import com.sample.news.repository.ArticleRepository;
import com.sample.news.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


/**
 * Created by sharaf on 4/23/18.
 */
public class ServiceTest {


    @InjectMocks
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepositoryMock;

    private ArticleEntity mockArticleEntity1, mockArticleEntity2, mockArticleEntity3;
    private Article article1, article2;


    @Before
    public void setUp() {

        articleRepositoryMock = Mockito.mock(ArticleRepository.class);
        articleService = new ArticleService(articleRepositoryMock);


        article1 = new Article();
        article1.setHeader("KFC prank");
        article1.setShortDescription("local boys prank gone wrong at kfc");
        article1.setText("Local boy has a prank setup at KFC gone wrong , causing delay in eating chicken");
        article1.setPublishDate("01/04/2019");
        article1.setAuthor(Arrays.asList("Ozil"));

        mockArticleEntity1 = new ArticleEntity();
        mockArticleEntity1.setHeader("KFC prank");
        mockArticleEntity1.setShortDescription("local boys prank gone wrong at kfc");
        mockArticleEntity1.setText("Local boy has a prank setup at KFC gone wrong , causing delay in eating chicken");
        try {
            mockArticleEntity1.setPublishDate(DateUtil.convertStringToFormattedDate("01/04/2019"));
        } catch (ArticleException e) {
            e.printStackTrace();
        }
        mockArticleEntity1.setAuthor(Arrays.asList(new AuthorEntity("Ozil")));


        article2 = new Article();
        article2.setHeader("Mc prank");
        article2.setShortDescription("local girls prank gone crazy at Mc");
        article2.setText("Local girls has a prank setup at MC gone wrong , causing delay in eating burgers");
        article2.setPublishDate("02/04/2019");
        article2.setAuthor(Arrays.asList("Ozil"));


        mockArticleEntity2 = new ArticleEntity();
        mockArticleEntity2.setHeader("Mc prank");
        mockArticleEntity2.setShortDescription("local girls prank gone crazy at Mc");
        mockArticleEntity2.setText("Local girls has a prank setup at MC gone wrong , causing delay in eating burgers");
        try {
            mockArticleEntity2.setPublishDate(DateUtil.convertStringToFormattedDate("02/04/2019"));
        } catch (ArticleException e) {
            e.printStackTrace();
        }
        mockArticleEntity2.setAuthor(Arrays.asList(new AuthorEntity("Ozil")));

        mockArticleEntity3 = new ArticleEntity();
        mockArticleEntity3.setHeader("Pizza prank");
        mockArticleEntity3.setShortDescription("local teens prank gone crazy at Pizzeria");
        mockArticleEntity3.setText("Local teens has a prank setup at Pizzeria gone wrong , causing delay in eating pizza");
        try {
            mockArticleEntity3.setPublishDate(DateUtil.convertStringToFormattedDate("02/04/2019"));
        } catch (ArticleException e) {
            e.printStackTrace();
        }
        mockArticleEntity3.setAuthor(Arrays.asList(new AuthorEntity("Ozil")));

    }


    @Test
    public void saveArticleTest() throws Exception {
        when(articleRepositoryMock.save(any(ArticleEntity.class))).thenReturn(mockArticleEntity1);
        Article response = articleService.saveArticle(article1);
        assertTrue(response.getHeader().equals("KFC prank"));
    }

    @Test
    public void findByKeyWordTest() throws Exception {
        when(articleRepositoryMock.findAllBy(any(TextCriteria.class))).thenReturn(Arrays.asList(mockArticleEntity2));
        List<Article> response = articleService.findByKeyWord("Mc");
        assertTrue(response.get(0).toString().contains("Mc"));
    }

    @Test
    public void getAllArticleForAuthorTest() throws Exception {
        when(articleRepositoryMock.findByAuthor(eq("Ozil"))).thenReturn(Arrays.asList(mockArticleEntity1,mockArticleEntity2,mockArticleEntity3));
        List<Article> response = articleService.getAllArticleForAuthor("Ozil");
        assertTrue(response.size()>2);
    }

    @Test
    public void getAllArticlesTest() throws Exception {
        when(articleRepositoryMock.findAll()).thenReturn(Arrays.asList(mockArticleEntity1,mockArticleEntity2,mockArticleEntity3));
        List<Article> response  = articleService.getAllArticles();
        assertTrue(response.size()>2);
    }


    @Test
    public void getAllArticleOnPeriodTest() throws Exception {
        when(articleRepositoryMock.findByPublishDateBetween(any(Date.class),any(Date.class))).thenReturn(Arrays.asList(mockArticleEntity1,mockArticleEntity2,mockArticleEntity3));
        List<Article> response = articleService.getAllArticleOnPeriod("01/04/2016","23/04/2018");
        assertTrue(response.size()>2);
    }
}
