package com.sample.news.web;

import com.sample.news.model.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sharaf on 4/23/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Article article, article2, article3;


    @Before
    public void setUp() {
        article = new Article();
        article.setHeader("KFC prank");
        article.setShortDescription("local boys prank gone wrong at kfc");
        article.setText("Local boy has a prank setup at KFC gone wrong , causing delay in eating chicken");
        article.setPublishDate("01/04/2018");
        article.setAuthor(Arrays.asList("Ozil"));

        article2 = new Article();
        article2.setHeader("Mc prank");
        article2.setShortDescription("local girls prank gone crazy at Mc");
        article2.setText("Local girls has a prank setup at MC gone wrong , causing delay in eating burgers");
        article2.setPublishDate("02/04/2018");
        article2.setAuthor(Arrays.asList("Ozil"));

        article3 = new Article();
        article3.setHeader("Pizza prank");
        article3.setShortDescription("local teens prank gone crazy at Pizzeria");
        article3.setText("Local teens has a prank setup at Pizzeria gone wrong , causing delay in eating pizza");
        article3.setPublishDate("04/04/2018");
        article3.setAuthor(Arrays.asList("Ozil"));
        this.restTemplate.postForEntity("http://localhost:" + port + "/articles", article3, String.class);
        this.restTemplate.postForEntity("http://localhost:" + port + "/articles", article2, String.class);

    }

    @After
    public void tearDown() {
        this.restTemplate.delete("http://localhost:" + port + "/articles/Mc prank");
        this.restTemplate.delete("http://localhost:" + port + "/articles/Mc prank");
    }


    @Test
    public void findoneTest() throws Exception {
        ResponseEntity<Article> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/Mc prank", Article.class);
        assertTrue(responseEntity.getBody().getHeader().equalsIgnoreCase("Mc prank"));
    }

    @Test
    public void createArticleTest() throws Exception {
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/articles", article,
                String.class);
        assertTrue(responseEntity.getBody().equals("Success"));
    }

    @Test
    public void findByKeyWordTest() throws Exception {
        ResponseEntity<Article[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/keyword/prank", Article[].class);
        assertTrue(Arrays.asList(responseEntity.getBody()).size() > 0);
    }

    @Test
    public void findByfindAllTest() throws Exception {
        ResponseEntity<Article[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/", Article[].class);
        assertTrue(Arrays.asList(responseEntity.getBody()).size() > 2);
    }

    @Test
    public void findByAuthorTest() throws Exception {
        ResponseEntity<Article[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/author/Ozil", Article[].class);
        assertTrue(Arrays.asList(responseEntity.getBody()).size() > 0);
    }

    @Test
    public void findArticleOnGivenPeriodTest() throws Exception {
        ResponseEntity<Article[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/period?begin=01/04/2018&end=20/04/2018", Article[].class);
        assertTrue(Arrays.asList(responseEntity.getBody()).size() > 0);
    }

    @Test
    public void deleteOneTest() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/articles/Pizza prank", String.class);
        ResponseEntity<Article> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/articles/Pizza prank", Article.class);
        assertNull(responseEntity.getBody());
    }

}
