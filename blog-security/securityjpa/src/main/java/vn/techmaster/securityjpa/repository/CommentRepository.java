package vn.techmaster.securityjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.securityjpa.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
