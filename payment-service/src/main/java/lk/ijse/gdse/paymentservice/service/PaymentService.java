package lk.ijse.gdse.paymentservice.service;

import lk.ijse.gdse.paymentservice.dto.PaymentDto;
import lk.ijse.gdse.paymentservice.dto.PaymentTicketDto;

import java.util.List;

public interface PaymentService {
    void createPayment(PaymentDto paymentDto);
    void updatePayment (PaymentDto paymentDto);
    void deletePayment (String paymentId);
    PaymentTicketDto getPaymentByPaymentId(String paymentId);
    List<PaymentDto> getAllPayments ();
}
