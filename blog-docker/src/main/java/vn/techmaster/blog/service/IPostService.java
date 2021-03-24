package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

public interface IPostService {
  List<Post> getAllPostOfUser(User user);

  List<Post> getAll();
  Post save(Post post);
  Post findById(long id);
  void delete(long id);
}
