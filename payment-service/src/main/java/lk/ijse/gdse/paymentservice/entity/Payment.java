/**
 * Created By Isuru Prabhath
 * Date : 7/5/2024
 * Time : 9:15 AM
 * Project Name : payment-service
 */

package lk.ijse.gdse.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    private String id;
    private String ticketId;
    private Date date;
    private double price;
    private String type;
}
