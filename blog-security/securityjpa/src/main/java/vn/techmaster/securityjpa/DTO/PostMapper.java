package vn.techmaster.securityjpa.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.techmaster.securityjpa.controller.request.PostRequest;
import vn.techmaster.securityjpa.model.Post;


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