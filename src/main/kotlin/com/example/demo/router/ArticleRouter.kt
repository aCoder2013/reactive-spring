package com.example.demo.router

import com.example.demo.domain.Article
import com.example.demo.service.ArticleService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


/**
 * @author song
 */
@Configuration
class ArticleRouter(val articleService: ArticleService) {

    @Bean
    fun router() = router {
        GET("/articles", { ServerResponse.ok().body(articleService.getAllArticles(), Article::class.java) })
        GET("/article/{id}", {
            ServerResponse.ok()
                    .body(articleService.getArticleById(it.pathVariable("id")), Article::class.java)
        })
        GET("/articles/generate", {
            ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(articleService.articleGenerator(), Article::class.java)
        })
    }

}