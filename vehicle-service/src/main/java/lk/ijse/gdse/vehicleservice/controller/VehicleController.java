package lk.ijse.gdse.vehicleservice.controller;

import lk.ijse.gdse.vehicleservice.dto.VehicleDto;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("server is up and running..!!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveVehicle(@RequestBody VehicleDto vehicleDto){
        try{
            vehicleService.registerVehicle(vehicleDto);
            return new ResponseEntity<>(vehicleDto.getRegisterNumber()+" vehicle registered successfully..!!",HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(vehicleDto.getRegisterNumber()+" vehicle Already in the System..!!",HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleDto vehicleDto){
        try{
            vehicleService.updateVehicle(vehicleDto);
            return new ResponseEntity<>(vehicleDto.getRegisterNumber()+" vehicle updated successfully", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(vehicleDto.getRegisterNumber()+" vehicle cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{registerNumber}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String registerNumber){
        try{
            vehicleService.deleteVehicle(registerNumber);
            return new ResponseEntity<>(registerNumber+" vehicle deleted successfully..!!",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(registerNumber+" vehicle cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByRegisterNumber/{registerNumber}")
    public ResponseEntity<?> findByRegisterNumber(@PathVariable String registerNumber){
        try{
            return new ResponseEntity<>(vehicleService.getVehicleByRegisterNumber(registerNumber),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(registerNumber+" vehicle cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/index")
    public ResponseEntity<?> index(){
        try{
            return new ResponseEntity<>(vehicleService.getAllVehicles(),HttpStatus.OK);
        }catch (RuntimeException E){
            return new ResponseEntity<>("Internal Server Error..!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
