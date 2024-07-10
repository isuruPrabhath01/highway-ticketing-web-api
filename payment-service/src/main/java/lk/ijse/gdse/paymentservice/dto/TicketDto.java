package lk.ijse.gdse.paymentservice.dto;

import lk.ijse.gdse.paymentservice.utils.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String ticketId;
    private String vehicleId;
    private Date issueDate;
    private TicketStatus status;
    private String userId;
}
