package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.JavaUserCreateRequest;
import com.group.libraryapp.dto.user.request.JavaUserUpdateRequest;
import com.group.libraryapp.dto.user.response.JavaUserResponse;
import com.group.libraryapp.service.user.JavaUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final JavaUserService javaUserService;

  public UserController(JavaUserService javaUserService) {
    this.javaUserService = javaUserService;
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody JavaUserCreateRequest request) {
    javaUserService.saveUser(request);
  }

  @GetMapping("/user")
  public List<JavaUserResponse> getUsers() {
    return javaUserService.getUsers();
  }

  @PutMapping("/user")
  public void updateUserName(@RequestBody JavaUserUpdateRequest request) {
    javaUserService.updateUserName(request);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name) {
    javaUserService.deleteUser(name);
  }

}
