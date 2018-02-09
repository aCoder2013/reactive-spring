package com.example.demo.domain

import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author song
 */
@Document
data class Article(var id: String? = null, val title: String? = null)