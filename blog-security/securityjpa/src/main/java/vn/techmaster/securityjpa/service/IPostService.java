package vn.techmaster.securityjpa.service;


import org.springframework.data.domain.Page;
import vn.techmaster.securityjpa.controller.request.CommentRequest;
import vn.techmaster.securityjpa.controller.request.PostRequest;
import vn.techmaster.securityjpa.model.Post;
import vn.techmaster.securityjpa.model.Tag;
import vn.techmaster.securityjpa.model.User;

import java.util.List;
import java.util.Optional;

public interface IPostService {
  public List<Post> findAll();
  public Page<Post> findAllPaging(int page, int pageSize);

  public List<Post> getAllPostOfUser(User user);
  public List<Post> getAllPostsByUserID(long user_id);
  public void createNewPost(PostRequest postRequest) throws PostException;
  public Optional<Post> findById(Long id);
  public void deletePostById(Long id);
  public void updatePost(PostRequest postRequest) throws PostException;
  public void addComment(CommentRequest commentRequest, long user_id) throws PostException;

  public List<Tag> getAllTags();

  public List<Post> searchPost(String term, int limit, int offset);

  public void reindexFullText();

  public void generateSampleData();
}