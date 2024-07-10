package lk.ijse.gdse.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTicketDto {
    private PaymentDto paymentDto;
    private TicketDto ticketDto;
}
