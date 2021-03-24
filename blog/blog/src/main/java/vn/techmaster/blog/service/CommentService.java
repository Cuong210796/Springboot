package vn.techmaster.blog.service;

import vn.techmaster.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.repository.CommentRepository;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    CommentRepository commentRepo;

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepo.deleteById(id);
    }
}
