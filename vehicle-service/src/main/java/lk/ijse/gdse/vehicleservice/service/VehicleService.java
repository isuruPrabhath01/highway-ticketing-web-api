package lk.ijse.gdse.vehicleservice.service;

import lk.ijse.gdse.vehicleservice.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    void registerVehicle (VehicleDto vehicleDto);
    void updateVehicle (VehicleDto vehicleDto);
    void deleteVehicle (String registerNumber);
    VehicleDto getVehicleByRegisterNumber (String registerNumber);
    List<VehicleDto> getAllVehicles ();
}
