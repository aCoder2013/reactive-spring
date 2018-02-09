package com.example.demo.repository

import com.example.demo.domain.Article
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author song
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ArticleRepositoryTest {


    @Autowired lateinit var articleRepository: ArticleRepository

    @Test
    fun findByTitle() {
        articleRepository.run {
            save(Article(title = "Hello World"))
                    .single()
                    .block()
            val article = findByTitle(title = "Hello World")
                    .block()
            assertNotNull(article)
            assertEquals("Hello World", article?.title)
        }
    }

    @After
    fun tearDown() {
        articleRepository.deleteAll().block()
    }
}