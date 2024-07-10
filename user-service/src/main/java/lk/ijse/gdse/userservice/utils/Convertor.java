package lk.ijse.gdse.userservice.utils;


import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public User userDtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO userToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public List<UserDTO> userEntityListToUserDTOList(List<User> users){
        return modelMapper.map(users,new TypeToken<List<UserDTO>>(){}.getType());
    }
}
