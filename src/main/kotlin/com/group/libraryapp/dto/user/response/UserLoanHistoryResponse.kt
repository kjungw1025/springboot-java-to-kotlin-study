package com.group.libraryapp.dto.user.response

data class UserLoanHistoryResponse(
    val name: String,   // 유저 이름
    val books: List<BookHistoryResponse>,
)

// books를 표현하기 위해 같은 파일 내에 만들어줌
data class BookHistoryResponse(
    val name: String,   // 책의 이름
    val isReturn: Boolean,
)