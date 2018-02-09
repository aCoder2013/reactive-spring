package com.example.demo.service

import com.example.demo.domain.Article
import com.example.demo.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import java.time.Duration
import java.util.*

/**
 * @author song
 */
@Service
class ArticleService {

    @Autowired lateinit var articleRepository: ArticleRepository

    fun getAllArticles(): Flux<Article> = this.articleRepository.findAll()

    fun getArticleById(id: String): Mono<Article> = this.articleRepository.findById(id)

    fun articleGenerator() = Flux.generate({ sink: SynchronousSink<Article> ->
        sink.next(Article(UUID.randomUUID().toString(), "Test"))
    })
            .delayElements(Duration.ofSeconds(1L))
}