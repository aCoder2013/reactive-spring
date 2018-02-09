package com.example.demo

import com.example.demo.domain.Article
import com.example.demo.repository.ArticleRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Flux

@SpringBootApplication
class DemoApplication {

    @Bean
    fun runner(ar: ArticleRepository) = ApplicationRunner {
        val articles = Flux.just("Paxos protocol", "AEon Flux", "Back to the Future")
                .flatMap { ar.save(Article(title = it)) }
        ar.run {
            deleteAll()
                    .thenMany(articles)
                    .thenMany(ar.findAll())
                    .subscribe({ println(it) })
        }

    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
