package vn.techmaster.blog.service;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.model.User;

public interface IAuthenService {
  public void login(LoginRequest loginRequest) throws AuthenException;

  User findByEmail(String email);
}
