package vn.techmaster.blog.service;

import org.springframework.stereotype.Repository;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

import java.util.List;

public interface ICommentService {

    Comment save(Comment comment);
    Comment findById(Long id);
    void delete(long id);
}
