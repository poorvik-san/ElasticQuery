package com.example.esearch.service;

//import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.esearch.model.Article;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jakarta.annotation.PostConstruct;

@Service
public class ArticleService {

    @Autowired
    private ElasticsearchClient client;

    @PostConstruct
public void indexDummyData() {
    try {
        List<Article> articles = List.of(
            new Article("1", "Elasticsearch Basics", "Learn how to use Elasticsearch."),
            new Article("2", "Spring Boot Intro", "Getting started with Spring Boot."),
            new Article("3", "Docker and Containers", "Intro to containerization.")
        );

        for (Article article : articles) {
            client.index(i -> i
                .index("articles")
                .id(article.getId())
                .document(article)
            );
        }

        System.out.println("Dummy articles indexed successfully");

    } catch (Exception e) {
        System.err.println("❌ Error indexing dummy data: " + e.getMessage());
        e.printStackTrace();
    }
}

}
