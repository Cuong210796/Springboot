package vn.techmaster.blog.service;

import vn.techmaster.blog.model.Comment;

import java.util.Optional;


public interface ICommentService {

     Optional<Comment> findById(Long id);
     void deleteCommentById(Long id);

}
