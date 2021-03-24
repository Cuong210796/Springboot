package vn.techmaster.blog.DTO;

import vn.techmaster.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//Đây là interface để chuyển đổi dữ liệu từ đối tượng kiểu A sang đối tượng kiểu B
@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
  UserInfo userToUserInfo(User user);
  User userInfoToUser(UserInfo userInfo);
}
