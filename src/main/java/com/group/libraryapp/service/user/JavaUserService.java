package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.JavaUserRepository;
import com.group.libraryapp.dto.user.request.JavaUserCreateRequest;
import com.group.libraryapp.dto.user.request.JavaUserUpdateRequest;
import com.group.libraryapp.dto.user.response.JavaUserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JavaUserService {

  private final JavaUserRepository javaUserRepository;

  public JavaUserService(JavaUserRepository javaUserRepository) {
    this.javaUserRepository = javaUserRepository;
  }

  @Transactional
  public void saveUser(JavaUserCreateRequest request) {
    User newUser = new User(request.getName(), request.getAge(), Collections.emptyList(), null);
    javaUserRepository.save(newUser);
  }

  @Transactional(readOnly = true)
  public List<JavaUserResponse> getUsers() {
    return javaUserRepository.findAll().stream()
        .map(JavaUserResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public void updateUserName(JavaUserUpdateRequest request) {
    User user = javaUserRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
    user.updateName(request.getName());
  }

  @Transactional
  public void deleteUser(String name) {
    User user = javaUserRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    javaUserRepository.delete(user);
  }

}
