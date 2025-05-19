package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.JavaBookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.JavaUserRepository;
import com.group.libraryapp.domain.user.loanhistory.JavaUserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.JavaBookLoanRequest;
import com.group.libraryapp.dto.book.request.JavaBookRequest;
import com.group.libraryapp.dto.book.request.JavaBookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JavaBookService {

  private final JavaBookRepository javaBookRepository;
  private final JavaUserRepository javaUserRepository;
  private final JavaUserLoanHistoryRepository javaUserLoanHistoryRepository;

  public JavaBookService(
      JavaBookRepository javaBookRepository,
      JavaUserRepository javaUserRepository,
      JavaUserLoanHistoryRepository javaUserLoanHistoryRepository
  ) {
    this.javaBookRepository = javaBookRepository;
    this.javaUserRepository = javaUserRepository;
    this.javaUserLoanHistoryRepository = javaUserLoanHistoryRepository;
  }

  @Transactional
  public void saveBook(JavaBookRequest request) {
    Book newBook = new Book(request.getName(), null);
    javaBookRepository.save(newBook);
  }

  @Transactional
  public void loanBook(JavaBookLoanRequest request) {
    Book book = javaBookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
    if (javaUserLoanHistoryRepository.findByBookNameAndIsReturn(request.getBookName(), false) != null) {
      throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
    }

    User user = javaUserRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.loanBook(book);
  }

  @Transactional
  public void returnBook(JavaBookReturnRequest request) {
    User user = javaUserRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.returnBook(request.getBookName());
  }

}
