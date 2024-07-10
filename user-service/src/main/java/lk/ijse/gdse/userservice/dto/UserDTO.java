package lk.ijse.gdse.userservice.dto;

import lk.ijse.gdse.userservice.utils.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String name;
    private UserRole role;
    private String email;
    private String mobile_number;
    protected String password;
}
