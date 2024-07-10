package lk.ijse.gdse.paymentservice.service.impl;

import lk.ijse.gdse.paymentservice.dto.PaymentDto;
import lk.ijse.gdse.paymentservice.dto.PaymentTicketDto;
import lk.ijse.gdse.paymentservice.dto.TicketDto;
import lk.ijse.gdse.paymentservice.repo.PaymentRepo;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import lk.ijse.gdse.paymentservice.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private Convertor convertor;

    @Value("${microservice.ticket-service.endpoints.endpoint.uri}")
    private String TICKET_SERVICE_ENDPOINT;

    @Override
    public void createPayment(PaymentDto paymentDto) {
        if(paymentRepo.existsById(paymentDto.getId()))
            throw new RuntimeException(paymentDto.getId()+" is already in the system..!!");
        if (Boolean.FALSE.equals(restTemplate.getForObject(TICKET_SERVICE_ENDPOINT +"existsById/"+ paymentDto.getTicketId(), Boolean.class)))
            throw new RuntimeException(paymentDto.getTicketId()+" Ticket Id cannot find in the system..!!");
        paymentRepo.save(convertor.paymentDtoToEntity(paymentDto));
    }

    @Override
    public void updatePayment(PaymentDto paymentDto) {
        if(!paymentRepo.existsById(paymentDto.getId()))
            throw new RuntimeException(paymentDto.getId()+" payment not in the system..!!");
        if (Boolean.FALSE.equals(restTemplate.getForObject(TICKET_SERVICE_ENDPOINT +"existsById/"+ paymentDto.getTicketId(), Boolean.class)))
            throw new RuntimeException(paymentDto.getTicketId()+" Ticket Id cannot find in the system..!!");
        paymentRepo.save(convertor.paymentDtoToEntity(paymentDto));
    }

    @Override
    public void deletePayment(String paymentId) {
        if(!paymentRepo.existsById(paymentId))
            throw new RuntimeException(paymentId+" payment not in the system..!!");
        paymentRepo.deleteById(paymentId);
    }

    @Override
    public PaymentTicketDto getPaymentByPaymentId(String paymentId) {
        if(!paymentRepo.existsById(paymentId))
            throw new RuntimeException(paymentId+" payment not in the system..!!");
        PaymentDto paymentDto=convertor.paymentToDto(paymentRepo.findById(paymentId).get());
        return new PaymentTicketDto(paymentDto,restTemplate.getForObject(TICKET_SERVICE_ENDPOINT+"findByTicketId/"+paymentDto.getTicketId(), TicketDto.class));
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return convertor.paymentEntityListToPaymentDTOList(paymentRepo.findAll());
    }
}
