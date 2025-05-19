package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.JavaBookLoanRequest;
import com.group.libraryapp.dto.book.request.JavaBookRequest;
import com.group.libraryapp.dto.book.request.JavaBookReturnRequest;
import com.group.libraryapp.service.book.JavaBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
//@RestController
public class JavaBookController {

  private final JavaBookService javaBookService;

  public JavaBookController(JavaBookService javaBookService) {
    this.javaBookService = javaBookService;
  }

  @PostMapping("/book")
  public void saveBook(@RequestBody JavaBookRequest request) {
    javaBookService.saveBook(request);
  }

  @PostMapping("/book/loan")
  public void loanBook(@RequestBody JavaBookLoanRequest request) {
    javaBookService.loanBook(request);
  }

  @PutMapping("/book/return")
  public void returnBook(@RequestBody JavaBookReturnRequest request) {
    javaBookService.returnBook(request);
  }

}
