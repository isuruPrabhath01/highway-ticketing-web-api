package lk.ijse.gdse.userservice.repo;

import lk.ijse.gdse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    void deleteByEmail(String email);
}
