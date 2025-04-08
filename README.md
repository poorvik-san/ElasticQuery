All good — no worries, I’ve got you! Here's the **final full `README.md` file** with **Search API** and **Sample Query** *already included and formatted* — everything in place and clean:


# ElasticQuery

A simple Spring Boot application integrated with Elasticsearch for indexing and multi-field search.

---

## 🛠 Tech Stack

- Spring Boot  
- Elasticsearch (Java API Client)

---

## 📦 Project Overview

This project demonstrates how to index data into Elasticsearch and perform multi-field full-text search using Spring Boot.

---

## 📌 Indexing Dummy Data

Dummy articles are indexed into the `articles` index.

```java
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
    }
}
```

> Indexes a predefined list of articles into Elasticsearch.

---

## 🔍 Search API

Search for articles by matching keywords across multiple fields.

**Endpoint:**

```
GET /search?q={keyword}
```

**Parameters:**

- `q` — Keyword or phrase to search in the `title` and `content` fields.

**Example:**

```java
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
        Article.class
    );

    return response.hits().hits().stream()
        .map(Hit::source)
        .collect(Collectors.toList());
}
```

> Performs a multi-match query and returns all matching articles.

---

## ✅ Sample Query

**Request:**

```
GET http://localhost:8080/search?q=use
```

**Response:**

```json
[
  {
    "id": "1",
    "title": "Elasticsearch Basics",
    "content": "Learn how to use Elasticsearch."
  }
]
```

> Returns articles that contain the word `use` in the title or content.

---

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-repo/elastic-query.git
   cd elastic-query
   ```

2. **Start Elasticsearch**  
   Ensure Elasticsearch is running locally (default port `9200`).

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Test the API**
   Open in browser or Postman:  
   [http://localhost:8080/search?q=your-keyword](http://localhost:8080/search?q=your-keyword)

---

## 📄 License

MIT License. Use freely and modify as needed.
```

You're all set now! Want me to help generate that `Article` model or add Swagger docs next?
