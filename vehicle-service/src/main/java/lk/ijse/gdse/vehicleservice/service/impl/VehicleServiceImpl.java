package lk.ijse.gdse.vehicleservice.service.impl;

import lk.ijse.gdse.vehicleservice.dto.VehicleDto;
import lk.ijse.gdse.vehicleservice.repo.VehicleRepo;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import lk.ijse.gdse.vehicleservice.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    Convertor convertor;

    @Override
    public void registerVehicle(VehicleDto vehicleDto) {
        if(vehicleRepo.existsById(vehicleDto.getRegisterNumber()))
            throw new RuntimeException("already in the system");
        vehicleRepo.save(convertor.vehicleDtoToEntity(vehicleDto));
    }

    @Override
    public void updateVehicle(VehicleDto vehicleDto) {
        if(!vehicleRepo.existsById(vehicleDto.getRegisterNumber()))
            throw new RuntimeException("cannot find in the system");
        vehicleRepo.save(convertor.vehicleDtoToEntity(vehicleDto));
    }

    @Override
    public void deleteVehicle(String registerNumber) {
        if(!vehicleRepo.existsById(registerNumber))
            throw new RuntimeException("cannot find in the system");
        vehicleRepo.deleteById(registerNumber);
    }

    @Override
    public VehicleDto getVehicleByRegisterNumber(String registerNumber) {
        if(!vehicleRepo.existsById(registerNumber))
            throw new RuntimeException("cannot find in the system");
        return convertor.vehicleToDto(vehicleRepo.findById(registerNumber).get());
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return convertor.vehicleEntityListToVehicleDTOList(vehicleRepo.findAll());
    }
}
