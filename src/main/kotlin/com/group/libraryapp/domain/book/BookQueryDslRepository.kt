package com.group.libraryapp.domain.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BookQueryDslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun getStatus(): List<BookStatResponse> {
        return queryFactory.select(Projections.constructor(
            BookStatResponse::class.java,
            book.type,
            book.id.count(),
        ))
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}