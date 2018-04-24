package com.sample.news.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sharaf on 4/23/18.
 */
@JsonAutoDetect
@JsonIgnoreProperties
public class Article  extends ResourceSupport implements Serializable{

    private String header;

    private String shortDescription;

    private String text;

    private String publishDate;

    private List<String> author;


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "header='" + header + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", text='" + text + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", author=" + author +
                '}';
    }
}
