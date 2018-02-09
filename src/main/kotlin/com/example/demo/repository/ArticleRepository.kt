package com.example.demo.repository

import com.example.demo.domain.Article
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * @author song
 */
@Repository
interface ArticleRepository : ReactiveCrudRepository<Article, String> {

    fun findByTitle(title: String): Mono<Article>
}
