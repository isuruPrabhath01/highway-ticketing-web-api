package lk.ijse.gdse.userservice.service;

import lk.ijse.gdse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    void registerUser (UserDTO userDTO);
    void updateUser (UserDTO userDTO);
    void deleteUser (String email);
    UserDTO getUserByEmail (String email);
    List<UserDTO> getAllUsers ();
    boolean checkPassword(String email, String password);
}
