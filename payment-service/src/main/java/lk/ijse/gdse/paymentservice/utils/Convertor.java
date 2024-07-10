package lk.ijse.gdse.paymentservice.utils;


import lk.ijse.gdse.paymentservice.dto.PaymentDto;
import lk.ijse.gdse.paymentservice.dto.PaymentTicketDto;
import lk.ijse.gdse.paymentservice.dto.TicketDto;
import lk.ijse.gdse.paymentservice.entity.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public Payment paymentDtoToEntity(PaymentDto paymentDto){
        return modelMapper.map(paymentDto, Payment.class);
    }
    public PaymentDto paymentToDto(Payment payment) {
        return modelMapper.map(payment, PaymentDto.class);
    }
    public List<PaymentDto> paymentEntityListToPaymentDTOList(List<Payment> vehicles){
        return modelMapper.map(vehicles,new TypeToken<List<PaymentDto>>(){}.getType());
    }
    public PaymentTicketDto convertToPaymentTicketDto(PaymentDto paymentDto, TicketDto ticketDto){
        return new PaymentTicketDto(paymentDto,ticketDto);
    }
}