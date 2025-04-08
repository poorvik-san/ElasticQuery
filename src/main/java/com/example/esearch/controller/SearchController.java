package com.example.esearch.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.esearch.model.*;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;

import co.elastic.clients.elasticsearch.core.search.Hit;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ElasticsearchClient client;

    @GetMapping
    public List<Article> search(@RequestParam("q") String query) throws IOException {
        
        SearchResponse<Article> response = client.search(s -> s
                .index("articles")
                .query(q -> q
                    .multiMatch(m -> m
                        .fields("title", "content")
                        .query(query) 
                    )
                ),
            Article.class // Specify the class type for deserialization let me make it simpel 
            //appagi we are doing a search and the search results should be the same class
        );

        return response.hits().hits().stream()
            .map(Hit::source)
            .collect(Collectors.toList());
    }
}
