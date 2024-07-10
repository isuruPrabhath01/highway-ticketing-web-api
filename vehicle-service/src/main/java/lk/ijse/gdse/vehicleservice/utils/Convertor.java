package lk.ijse.gdse.vehicleservice.utils;

import lk.ijse.gdse.vehicleservice.dto.VehicleDto;
import lk.ijse.gdse.vehicleservice.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public Vehicle vehicleDtoToEntity(VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto, Vehicle.class);
    }
    public VehicleDto vehicleToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }
    public List<VehicleDto> vehicleEntityListToVehicleDTOList(List<Vehicle> vehicles){
        return modelMapper.map(vehicles,new TypeToken<List<VehicleDto>>(){}.getType());
    }
}