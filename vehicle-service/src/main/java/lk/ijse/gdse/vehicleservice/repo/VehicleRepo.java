package lk.ijse.gdse.vehicleservice.repo;

import lk.ijse.gdse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {
}
