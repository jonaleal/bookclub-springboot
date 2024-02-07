package com.udea.bookclub.dtos.mappers;

import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
//    @Mapping(target = "userId", source = "userDTO.userId")
//    @Mapping(target = "username", source = "userDTO.username")
//    @Mapping(target = "pictureUrl", source = "userDTO.pictureUrl")
//    @Mapping(target = "email", source = "userDTO.email")
//    @Mapping(target = "password", source = "userDTO.password")
    public User userDTOToUser(UserDTO userDTO);

//    @Mapping(target = "userId", source = "user.userId")
//    @Mapping(target = "username", source = "user.username")
//    @Mapping(target = "pictureUrl", source = "user.pictureUrl")
//    @Mapping(target = "email", source = "user.email")
//    @Mapping(target = "password", source = "user.password")
    public UserDTO userToUserDTO(User user);

//    @Mapping(target = "userId", source = "user.userId")
//    @Mapping(target = "username", source = "user.username")
//    @Mapping(target = "pictureUrl", source = "user.pictureUrl")
//    @Mapping(target = "email", source = "user.email")
//    @Mapping(target = "password", source = "user.password")
    public List<UserDTO> usersToUsersDTO(List<User> users);
}
