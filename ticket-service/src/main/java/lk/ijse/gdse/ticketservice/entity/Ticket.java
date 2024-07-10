package lk.ijse.gdse.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.gdse.ticketservice.utils.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {
    @Id
    private String ticketId;
    private String vehicleId;
    private Date issueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private String userId;
}
