package lk.ijse.gdse.userservice.service.impl;

import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.repo.UserRepo;
import lk.ijse.gdse.userservice.service.UserService;
import lk.ijse.gdse.userservice.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userREPO;

    @Autowired
    Convertor convertor;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) {
        if(userREPO.existsByEmail(userDTO.getEmail()))
            throw new RuntimeException("user already in the system..!!");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userREPO.save(convertor.userDtoToEntity(userDTO));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if(!userREPO.existsByEmail(userDTO.getEmail()))
            throw new RuntimeException("cannot find user ");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userREPO.save(convertor.userDtoToEntity(userDTO));
    }

    @Override
    public void deleteUser(String email) {
        if(!userREPO.existsByEmail(email))
            throw new RuntimeException("cannot find user ");
        userREPO.deleteByEmail(email);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        if(!userREPO.existsByEmail(email))
            throw new RuntimeException(" Cannot Find user..!!");
        return convertor.userToDto(userREPO.findByEmail(email));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return convertor.userEntityListToUserDTOList(userREPO.findAll());
    }

    @Override
    public boolean checkPassword(String email, String password) {
        if(!userREPO.existsByEmail(email))
            throw new RuntimeException(" Cannot Find user..!!");
        return passwordEncoder.matches(password,userREPO.findByEmail(email).getPassword());
    }

}
