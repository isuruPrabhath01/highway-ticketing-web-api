package lk.ijse.gdse.userservice.controller;

import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService  userService;

    @GetMapping("/health")
    public String healthCheck() {
        return "User service is up and running";
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        try {
            userService.registerUser(userDTO);
            return new ResponseEntity<>(userDTO.getEmail() + " User Created..!!", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(userDTO.getEmail()+" user Already in the System..!!",HttpStatus.CONFLICT);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO){
        try {
            userService.updateUser(userDTO);
            return new ResponseEntity<>("User Update Successfully", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(userDTO.getEmail()+" user cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        try {
            userService.deleteUser(email);
            return new ResponseEntity<>(email + " User Deleted..!!", HttpStatus.GONE);
        }catch (RuntimeException e){
            return new ResponseEntity<>(email+" user cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(email+" user cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/verifiedPassword/{email}/{password}")
    public ResponseEntity<String> verifiedPassword(@PathVariable String email,@PathVariable String password){
        try{
            if (userService.checkPassword(email,password))
                return new ResponseEntity<>(email+" User's password is Verified..!!  ",HttpStatus.OK);
            else
                return new ResponseEntity<>(email+" User's password is invalid..!!  ",HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(email+" user cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }
}