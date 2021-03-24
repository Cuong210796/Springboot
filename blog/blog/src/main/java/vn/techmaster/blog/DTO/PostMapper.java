package vn.techmaster.blog.DTO;

import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
  
  Post postRequestToPost(PostRequest postRequest);

  @Mapping(target="user_id", source="post.user.id")
  PostRequest postToPostRequest(Post post);

  
  @Mapping(target="user_id", source="post.user.id")
  @Mapping(target="userFullName", source="post.user.fullname")
  PostPOJO postToPostPOJO(Post post);
}