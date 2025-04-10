package com.example.esearch.model;

//import org.springframework.data.elasticsearch.annotations.Setting;


public class Article {
    private String id;
    private String title;
    private String content;

    public Article() {}
    public Article(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    // Getters and setters...
}
