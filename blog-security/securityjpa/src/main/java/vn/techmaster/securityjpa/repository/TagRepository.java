package vn.techmaster.securityjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.securityjpa.model.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
  
}