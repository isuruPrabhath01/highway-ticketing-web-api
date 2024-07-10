package lk.ijse.gdse.paymentservice.controller;


import jakarta.ws.rs.GET;
import lk.ijse.gdse.paymentservice.dto.PaymentDto;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("Payment service up and running",HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentDto paymentDto){
        try{
            paymentService.createPayment(paymentDto);
            return new ResponseEntity<>(paymentDto.getId()+" Payment placed..!!",HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping
    public  ResponseEntity<String> updatePayment(@RequestBody PaymentDto paymentDto){
        try{
            paymentService.updatePayment(paymentDto);
            return new ResponseEntity<>(paymentDto.getId()+" Payment updated..!!",HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePayment(@PathVariable String id){
        try{
            paymentService.deletePayment(id);
            return new ResponseEntity<>(id+ " Payment deleted..!!",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id){
        try{
            return new ResponseEntity<>(paymentService.getPaymentByPaymentId(id),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/index")
    public ResponseEntity<?> index(){
        try{
            return new ResponseEntity<>(paymentService.getAllPayments(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
